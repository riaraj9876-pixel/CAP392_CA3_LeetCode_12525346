class Solution {
    public boolean isNumber(String s) {

        s = s.trim();

        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenE = false;
        boolean digitAfterE = true;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch >= '0' && ch <= '9') {
                seenDigit = true;

                if (seenE) {
                    digitAfterE = true;
                }
            }

            else if (ch == '.') {

                if (seenDot || seenE) {
                    return false;
                }

                seenDot = true;
            }

            else if (ch == 'e' || ch == 'E') {

                if (seenE || !seenDigit) {
                    return false;
                }

                seenE = true;
                digitAfterE = false;
            }

            else if (ch == '+' || ch == '-') {

                if (i > 0 &&
                    s.charAt(i - 1) != 'e' &&
                    s.charAt(i - 1) != 'E') {

                    return false;
                }
            }

            else {
                return false;
            }
        }

        return seenDigit && digitAfterE;
    }
}
