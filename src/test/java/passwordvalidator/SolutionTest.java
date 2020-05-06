package passwordvalidator;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SolutionTest {
    private static Object invokeMethod(Method method, Object... args) {
        try {
            return (Object) method.invoke(null, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test public void testCharSubstringHappyCase() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("charSubstring",
                new Class[] { String.class, int.class, int.class });
        method.setAccessible(true);
        assertArrayEquals(new char[] { 'h', 'e', 'l', 'l', 'o' },
                          (char[]) invokeMethod(method, "hello", 0, 5));
    }

    @Test public void testCharSubstring() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("charSubstring",
                new Class[] { String.class, int.class, int.class });
        method.setAccessible(true);
        assertArrayEquals(new char[] { 'l', 'l' },
                          (char[]) invokeMethod(method, "hello", 2, 4));
    }

    @Test public void testCountMissingCharsShorter() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countMissingChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "hello"));
    }

    @Test public void testCountMissingCharsRightSize() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countMissingChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "123456"));
    } 
    
    @Test public void testCountMissingCharsLonger() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countMissingChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "1234567"));
    }

    @Test public void testCountMissingTypesLess() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countMissingTypes",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "ab12"));
    }

    @Test public void testCountMissingTypesRight() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countMissingTypes",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "ab12AB"));
    }

    @Test public void testCountMissingTypesMore() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countMissingTypes",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(3, (int) invokeMethod(method, "...&"));
    }

    @Test public void testCountRepetitionsSmall() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(4, (int) invokeMethod(method, "...naaa11111123433"));
    }

    @Test public void testCountRepetitionsNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, ".b.&klnaaiip"));
    }

    @Test public void testCountRepetitionsLarge() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(3, (int) invokeMethod(method, ".ampqi190c...&nnnnnnnnjod"));
    }

    @Test public void testCountRepetitionsLastIndex() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "012345678901aaabbbccc"));
    }

    @Test public void testCountExcessCharsNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countExcessChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "012345678901aaabbbcc"));
    }

    @Test public void testCountExcessCharsLot() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countExcessChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(10, (int) invokeMethod(method, "012345678901234567890123456789"));
    }

    @Test public void testCountExcessCharsLastIndex() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countExcessChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "012345678901aaabbbccc"));
    }

    @Test public void testHasTrailingRepetitionNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("hasTrailingRepetition",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(false, (boolean) invokeMethod(method, "012345678901aaabbbcc"));
    }

    @Test public void testHasTrailingRepetitionNoneHard() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("hasTrailingRepetition",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(false, (boolean) invokeMethod(method, "012345678901aaabbbccc"));
    }

    @Test public void testHasTrailingRepetitionEasy() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("hasTrailingRepetition",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(true, (boolean) invokeMethod(method, "012345678901aaabbccc"));
    }

    @Test public void testHasTrailingRepetitionHard() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("hasTrailingRepetition",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(true, (boolean) invokeMethod(method, "012345678901aaabbbbbbb"));
    }

    @Test public void testCountRepetitionCharsEasy() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitionChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "aaa"));
    }

    @Test public void testCountRepetitionCharsNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitionChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "aa12bb12nn12jj12kk12gg12ff12uu"));
    }

    @Test public void testCountRepetitionCharsHard() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countRepetitionChars",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(8, (int) invokeMethod(method, "1aaa2bbbb3ccccc2dddd"));
    }

    @Test public void testCountInsertedExcessChars() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countInsertedExcessChars",
                new Class[] { int.class, int.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, 1, 4));
    }

    @Test public void testCountInsertedExcessCharsAlt() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countInsertedExcessChars",
                new Class[] { int.class, int.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, 4, 1));
    }

    @Test public void testCountUpdatedMissingTypes() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countUpdatedMissingTypes",
                new Class[] { int.class, int.class, int.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, 1, 2, 2));
    }

    @Test public void testCountUpdatedMissingTypesAlt() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countUpdatedMissingTypes",
                new Class[] { int.class, int.class, int.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, 2, 1, 2));
    }

    @Test public void testCountShortTailCorrectionsNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countShortTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "aaa34567890123456789a", 1));
    }

    @Test public void testCountShortTailCorrectionsEasy() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countShortTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "aaaaa567890123456789aaa", 3));
    }

    @Test public void testCountShortTailCorrectionsMedium() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countShortTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "aaaaabbbbb8901234567aaaaaa", 6));
    }

    @Test public void testCountShortTailCorrectionsHard() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countShortTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "aaaaaabbbbb901234567baaaaab", 7));
    }

    @Test public void testCountLongTailCorrectionsNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countLongTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "01234567890123456xxxa", 1));
    }

    @Test public void testCountLongTailCorrectionsEasy() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countLongTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(1, (int) invokeMethod(method, "012345678901234aaaaaaaa", 3));
    }

    @Test public void testCountLongTailCorrectionsMedium() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countLongTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "012345678901aaaaaaaaaaaaaa", 6));
    }

    @Test public void testCountLongTailCorrectionsHard() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countLongTailCorrections",
                new Class[] { String.class, int.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "012345678901aaaaaaaabbbbbb", 6));
    }

    @Test public void testCountTrailingRepetitions() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countTrailingRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(2, (int) invokeMethod(method, "1234567890123456Baaaaa"));
    }

    @Test public void testCountTrailingRepetitionsNone() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countTrailingRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(0, (int) invokeMethod(method, "1234567890123456Baaabaaa"));
    }

    @Test public void testCountTrailingRepetitionsUnique() throws NoSuchMethodException,
                                                 SecurityException {
        Method method = Solution2.class.getDeclaredMethod("countTrailingRepetitions",
                new Class[] { String.class });
        method.setAccessible(true);
        assertEquals(3, (int) invokeMethod(method, "1234567890123456Bxxxxxxaxxxa"));
    }
}
