import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Додавање елементи
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Стекот: " + stack);

        // Гледање на врвот
        System.out.println("Врвот е: " + stack.peek());

        // Вадење елементи
        System.out.println("Изваден елемент: " + stack.pop());
        System.out.println("Стекот после pop: " + stack);

        // Проверка дали е празен
        System.out.println("Дали е празен? " + stack.isEmpty());
    }
}
