/*Задача 2. Промени низи
Нека се дадени две низи, кои треба да бидат со иста големина. Да се напише
функциjа коjа ´ке прави промени во двете низи така што доколку на дадена
позициjа тие имаат еднакви елементи, истите треба да се избришат и во двете
низи */

import java.util.*;
import java.lang.Math.*;

public class vezba3<E> {
    public void SameArrays(ArrayList<E> arr1,ArrayList<E> arr2) {
        if(arr1.size() != arr2.size()) {
            System.out.println("Dadenite nizi se razlicna golemina!");
        }

        for(int i = 0; i < arr1.size(); i++) {
            if(arr1.get(i) == arr2.get(i)) {
                arr1.remove(i);
                arr2.remove(i);
            }
        }
    }

    public static void PrintList(ArrayList<Integer> arr) {
        for(int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i));
            if(i < arr.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }



    public static void main() {
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(4, 2, 7, 9, 6));


       vezba3<Integer> kraj = new vezba3<>();
        kraj.SameArrays(array1, array2);

        System.out.println("Array 1: ");
        PrintList(array1);
        System.out.println("Array 2: ");
        PrintList(array2);
    }
}
