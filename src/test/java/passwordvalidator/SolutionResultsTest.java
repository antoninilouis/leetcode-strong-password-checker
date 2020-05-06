package passwordvalidator;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SolutionResultsTest {
    private static Object invokeMethod(Method method, Object... args) {
        try {
            Solution2 s2 = new Solution2();
            return (Object) method.invoke(s2, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test public void testStrongPasswordCheckerEssentials() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("strongPasswordChecker",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "aaa111"));
        assertEquals(7, (int) invokeMethod(method, "aaaaaaaaaaaaaaaaaaaaa"));
        assertEquals(3, (int) invokeMethod(method, "1111111111"));
        assertEquals(6, (int) invokeMethod(method, "ABABABABABABABABABABABAB"));
        assertEquals(7, (int) invokeMethod(method, "..................!!!"));
        assertEquals(3, (int) invokeMethod(method, "pppppp1020304050607080"));
        assertEquals(3, (int) invokeMethod(method, "abababababababababaaa"));
        assertEquals(3, (int) invokeMethod(method, "ababababababababaaaaa"));
        assertEquals(3, (int) invokeMethod(method, "1234567890123456Baaaaa"));
        assertEquals(3, (int) invokeMethod(method, "10203040aaaaa50607080B"));
    }

    @Test public void testStrongPasswordCheckerEasy() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("strongPasswordChecker",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "abcdA1"));
        assertEquals(6, (int) invokeMethod(method, ""));
        assertEquals(1, (int) invokeMethod(method, "abcde1"));
        assertEquals(2, (int) invokeMethod(method, "abcdef"));
        assertEquals(2, (int) invokeMethod(method, "abcdefg"));
    }

    @Test public void testStrongPasswordCheckerEasyAlt() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("strongPasswordChecker",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "xyxyxyxyxyabcdefghij"));
        assertEquals(2, (int) invokeMethod(method, "xyxyxyxyxyabcdefghijA1"));
        assertEquals(1, (int) invokeMethod(method, "xyxyxyxyxyabcdefghiA1"));
    }

    @Test public void testStrongPasswordCheckerMedium() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("strongPasswordChecker",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "xyxyxyxyxyabcdefghA1"));
        assertEquals(3, (int) invokeMethod(method, "xyxyxyxyxyabcdefghA1aaa"));
        assertEquals(2, (int) invokeMethod(method, "xyxyxyxyxyabcdefgA1aaa"));
        assertEquals(2, (int) invokeMethod(method, "xyxyxyxyxyabcdefgaaaA1"));
    }

    @Test public void testStrongPasswordCheckerHard() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("strongPasswordChecker",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(3, (int) invokeMethod(method, "xyxyxyxyxyabcdefghhhaA1"));
        assertEquals(5, (int) invokeMethod(method, "xyxyxyxyxyabcdefghhhaAaa1"));
        assertEquals(5, (int) invokeMethod(method, "A1xyxyxyxyxycdefghhhaAaa1"));
    }
}
