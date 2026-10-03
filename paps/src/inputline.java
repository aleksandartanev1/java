import java.util.Scanner;
public class inputline {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");

        //reads the entre line
        String value = input.nextLine();
        System.out.println("Using nextLine(): " + value);

        //closing the scanner object
        input.close();


    }
}
