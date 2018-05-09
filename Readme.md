# Mini Tiger with GraalVm and Truffle proof-of-concept #



## Tiger language modification ##
I modify the official Tiger <a href="https://cs.nyu.edu/courses/fall13/CSCI-GA.2130-001/tiger-spec.pdf">specification</a> to simplify the development time and beacuse this is a proof-of-concept

The principal modifications are:
-  This implementation does not have **Record** types, then, we dont have **Record** fields or **Record** field access

-  I add double type support **(\d+\\.\d+)**

- There are only three builtin functions:
    - **print** (print the String representation of the first argument), ie: 
    ``` 
    print(19)
    > 19
     ```
     ``` 
     print("hello world")
     > 19
     ```
    ``` 
    print(nano_time())
    > ... 
    ```
    - **nano_time** (returns the System.nanoTime() Java function)
    - **wait** (Sleep the current thread by x milliseconds where x is the first argument)
    
- There is no type declaration or check. All types are created dynamically

- We dont have comment declarations

## Graal VM and Truffle

I use the <a href="http://www.oracle.com/technetwork/oracle-labs/program-languages/downloads/index.html">official</a> graal jdk compilation with Truffle (*com.oracle.truffle:1.0.0-rc1*)

I transform the Antlr output AST to Truffle api basically

### Types
These are the types mapped to Truffle Tiger language definition:
   - Long
   - Double
   - Function (This is a custom function object implementation)
   - Nil (Every expression in Mini-Tiger returns value, so, nil is the default)

### Variable Scope

### Variable lookup

### Function call

### Boxing and unboxing

### Tests

### Tests Results

![alt results](imgs/result.png)

## Remarks


## Future research