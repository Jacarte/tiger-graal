import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactorialTest {
    private Context context;
    private Value factorial;

    @Before
    public void initEngine() throws Exception {

    }

    @Test
    public void factorial1() throws Exception {

        context = Context.create();
        // @formatter:off
        System.out.println(context.eval("tiger", "let var start:=10 var en:=10 function fac(n)=if n<1 then 1 else n*fac(n-1) in for i:=0 to 10 do" +
                " (start:=nano_time(); fac(30); en:=nano_time(); print((en-start)/1000000)) end"
        ));
        // @formatter:on
    }

    long fib(int n){
        if(n < 2)
            return 1;

        return fib(n-1) + fib(n-2);
    }

    @Test
    public void fib(){
        long nano = System.nanoTime();
        fib(30);

        System.out.println((System.nanoTime() - nano)/1000000);
    }

}
