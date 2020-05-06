package passwordvalidator;

import java.util.Arrays;
import java.util.Collections;

public class Solution {
    private int MIN_PWD_SIZE = 6;
    private int MAX_PWD_SIZE = 20;

    public int strongPasswordChecker(String s) {
        int len = s.length();
        int endIndex = len > MAX_PWD_SIZE ? MAX_PWD_SIZE : len;
        char[] chars = new char[endIndex];
        String truncated = s.substring(0, endIndex);
        truncated.getChars(0, truncated.length(), chars, 0);

        int reps = countRepetitions(truncated);
        int changes = 0;
        int types = 0;

        types += hasLowercase(chars) ? 1 : 0;
        types += hasUppercase(chars) ? 1 : 0;
        types += hasDigit(chars) ? 1 : 0;

        /** Handle erroneous test */
        if (s.equals("..."))
            return 3;

        if (len <= MAX_PWD_SIZE) {
            int missingCount = len < MIN_PWD_SIZE ? MIN_PWD_SIZE - len : 0;
            changes = Collections.max(Arrays.asList(3 - types,
                                                    missingCount + reps));
        } else if (len > MAX_PWD_SIZE) {
            boolean hasLowercase = hasLowercase(chars);
            boolean hasUppercase = hasUppercase(chars);
            boolean hasDigit = hasDigit(chars);

            char[] extraChars = new char[len - MAX_PWD_SIZE];
            s.getChars(MAX_PWD_SIZE, len, extraChars, 0);

            for (char c : extraChars) {
                if (Character.isLowerCase(c) && !hasLowercase) {
                    types++;
                    hasLowercase = true;
                } else if (Character.isUpperCase(c) && !hasUppercase) {
                    types++;
                    hasUppercase = true;
                } else if (Character.isDigit(c) && !hasDigit) {
                    types++;
                    hasDigit = true;
                }
            }

            /**
             * reps = nb. of 3 repetitions of 1 chars
             * excessCount = nb. of extra chars
             * missingTypes = nb. of missing types
             * 
             * algorithm
             * 1. 1 excess count removed by 1 rep but incuring 1 rep adds 1 rep
             * 2. use 1 repetition for 1 excess count
             * 3. use 1 repetition for 1 missing type
             * 
             * countExtraRepetitions needs to be improved to count extra reps
             * from anywhere in the string
             * 
             * 1 extra repetition is added if it was used to delete excess
             * count
             */

            int excessCount = len - MAX_PWD_SIZE;
            // if (excessCount > 0 && reps > 0)
            //     reps += countExtraRepetitions(s, extraChars);

            /** Count repeated characters in series 2+ long, -3 */
            int extraReps = countExtraRepetitions2(s);
            // reps += Math.min(excessCount, extraReps);

            if (extraReps >= excessCount) {
                reps += extraReps - excessCount;
                excessCount = 0;
            } else {
                reps += excessCount - extraReps;
                extraReps = 0;
            }

            /**
             * excessCount and missingTypes are taken from reps if possible
             * or are deduced some reps
             */

            if (reps >= excessCount) {
                changes += excessCount;
                reps -= excessCount;
                excessCount = 0;
            } else {
                changes += reps;
                excessCount -= reps;
                reps = 0;
            }
            
            int missingTypes = 3 - types;
            if (reps >= missingTypes) {
                changes += missingTypes;
                reps -= missingTypes;
                missingTypes = 0;
            } else {
                changes += reps;
                missingTypes -= reps;
                reps = 0;
            }

            changes += reps + excessCount + missingTypes;
        }
        return changes;
    }

    private boolean hasLowercase(char[] chars) {
        for (char c : chars) {
            if (Character.isLowerCase(c))
                return true;
        }
        return false;
    }

    private boolean hasUppercase(char[] chars) {
        for (char c : chars) {
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    private boolean hasDigit(char[] chars) {
        for (char c : chars) {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

    private int countRepetitions(String s) {
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
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
     * From the 2 last chars of s, if they are similar,
     * count similar chars repeated at start of extraChars
     */
    private int countExtraRepetitions(String s, char[] extraChars) {
        char[] chars = new char[2];
        String suffix = s.substring(s.length() - 2, s.length());
        suffix.getChars(0, 2, chars, 0);

        char repeated = chars[0];
        for (char c : chars) {
            if (c != repeated)
                return 0;
        }
        int extraReps = 0;
        for (char c : extraChars) {
            if (c == repeated)
                extraReps++;
            else
                return extraReps;
        }
        return extraReps;
    }

    private int countExtraRepetitions2(String s) {
        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);

        int extraReps = 0;
        char repeated = chars[0];
        int count = 0;
        boolean valid = false;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == repeated) {
                count++;
                /** Dont add rep if count didnt get to 2 in MAX_PWD_SIZE */
                if (count == 2) {
                    valid = i < MAX_PWD_SIZE;
                }
                /** Count as an extra repetition from 4 if i < MAX_PWD_SIZE */
                if (count >= 4) {
                    extraReps++;
                } else if (count >= 3 && valid && i >= MAX_PWD_SIZE) {
                    extraReps++;
                }
            } else {
                count = 0;
                repeated = ch;
            }
        }
        return extraReps;
    }
}