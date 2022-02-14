

public class App {
    public static void main(String[] args) throws Exception {
        test_cube();
    }

    public static double cube(double x) {
        double ans = x * x * x;
        return ans;
    }

    public static void test_cube() {
        double actual = cube(5.0);
        double expected = 125.0;
        System.out.println("test_cube:");
        if (actual == expected) {
            System.out.println("Success: actual = " + actual);
        } else {
            System.out.println("Failure: actual = " + actual + ", but expected = " + expected);
        }
    }

    /**
     * Tests if a string is a palindrome when whitespace, punctuation, and case are ignored.
     * 
     * For example, "A man, a plan, a canal -- Panama!" qualifies as a palindrome.
     * 
     * @param s
     * @return true if s is a palinddrome when whitespace, punctuation, and case are ignored.
     */
    public static boolean isPalindromeIngoringPunctuationAndPunctuation(String s) {
        return false;
    }

}
