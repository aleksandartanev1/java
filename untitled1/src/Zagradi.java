public class Zagradi {

    public static boolean daliZagraditeSePravilni(String izraz) {
        LinkedStack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < izraz.length(); i++) {
            char c = izraz.charAt(i);
            if (c == ' ') continue;

            if ((c == '(') || (c == '{') || (c == '[')) {
                stack.push(c);

            } else if ((c == ')') || (c == '}') || (c == ']')) {
                if (stack.isEmpty()) return false;

                char t = stack.pop();
                if (!((t == '{' && c == '}')
                        || (t == '[' && c == ']') ||
                        (t == '(' && c == ')'))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String phrase = "s x {(s - a) x (s - b) x (s - c)";
//        String phrase = "s x (s - a) x s - b) x (s - c)";
        System.out.println(phrase + " ima "
                + (daliZagraditeSePravilni(phrase) ? "korektni" : "nekorektni")
                + " zagradi.");
    }
}
