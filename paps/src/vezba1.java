//import java.util.*;
//public class vezba1 {
//    public static void main(String[] args) {
//        Scanner enter = new Scanner(System.in);
//        Integer n = enter.nextInt();
//
//        Scanner enter2 = new Scanner(System.in);
//        ArrayList<String> array = new ArrayList<>();
//        for(int i = 0; i < n; i++) {
//            array.add(enter2.nextLine());
//        }
//
//
//        System.out.println(array);
//    }
//}
import java.util.*;

public class vezba1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextLine());
        }

        sc.nextLine(); // read the unused integer

        // Print original list
        print(list);

        // Insert "Target" after every 1-character string
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 1) {
                list.add(i + 1, "Target");
                i++; // skip over inserted "Target"
            }
        }

        // Print modified list
        print(list);
    }

    static void print(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print("->");
        }
        System.out.println();
    }
}
