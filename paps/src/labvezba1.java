
import java.util.*;
public class labvezba1 {

    public static void Mesti(ArrayList<Integer> array, Integer k) {
            ArrayList<Integer> newarray = new ArrayList<>();
        for(int j = 1; j <= k; j++) {
            newarray.add(array.get((array.size() - j)));
        }
        for(int i = 1; i <= k; i++) {
            array.remove(array.size() - 1);
        }
        for(int i = 0; i < newarray.size(); i++) {
            array.addFirst(newarray.get(i));
        }
    }
    public static void main() {
        Scanner input = new Scanner(System.in);
    Integer n = input.nextInt();


    ArrayList<Integer> array = new ArrayList<>();
    for(int i = 0; i < n; i++) {
            array.add(input.nextInt());
         }

        Integer k = input.nextInt();
        for(int i = 0; i < n; i++) {
            System.out.print(array.get(i));
            if (i < n -1) {
                System.out.print("<->");
            }
        }
        Mesti(array, k);
        System.out.println();
        for(int i = 0; i < n; i++) {
            System.out.print(array.get(i));
            if (i < n -1) {
                System.out.print("<->");
            }
        }
    }
}
