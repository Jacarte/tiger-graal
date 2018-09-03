operations. The only unboxing operations are present in the *ReadArg* node evaluation in the AST.

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

The <a href="https://github.com/smarr/Classic-Benchmarks/blob/master/benchmarks/Mandelbrot.java#L42ß">classic Mandelbrot benchmark</a> for Java was tested against <a href="https://github.com/Jacarte/tiger-Graal/src/tests/mandelbrot.tiger">a custom tiger Mandelbrot test<a>

### Tests Results and remarks

![alt results](/assets/img/result.png)

The results are very encouraging. The deviation between the mean, the max and min execution time values is because Graal VM ensure inline function calls and cache the most cacheable nodes (Integer constants, etc), so the first calls have remarkable delays, but this is a Java run effect too as we can see in the figure above.




## Future research
- Tail call optimization:

    In related works <a href="http://cesquivias.github.io/blog/2015/01/15/writing-a-language-in-truffle-part-4-adding-features-the-truffle-way/">[3]</a> the author proposes to implement a Tail Call optimization showing very good results for recursive function calls
- IGV profiling to detect operations time overloads and unexpected nodes construction

    
## Bibliography

- <a href="https://www.GraalVM.org" id="bib1">GraalVM</a>
- <a id="bib2" href="http://stefan-marr.de/papers/dls-marr-et-al-cross-language-compiler-benchmarking-are-we-fast-yet/">
Cross-Language Compiler Benchmarking</a>