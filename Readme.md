

GraalVM is a universal virtual machine for running applications written in JavaScript, Python 3, Ruby, R, JVM-based languages like Java, Scala, Kotlin, and LLVM-based languages such as C and C++.

GraalVM removes the isolation between programming languages and enables interoperability in a shared runtime. It can run either standalone or in the context of OpenJDK, Node.js, Oracle Database, or MySQL.
<a href="#bib1">[1]</a> 

In other words, Graal provides a boosted low-level Java Virtual Machine.

The latest Benchmarks shows that the basic implementation of Javascript Engine using Graal won against Google V8 Engine. <a href="#bib2">[2]</a>

GraalVM can be embedded in both managed and native applications. There are existing integrations into OpenJDK, Node.js, Oracle Database, and MySQL.

For a proof of concept to study the execution time and memory usage, in a custom language interpretation, the target codes were written in Tiger. It is an academic language with specific issues thought to put down the student abilities, due to that reason the language specification has been changed a little to simplify the proof of concept.


## Tiger language modification ##
The Tiger language specification has been modified to simplify the development time of the proof-of-concept.

Principal modifications:
-  This implementation does not have **Record** types, then, there are not  **Record** fields or **Record** field access

-  There is double type support **(\d+\\.\d+)**

- There are only three builtin functions:
    - **print** (print the String representation of the first argument), ie: 
        
        ``` python
        print(19)
        > 19
        ```
        

        ``` python
         print("hello world")
         > hello world
        ```
        
        ``` python
        print(nano_time())
        > ... 
        ```
    
    - **nano_time** (returns the **System.nanoTime()** Java function result)
    - **wait** (Sleep the current thread by **x** milliseconds where **x** is the first argument)
    
- There is no type declaration or check. All types and values are created and assigned dynamically

- There are no comment declarations

## Graal VM and Truffle

The Truffle framework allows you to run programming languages efficiently on GraalVM. It simplifies language implementation by automatically deriving high-performance code from interpreters. It brings a simple way to transform your custom AST to Graal AST nodes. Truffle is another level of abstraction, but, this API generate code statically using Java annotations, so there is no extra level of code in the interpretation stage, there is only Graal.


### Types

All languages use the abstraction of specific types, or a way to group data by its characteristics, even in the backyard, so if you hear that a language have no types... that is not really true. 2 + 2 returns 4, that is an integer (number, digits) from the beginning of the times.

This Tiger approximation has four primitive types in the interpretation stage.

These are the types mapped using Java POO patterns definition:

   - Long: to represent integers from $$-(2^{64} - 1)$$ to $$2^{64}$$
   - Double: to represent floating comma numbers with, 1 bit for sign, 11 bits for exponent and 52 bits for the fractional part.
   - Function: this is a custom function object implementation
   - Nil: every expression in Tiger returns value, nil is the default one


### Parsing

Tiger script parsing is made using ANTLR 4.0 framework to generate a basic Abstract Syntax Tree to evaluate the script. The first approach to interpret the source code can be visiting the obtained AST, but the main idea of this work is to use/test the Graal and Truflle potentials.

I use the previously obtained AST to transform it into a Truffle tree. Most of the compiler’s implementations have four basic stages: 1 - Tokenize, 2 - Obtain a tree base on the grammar, 3 - Semantic analysis, 4 - Code generaton, the first two stages are achieved using Antlr, the next two are the Graal/Truffle matter.

### Variable Scope

The variables and functions scope are implemented like tree parent struct. Every scope has a parent scope. If a variable does not exist in the current scope, then, it is searched in the parent scope until the variable is found or there is no parent throwing “The variable x is not defined” exception.

```
    
    | a. 1 |
    | b, "hello world" | <-
     ...                   |
     ...                   | a, 10  |
     ...                   | z, 1000|
     
    |c, 1 |
     
```

### Performance

In dynamic languages, the concrete operation behind the multiplication ‘*’, the division ‘/’ or a less-than ‘<’ comparison operator is typically known only at runtime.
 
For optimal performance, a language implementation needs to ensure that these operators are not looked up for every single operation. 

My first approach to the variable (read, write) and function lookups shows that there is a huge overload in did, almost 10X time of the final time execution result. 

### Variable lookup

All the variable and function arguments are stored in **FrameSlots** (this is the Truffle API implementation to store values). I ensure that every variable access (read and write) address is stored in her specific AST Node to avoid walking through the Scope tree structure in every read-write call. The variable context structures are built in the semantical analysis stage and written in the node as an *O(1)* access java field.

The truffle API options to read and write values bring a way to avoid the boxing and unboxing of those values. However, the only way to pass arguments to a function call (Mapping to Graal RootNode class) is passing the values as Object values.

So, I ensure that exist only a few unboxing operations. The only unboxing operations are present in the *ReadArg* node evaluation in the AST.

### Function calls

Truffe can set the function call arguments in an API class named Frame, that is optimized in the Graal VM execution.

In Tiger, we have nested functions args scopes. For this reason, I added an extra argument in the Frame object passed to a function call.

To pass arguments to a function call, I set the arguments as follows :
   - The first argument is the current branch Frame
   - The next arguments are the evaluated expressions for the current Tiger function call
    
        ```
            function a(n)=
                let
                    function b(r)=
                        print(r + n) // to access the n value we have to access the first argument in the b call and then the frame argument of the scope
                in
                    b(n)
                end
        ```
        
        
#### Polymorphic Inline
Polymorphic inline caches optimize function and property lookup in dynamic languages and are usually implemented using assembler code and code patching

### Tests

The <a href="https://github.com/smarr/Classic-Benchmarks/blob/master/benchmarks/Mandelbrot.java#L42ß">classic Mandelbrot benchmark</a> for Java was tested against <a href="https://github.com/Jacarte/tiger-graal/tree/master/tiger-compiler/src/tests/mandelbrot.tiger">a custom tiger Mandelbrot test<a>

### Tests Results and remarks

![alt results](/imgs/result.png)

The results are very encouraging. The deviation between the mean, the max and min execution time values is because Graal VM ensure inline function calls and cache the most cacheable nodes (Integer constants, etc), so the first calls have remarkable delays, but this is a Java run effect too as we can see in the figure above.




## Future research
- Tail call optimization:

    In related works <a href="http://cesquivias.github.io/blog/2015/01/15/writing-a-language-in-truffle-part-4-adding-features-the-truffle-way/">[3]</a> the author proposes to implement a Tail Call optimization showing very good results for recursive function calls
- IGV profiling to detect operations time overloads and unexpected nodes construction

    
## Bibliography

- <a href="https://www.GraalVM.org" id="bib1">GraalVM</a>
- <a id="bib2" href="http://stefan-marr.de/papers/dls-marr-et-al-cross-language-compiler-benchmarking-are-we-fast-yet/">
Cross-Language Compiler Benchmarking</a>
