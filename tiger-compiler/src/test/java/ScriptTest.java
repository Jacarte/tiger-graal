import org.apache.commons.io.IOUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ScriptTest {
    private Context context;
    private Value factorial;

    @Test
    public void testTigerFibonacci() throws IOException {

        String fibo = IOUtils.toString(new FileInputStream("fibo.tiger"), "UTF-8");

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }

    @Test
    public void testTigerFactorial() throws IOException {

        String fibo = IOUtils.toString(new FileInputStream("factorial.tiger"), "UTF-8");

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }


    @Test
    public void test1TigerMandelbrot() throws IOException {

        String fibo = IOUtils.toString(new FileInputStream("mandelbrot.tiger"), "UTF-8");

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }


    @Test
    public void testSimpleAssignAndPrint() throws IOException {

        String fibo = "let var t:=1 in for a:= 1 to 10 do print(a) end";

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }




    @Test
    public void testnestedCycles() throws IOException {

        String fibo = IOUtils.toString(new FileInputStream("nested_while.tiger"), "UTF-8");


        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }

    @Test
    public void testNativeForCycles(){
        for(int i = 0; i < 1000; i++)
            for(int j = 0; j < 1000; j++)
                for(int z = 0; z < 50; z++)
                {

                }
    }


    @Test
    public void testFunctionDeclare() throws IOException {

        String fibo = "let function b(n)= print(2) in for a:= 1 to 10 do b(10) end";

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }

    @Test
    public void testRecursive() throws IOException {

        String fibo = "let function b(n)=if n < 1 then 1 else b(n-1)*n in for a:= 1 to 10 do print(b(10)) end";

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }


    @Test
    public void testFunctionInsideFunction() throws IOException {

        String fibo = "let function b(n)=let function c(r)=n + r in c(2*n) end in for a:= 1 to 10 do print(b(10)) end";

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }



    @Test
    public void testFib() throws IOException {

        String fibo = "let function fib(n)=(if n < 2 then 1 else fib(n - 1)+fib(n-2)) in for a:= 1 to 3 do print(fib(30)) end";

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }



    @Test
    public void testFor() throws IOException {

        String fibo = IOUtils.toString(new FileInputStream("for.tiger"), "UTF-8");

        context = Context.create();
        // @formatter:off
        Object result = context.eval("tiger", fibo
        );

    }

    @Test
    public void testNative(){
        Mandelbrot.main(new String[0]);
    }

    public static class Mandelbrot {
        public static int mandelbrot(final double size) {
            int sum = 0;
            int byte_acc = 0;
            int bit_num  = 0;
            int y = 0;
            while (y < size) {
                double ci = (2.0 * y / size) - 1.0;
                int x = 0;
                while (x < size) {
                    double zr   = 0.0;
                    double zrzr = 0.0;
                    double zi   = 0.0;
                    double zizi = 0.0;
                    double cr = (2.0 * x / size) - 1.5;
                    int z = 0;
                    int escape = 1;
                    while (z < 50) {
                        double tr = zrzr - zizi + cr;
                        double ti = 2.0 * zr * zi + ci;
                        zr = tr;
                        zi = ti;
// preserve recalculation
                        zrzr = zr*zr;
                        zizi = zi*zi;
                        if (zrzr + zizi > 4.0) {
                            escape = 0;
                            break;
                        }
                        z += 1;
                    }
                    byte_acc = (byte_acc << 1) | escape;
                    bit_num += 1;
// Code is very similar for these cases, but using separate blocks
// ensures we skip the shifting when it's unnecessary, which is most cases.
                    if (bit_num == 8) {
                        sum ^= byte_acc;
                        byte_acc = 0;
                        bit_num  = 0;
                    } else if (x == size - 1) {
                        byte_acc <<= (8 - bit_num);
                        sum ^= byte_acc;
                        byte_acc = 0;
                        bit_num  = 0;
                    }
                    x += 1;
                }
                y += 1;
            }
            return sum;
        }
        public static void warmup() {
            for (int n = 0; n < 10000; n++) {
                mandelbrot(10);
            }
        }
        public static boolean sample() {
            return mandelbrot(750) == 192;
        }
        public static void main(final String[] args) {
            if (!sample()) {
                throw new RuntimeException();
            }
            int iterations = 100;
            int warmup     = 0;
            int problemSize = 1000;
            if (args.length >= 1) {
                iterations = Integer.parseInt(args[0]);
            }
            if (args.length >= 2) {
                warmup = Integer.parseInt(args[1]);
            }
            if (args.length >= 3) {
                problemSize = Integer.parseInt(args[2]);
            }
            System.out.println("Overall iterations: " + iterations);
            System.out.println("Warmup  iterations: " + warmup);
            System.out.println("Problem size:       " + problemSize);
            while (warmup > 0) {
                mandelbrot(problemSize);
                warmup--;
            }
            while (iterations > 0) {
                long start = System.nanoTime();
                mandelbrot(problemSize);
                long elapsed = (System.nanoTime() - start) / 1000000;
                iterations--;
                System.out.print(
                        elapsed + ",");
                System.out.flush();
            }
            if (!sample()) {
                throw new RuntimeException();
            }
        }
    }

}
