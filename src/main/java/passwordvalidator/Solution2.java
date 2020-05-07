package passwordvalidator;

import java.util.Arrays;
import java.util.Collections;

/**
 * countUpdatedMissingTypes:
 * Implementation pitfall: counting the number of missingTypes that are
 * left to update instead of counting the number of missingTypes that
 * will be updated.
 * 
 * strongPasswordChecker:
 * Implementation pitfall: nbRepetitionsChars instead of nbRepetitions
 * when returning for cases with repetitions
 * 
 * Why: ?
 */
public class Solution2 {
    private static int MIN_PWD_SIZE = 6;
    private static int MAX_PWD_SIZE = 20;

    public int strongPasswordChecker(String s) {
        int nbExcessChars = countExcessChars(s);
        int nbRepetitions = countRepetitions(s);
        int nbMissingTypes;

        if (s.length() <= MAX_PWD_SIZE) {
            nbMissingTypes = countMissingTypes(s);
        } else {
            nbMissingTypes = countMissingTypes(s.substring(0, MAX_PWD_SIZE));
        }

        /** Is there excess characters? */
        if (nbExcessChars > 0) {
            /** Is there repetitions? */
            if (nbRepetitions > 0) {
                // int nbRepetitionChars = countRepetitionChars(s);
                // int nbUpdatedMissingTypes = countUpdatedMissingTypes(nbRepetitionChars,
                //                                                      nbExcessChars,
                //                                                      nbMissingTypes);
                // int nbinsertedExcessChars = countInsertedExcessChars(nbRepetitionChars,
                //                                                      nbExcessChars);
                // int nbCharsToBeRemoved = Math.max(nbExcessChars, countLongTailCorrections(s, nbinsertedExcessChars));
                // int nbTotalRepetitions = countRepetitions(s.substring(0, MAX_PWD_SIZE + nbinsertedExcessChars));

                int nbGlobalMissingTypes = countMissingTypes(s);
                int changes = nbRepetitions + nbExcessChars + nbMissingTypes;

                /** group corrections for: repetition & excess char & missing type */
                changes -= Math.min(nbRepetitions, Math.min(nbExcessChars, nbMissingTypes - nbGlobalMissingTypes));

                /** group corrections for: repetition & missing type */
                changes -= Math.min(nbMissingTypes, nbRepetitions);
                int nbRepetitionsRemaining = nbRepetitions - Math.min(nbMissingTypes, nbRepetitions);

                /** group corrections for: repetition & excess char */
                changes -= Math.min(nbExcessChars, nbRepetitionsRemaining);
                int nbFixedExcessChars = Math.min(nbExcessChars, nbRepetitionsRemaining);

                /**
                 * when grouping excess char and repetition corrections
                 * we don't account for a new repetition caused by the resulting correction
                 * 
                 * Ex1:
                 * input: "1234567890123456Baaaaa"
                 * resulting correction: "1234567890123456Baaa"
                 * 
                 * Ex2:
                 * input: "aaaaaaaaaaaaaaaaaaaaa"
                 * resulting correction: "aaAaa1aabaabaabaabba"
                 * 
                 * the number of new repetitive chars added =
                 * {length of the repetition
                 *  from MAX_PWD_SIZE - 2
                 *  to s.length() - nbFixedExcessChars} - 2
                 * 
                 * The number of additional changes to correct these new repetitions
                 * is limited to the number of
                 * "group corrections for: repetition & excess char"
                 * That is: nbFixedExcessChars
                 */
                int addedRepetitions = countTrailingRepetitions(s.substring(0, s.length() - nbFixedExcessChars));
                changes += Math.min(nbFixedExcessChars, addedRepetitions);
                return changes;
            } else {
                int nbGlobalMissingTypes = countMissingTypes(s);
                return nbExcessChars + Math.min(nbMissingTypes, nbGlobalMissingTypes);
            }
        } else {
            /** Is there missing characters? */
            int nbMissingChars = countMissingChars(s);
            if (nbMissingChars > 0) {
                /** Is there repetitions? */
                if (nbRepetitions > 0) {
                    return nbMissingChars + nbMissingTypes;
                } else {
                    return Math.max(nbMissingChars, nbMissingTypes);
                }
            } else {
                /** Is there repetitions? */
                if (nbRepetitions > 0) {
                    return Math.max(nbMissingTypes, nbRepetitions);
                } else {
                    return nbMissingTypes;
                }
            }
        }
    }

    /**
     * Returns an array of char found from (zero based) beginIndex to endIndex
     * @param s - the string to take characters from
     * @param beginIndex - the index of the first character to include
     * @param endIndex - the index of the last character (not included)
     * @return
     */
    private static char[] charSubstring(String s,
                                        int beginIndex, int endIndex) {
        char[] chars = new char[endIndex - beginIndex];
        s.getChars(beginIndex, endIndex, chars, 0);
        return chars;
    }

    /**
     * Returns an integer representing the number of chars to insert in 
     * the string for it to have a length of MIN_PWD_SIZE
     * to have at least 
     * @param s - string to count characters from
     * @return
     */
    private static int countMissingChars(String s) {
        return s.length() < MIN_PWD_SIZE ? MIN_PWD_SIZE - s.length() : 0;
    }

    /**
     * Return the number of missing character types from the string s
     * @param s - string to count missing character types from
     * @return
     */
    private static int countMissingTypes(String s) {
        char[] chars;
        chars = charSubstring(s, 0, s.length());
        int types = 0;
        types += hasLowercase(chars) ? 1 : 0;
        types += hasUppercase(chars) ? 1 : 0;
        types += hasDigit(chars) ? 1 : 0;
        return 3 - types;
    }

