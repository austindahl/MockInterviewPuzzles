public class AustinSolutions {
   
    public static void main(String[] args) throws Exception {
        test_isPalindromeIngoringPunctuationAndCase();
        test_find2ndLargest();
        test_findKthLargest();
        test_dutchSortWords();
        test_dutchSortInts();
        test_dutchSortIntsRef();
    }

    private void MightBeUseful() {
        char ch = 'A';
        boolean b1 = Character.isWhitespace(ch);
        boolean b2 = Character.isLetterOrDigit(ch);
        char lch = Character.toLowerCase(ch);
    }

    /**
     * Tests if a string is a palindrome when whitespace, punctuation, and case are ignored.
     * 
     * For example, "A man, a plan, a canal -- Panama!" qualifies as a palnindrome.
     * 
     * @param s
     * @return true if s is a palinddrome when whitespace, punctuation, and case are ignored.
     */
    public static boolean isPalindromeIngoringPunctuationAndCase(String s) {
        if (s.length() == 0) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        while (true) {
            char a = s.charAt(start);
            while (!Character.isLetterOrDigit(a) && (start < end)) {
                start++;
                a = s.charAt(start);
            }
            char b = s.charAt(end);
            while (!Character.isLetterOrDigit(b) && (start < end)) {
                end--;
                b = s.charAt(end);
            }
            if (start >= end) {
                return true;
            }
            a = Character.toLowerCase(s.charAt(start));
            b = Character.toLowerCase(s.charAt(end));
            if (a != b) {
                return false;
            }
            start++;
            end--;
        }
    }

    public static void test_isPalindromeIngoringPunctuationAndCase() {
        String[] strs = { "A man, a plan, a canal -- Panama!", "123 Could this be one? 321", "2@#$%w&&2&", "I!", "  $$"};
        boolean[] expecteds = { true, false, true, true, true };
        System.out.println("test_isPalindromeIngoringPunctuationAndCase:");
        for (int i = 0; i < strs.length; i++) {
            boolean actual = isPalindromeIngoringPunctuationAndCase(strs[i]);
            boolean expected = expecteds[i];
            System.out.print("Case " + i + " ");
            if (actual == expected) {
                System.out.println("Success: actual= " + actual);
            } else {
                System.out.println("Failure: actual = " + actual +", but expected = " + expected);
            }
        }
    }

    public static double find2ndLargest(double[] a) {
        if (a.length < 2) {
            throw new IllegalArgumentException("Array must have length of 2 or more.");
        }
        double largest = Double.NEGATIVE_INFINITY;
        double secondLargest = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > largest) {
                secondLargest = largest;
                largest = a[i];
            } else if (a[i] > secondLargest) {
                secondLargest = a[i];
            }
        }
        return secondLargest;
    }
    
    public static void test_find2ndLargest() {
        System.out.println("test_find2ndLargest:");
        double[] a = { 1.0, 2.0, 3.0, 6.0, 8.0, 7.0, 0.0, 9.0, 4.0, 5.0 };
        double actual = find2ndLargest(a);
        double expected = 8.0;
        if (actual == expected) {
            System.out.println("Success: actual= " + actual);
        } else {
            System.out.println("Failure: actual = " + actual +", but expected = " + expected);
        }
    }

    public static double findKthLargest(double[] a, int k) {
        double[] largest = new double[k];
        for (int i = 0; i < k; i++) {
            largest[i] = Double.NEGATIVE_INFINITY;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < k; j++) {
                if (a[i] > largest[j]) {
                    for (int n = k - 1; n > j; n--) {
                        largest[n] = largest[n - 1];
                    }
                    largest[j] = a[i];
                    break;
                }
            }
        }
        return largest[k - 1];
    }

    public static void test_findKthLargest() {
        System.out.println("test_findKthLargest:");
        double[] a = { 1.0, 2.0, 3.0, 6.0, 8.0, 7.0, 0.0, 9.0, 4.0, 5.0 };
        for (int i = 1; i < a.length; i++) {
            double actual = findKthLargest(a, i);
            double expected = 10.0 - i;
            System.out.print("Case " + i + " ");
            if (actual == expected) {
                System.out.println("Success: actual= " + actual);
            } else {
                System.out.println("Failure: actual = " + actual +", but expected = " + expected);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static <T> void swap(T[] a, int i, int j) {
        if (i == j) {
            return;
        }
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void dutchSortInts(int[] a) {
        int start = 0;
        int end = a.length - 1;
        int leftzerostart = start;
        boolean leftzero = false;
        while (start <= end) {
            while ((a[start] < 0) && (start < end)) {
                if (!leftzero) {
                    start++;
                } else {
                    swap(a, leftzerostart, start);
                    leftzerostart++;
                }
            }
            while ((a[end] > 0) && (start < end)) {
                end--;
            }
            if ((a[start] > 0) && (a[end] < 0)) {
                swap(a, start, end);
                if (leftzero) {
                    swap(a, leftzerostart, start);
                    leftzerostart++;
                }
                start++;
                end--;
            } else if ((a[start] > 0) && (a[end] == 0)) {
                if (!leftzero) {
                    leftzerostart = start;
                }
                swap(a, start, end);
                end--;
            } else if ((a[start] == 0) && (a[end] < 0)) {
                if (!leftzero) {
                    swap(a, start, end);
                } else {
                    swap(a, leftzerostart, end);
                    leftzerostart++;
                }
                start++;
            } else if ((a[start] == 0) && (a[end] == 0)) {
                if (!leftzero) {
                    leftzerostart = start;
                }
                start++;
            }
            leftzero = (a[leftzerostart] == 0);
        }
    }

    public static void test_dutchSortInts() {
        int[] a = {4, 0, -1, 0, -2, 3, 7, 0, -8, 0, -6};
        dutchSortInts(a);
        boolean success = true;
        int lastSignum = -1;
        for (int i: a) {
            int signum = Integer.signum(i);
            success = (lastSignum <= signum);
            if (!success) {
                break;
            }
            lastSignum = signum;
        }
        System.out.println("dutchSortInts:");
        if (success) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
    }

    public static void dutchSortIntsRef(int[] a) {
        int lo = 0;
        int mid = 0;
        int hi = a.length - 1;
        while (mid <= hi) {
            switch (Integer.signum(a[mid])) {
            case -1:
                swap(a, lo++, mid++);
                break;
            case 0:
                mid++;
                break;
            case 1:
                swap(a, mid, hi--);
                break;
            }
        }
    }

    public static void test_dutchSortIntsRef() {
        int[] a = {4, 0, -1, 0, -2, 3, 7, 0, -8, 0, -6};
        dutchSortIntsRef(a);
        boolean success = true;
        int lastSignum = -1;
        for (int i: a) {
            int signum = Integer.signum(i);
            success = (lastSignum <= signum);
            if (!success) {
                break;
            }
            lastSignum = signum;
        }
        System.out.println("dutchSortIntsRef:");
        if (success) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
    }
    
    public static void dutchSortWords(String[] a) {
        int numTwos = 0;
        int numThrees = 0;
        for (int i = 0; i < a.length; i++) {
            switch (a[i].length()) {
            case 2: 
                numTwos++;
                break;
            case 3:
                numThrees++;
                break;
            default:
                // do nothing
            }
        }

        int twoSpot = 0;
        int threeSpot = numTwos;
        int otherSpot = numTwos + numThrees;
        int i = 0;
        while (i < a.length) {
            switch (a[i].length()) {
            case 2:
                if (i < numTwos) {
                    i++;
                } else if (i == twoSpot) {
                    i++;
                    twoSpot++;
                } else {
                    swap(a, i, twoSpot++);
                }
                break;
            case 3:
                if ((i >= numTwos) && (i < numTwos + numThrees)) {
                    i++;
                } else if (i == threeSpot) {
                    i++;
                    threeSpot++;
                } else {
                    swap(a, i, threeSpot++);
                }
                break;
            default:
                if (i >= numTwos + numThrees) {
                    i++;
                } else if (i == otherSpot) {
                    i++;
                    otherSpot++;
                } else {
                    swap(a, i, otherSpot++);
                }
            }
        }
    }

    public static void test_dutchSortWords() {
        System.out.println("test_dutchSortWords:");
        String[] words = { "Now", "is", "the", "time", "for", "all", "good", 
            "men", "to", "come", "to", "the", "aid", "of", "their", "party" };
        dutchSortWords(words);
        boolean success = true;
        int lastLength = 0;
        for (String word: words) {
            int length = word.length();
            success = (length >= lastLength);
            if (!success) {
                break;
            }
            lastLength = length;
        }
        if (success) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
    }
}

