/*Задача 2. Додади средни вредности
За дадена низа од N (1<=N<=50) природни броеви мeгу секои два соседи да се
внесе нов елемент коj е (целоброен, заокружен) просек од двата соседи.

На пример низата 1, 3, 5, 6 по обработката ´ке биде 1, 2, 3, 4, 5, 6, 6.

Првиот броj од влезот е броjот на елементи во низата N, а потоа во секоj ред
се дадени броевите.
*/


import java.sql.Array;
import java.util.ArrayList;
import java.util.*;


public class vezba5 {
    static void PrintList(ArrayList<Integer> array) {
        for(int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i));
            if(i < array.size()-1)
                System.out.print(", ");
        }
        System.out.println();
    }


    static void AddAvgList(ArrayList<Integer> array) {
        for(int i = 0; i < array.size() - 1; i++) {
            int avg = (array.get(i) + array.get(i+1)) / 2;
            array.add(i+1, avg);
            i++;
        }
    }

    public static void main() {
        Integer size;
        Scanner input = new Scanner(System.in);
        size = input.nextInt();
        ArrayList<Integer> array = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            array.add(input.nextInt());
        }

        AddAvgList(array);
        PrintList(array);


    }


}
