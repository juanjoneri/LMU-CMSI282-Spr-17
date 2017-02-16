import java.math.BigInteger;

public class MathMethods {

    public static BigInteger factorial (long n) {
        if (n < 0) throw new IllegalArgumentException("Cannot compute Factorial of negative numbers");
        if (n < 2) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(factorial(n-1));
    }

    public static BigInteger fibonacci (int n) {
        if (n < 0) throw new IllegalArgumentException("Cannot compute Fibonacci of negative numbers");
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger fib_1 = BigInteger.ZERO;
        BigInteger fib_2 = BigInteger.ONE;
        BigInteger temp = BigInteger.ONE; // BigInteger has no constructor without parameters

        for (int i = 2; i <= n ; i++) {
            temp = fib_1.add(fib_2);
            fib_1 = fib_2;
            fib_2 = temp;
        }
        return fib_2;
    }

    public static long gcd (long m, long n) {
        m = Math.abs(m); n = Math.abs(n);
        if (n == 0) return m;
        return gcd(n, m%n);
    }

    public static long lcm (long m, long n) {
        return (m * n)/gcd(m, n);
    }

    public static double poly (double x, double[] coeff) {
        double result = 0;
        for (int i = coeff.length - 1; i > 0; i --) {
            result += coeff[i];
            result *= x;
        }
        return result + coeff[0];
    }

    public static double power (double x, int n) {
        if (n < 0) return power(1/x, -n); //inverse
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) return power(x*x, n/2);
        else return x * power(x*x, (n-1)/2);

    }

    public static double root (int n, double x, double epsilon) {
        if (x < 0 && n % 2 == 1) throw new IllegalArgumentException("Cannot compute even root of negative numbers");

        boolean isPositive = x > 0;
        x = Math.abs(x);

        if (x == 1) return isPositive ? 1 : -1;

        double lb = 0;
        double rb = 0;
        if (x < 1) {
            lb = x; rb = 1;
        } else {
            lb = 0; rb = x;
        }

        boolean done = false;
        double avg = (lb + rb)/2;
        double xo; // the current value i am evaluating
        while (!done) {
            avg = (lb + rb)/2;
            xo = power(avg, n);
            done = Math.abs(xo - x) < epsilon;
            if (!done) {
                if (xo > x) rb = avg;
                else lb = avg;
            }
        }

        return isPositive ? avg : -avg;
    }

    public static double sqrt (double x, double epsilon) {
        return root(2, x, epsilon);
    }

    public static String instructions () {
        String[] functions = {"factorial","fibonacci","gcd","lcm","poly","power","root", "sqrt"};
        String instruction =  " # This is how you should use this program : \n";
        instruction += " > java MathMethods [function] [parameters]...\n\n";
        instruction += " ## Allowed functins are: \n";
        for (String i : functions) {
            instruction += " - " + i + "\n";
        }
        return instruction;
    }

    public static void main (String[] args) {

        if (args.length >= 2) {
            String operation = args[0];

            switch (operation) {
               case "factorial":  System.out.println(factorial(Integer.parseInt(args[1])));
                    break;
               case "fibonacci":  System.out.println(fibonacci(Integer.parseInt(args[1])));
                    break;
               case "gcd":  System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
                    break;
               case "lcm":  System.out.println(lcm(Long.parseLong(args[1]), Long.parseLong(args[2])));
                    break;
               case "poly":
                    double[] coeff = new double[args.length - 2];
                    for (int i = 2; i < args.length; i ++) {
                        coeff[i-2] = Double.parseDouble(args[i]);
                    }
                    System.out.println(poly(Double.parseDouble(args[1]), coeff));
                    break;
               case "power":  System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
                    break;
               case "root":  System.out.println(root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                    break;
               case "sqrt":  System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
                    break;
               default: System.out.println(instructions());
                    break;
           }
       } else {
           System.out.println(instructions());
       }

    }
}
