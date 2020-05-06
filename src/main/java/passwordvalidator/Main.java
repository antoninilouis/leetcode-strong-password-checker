package passwordvalidator;

/**
 * Main2
 */
public class Main {
    public static void main(String[] args) {
        Solution2 s = new Solution2();

        // Cases not foreseen

        // System.out.println(s.strongPasswordChecker("aaa111"));
        // System.out.println(s.strongPasswordChecker("aaaaaaaaaaaaaaaaaaaaa"));
        // System.out.println(s.strongPasswordChecker("1111111111"));
        // System.out.println(s.strongPasswordChecker("ABABABABABABABABABABABAB"));
        // System.out.println(s.strongPasswordChecker("..................!!!"));
        // System.out.println(s.strongPasswordChecker("pppppp1020304050607080"));
        // System.out.println(s.strongPasswordChecker("abababababababababaaa"));
        // System.out.println(s.strongPasswordChecker("ababababababababaaaaa"));
        // System.out.println(s.strongPasswordChecker("1234567890123456Baaaaa"));
        System.out.println(s.strongPasswordChecker("10203040aaaaa50607080B"));

        // System.out.println("---");

        // System.out.println(s.strongPasswordChecker("abcdA1"));
        // System.out.println(s.strongPasswordChecker(""));
        // System.out.println(s.strongPasswordChecker("abcde1"));
        // System.out.println(s.strongPasswordChecker("abcdef"));
        // System.out.println(s.strongPasswordChecker("abcdefg"));

        // System.out.println("---");
        
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghij"));
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghijA1"));
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghiA1"));

        // System.out.println("---");

        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghA1"));
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghA1aaa"));
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefgA1aaa"));
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefgaaaA1"));

        // System.out.println("---");

        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghhhaA1"));
        // System.out.println(s.strongPasswordChecker("xyxyxyxyxyabcdefghhhaAaa1"));
        // System.out.println(s.strongPasswordChecker("A1xyxyxyxyxycdefghhhaAaa1"));
    }
}
