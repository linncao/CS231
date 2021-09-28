public class ExceptionHandler {
    public static void main(String[] args) {
        System.out.println("start-of-main ");
        // try {
        //     foo();
        // } catch (Exception e) {
        //     System.out.println("Oh, yeah");
        // }
        foo();
        System.out.println("end-of-main ");
    }

    public static void foo() {
        try {
            System.out.println("start-of-foo ");
            bar();
            System.out.println("end-of-foo ");
        }
        catch (ArithmeticException ex) {
            System.out.println("Oh, yeah");
        }
    }

    public static void bar() {
        System.out.println("start-of-bar ");
        // try {
        //     int x = 3 / 0;
        // }
        // catch (ArithmeticException ex) {
        //     System.out.println("Oh, yeah");
        // }
        int x = 3 / 0;
        System.out.println("end-of-bar ");
    }
}

/*
 2. a. start-of-main
       start-of-foo
       start-of-bar
       Oh, yeah
       end-of-main
    b. I added try-catch block in the bar method
    c. I removed the try-catch block in the main method and added the block into the foo method
    d. We create classes of Exceptions we need an exception type that is not included in the Java platform.
       We can also do catches only for the exceptions that we expect.
 */