import java.util.Scanner;
import java.util.*;
import java.util.Stack;


public class asd {
    public static boolean isValid(List<String> lista) {
        Stack<String> stack = new Stack<>();
        for (String l : lista) {
            if (!l.startsWith("end")) {
                stack.push(l);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String krati = l.substring(3);
                if (!krati.equals(stack.pop())) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> lista = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.equals("x")) {
                break;
            }
            lista.add(line);
        }
        if (isValid(lista)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}