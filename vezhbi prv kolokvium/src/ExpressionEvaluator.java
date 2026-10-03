import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        char previoussign = '+';
        int number = 0;
        for(int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if(Character.isDigit(character)) {
                number = number * 10 + (character - '0');
            }
            if(character == '+' || character == '*' || i == expression.length() - 1) {
                if (previoussign == '+') {
                    stack.push(number);
                } else if (previoussign == '*') {
                    stack.push(stack.pop() * number);
                }
                number = 0;
                previoussign = character;
            }
        }
        while(!stack.isEmpty()) {
            sum+=stack.pop();
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}