    private static boolean hasLowercase(char[] chars) {
        for (char c : chars) {
            if (Character.isLowerCase(c))
                return true;
        }
        return false;
    }

    private static boolean hasUppercase(char[] chars) {
        for (char c : chars) {
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    private static boolean hasDigit(char[] chars) {
        for (char c : chars) {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

    /**
     * Count the number of time 3 repeated characters are found in the string
     * The repetitions are only counter within the index 0 and MAX_PWD_SIZE
     * not included
     * @param s - the string to count the repetitions from
     * @return
     */
    private static int countRepetitions(String s) {
        char[] chars;
        chars = charSubstring(s, 0, s.length());
        int reps = 0;
        char symbol = chars.length > 0 ? chars[0] : 0;
        int count = 0;
        for (char c : chars) {
            if (symbol == c) {
                count++;
                if (count == 3) {
                    count = 0;
                    reps++;
                }
            } else {
                symbol = c;
                count = 1;
            }
        }
        return reps;
    }

    /**
     * Count the number of characters above the index (zero based)
     * MAX_PWD_SIZE - 1
     * @param s - the string to count the number of character above the index
     * @return
     */
    private static int countExcessChars(String s) {
        return s.length() > MAX_PWD_SIZE ? s.length() - MAX_PWD_SIZE : 0;
    }

    /**
     * Verifies if the string s has 3 repeated characters right before the
     * MAX_PWD_SIZE index (zero based) not included.
     * @param s - the string to verify the trailing repetition from
     * @return
     */
    private static boolean hasTrailingRepetition(String s) {
        if (s.length() < MAX_PWD_SIZE)
            return false;
        char[] chars = charSubstring(s, MAX_PWD_SIZE - 3, MAX_PWD_SIZE);
        for (char c : chars) {
            if (c != chars[0])
                return false;
        }
        return true;
    }

    /**
     * Count the cumulated length of inner repetitions starting from the 3d
     * character in each repetition, within the MAX_PWD_SIZE
     * E.g: aaa3 has 2 1 inner repetition and aaaaa has 3 inner repetitions.
     * 
     * Corresponds to R in solution notes
     * 
     * @param s - the string to count the inner repetitions from
     * @return
     */
    private static int countRepetitionChars(String s) {
        char[] chars;
        
        if (s.length() <= MAX_PWD_SIZE) {
            chars = charSubstring(s, 0, s.length());
        } else {
            chars = charSubstring(s, 0, MAX_PWD_SIZE);
        }
        int repetitionChars = 0;
        char symbol = chars.length > 0 ? chars[0] : 0;
        int count = 0;
        for (char c : chars) {
            if (symbol == c) {
                count++;
                if (count >= 3) {
                    repetitionChars++;
                }
            } else {
                symbol = c;
                count = 1;
            }
        }
        return repetitionChars;
    }

    /**
     * Returns the number of repeated characters, starting from the 3d
     * character in each repetition, that will be deleted and cede their
     * spot to 1 extra character each.
     * 
     * Corresponds to Y in notes
     * 
     * @param nbRepetitionChars
     * @param nbExcessChars
     * @return
     */
    private static int countInsertedExcessChars(int nbRepetitionChars,
                                                int nbExcessChars) {
        return Math.min(nbRepetitionChars, nbExcessChars);
    }

    /**
     * Returns the number of missing types that will be added by updating
     * one of the repeated characters, starting from the 3d character in
     * each repetition, that will not be used to remove excess count.
     * 
     * Corresponds to T in notes
     * 
     * @param nbRepetitionChars
     * @param excessChars
     * @param missingTypes
     * @return
     */
    private static int countUpdatedMissingTypes(int nbRepetitionChars,
                                                int excessChars,
                                                int missingTypes) {
        int updatedMissingTypes = 0;
        if (nbRepetitionChars >= excessChars) {
            updatedMissingTypes = Math.min(missingTypes,
                                           nbRepetitionChars - excessChars);
        }
        return updatedMissingTypes;
    }

    /**
     * Count the number of repetitions present in the inserted excess chars.
     * The short tail corrections consider the excess characters to not cause
     * a repetition with the characters from the original string.
     * 
     * Corresponds to K1 in notes
     * 
     * @param s - the string to count repetitions present in the inserted chars
     * @param nbinsertedExcessChars
     * @return
     */
    private static int countShortTailCorrections(String s,
                                                 int nbinsertedExcessChars) {
        if (s.length() <= MAX_PWD_SIZE)
            return 0;
        return countRepetitions(s.substring(s.length() - nbinsertedExcessChars,
                                            s.length()));
    }

    /**
     * Count the number of repetitions present in the inserted excess chars.
     * The long tail corrections consider the excess characters to cause
     * repetitions with the 2 last characters from the original string also.
     * 
     * Corresponds to K2 in notes
     * 
     * @param s - the string to count repetitions present in the inserted chars
     * @param nbinsertedExcessChars
     * @return
     */
    private static int countLongTailCorrections(String s,
                                                int nbinsertedExcessChars) {
        if (s.length() <= MAX_PWD_SIZE)
            return 0;
        return countRepetitions(s.substring(MAX_PWD_SIZE - 2, MAX_PWD_SIZE + nbinsertedExcessChars));
    }

    /**
     * Return the length -2 of the repetition from the index MAX_PWD_SIZE - 2
     * @param s
     * @return
     */
    private static int countTrailingRepetitions(String s) {
        if (s.length() < MAX_PWD_SIZE)
            return 0;
        int count = 0;
        char[] chars = charSubstring(s, MAX_PWD_SIZE - 2, s.length());
        for (char c : chars) {
            if (c == chars[0])
                count++;
            else
                return count > 2 ? count - 2 : 0;
        }
        return count - 2;
    }
}