/*Задача 1. Бриши дупли
За дадена низа од N (1<=N<=50) природни броеви да се избришат дупликат
вредностите кои се jавуваат на соседни позии.
Односно доколку две и повеке вредности се една до друга во низата, да се остави само едно поjавување.

На пример низата 1, 2, 2, 2, 3, 2, 4, 4, 1 по обработката ´ке биде 1, 2, 3, 2, 4, 1.

Првиот броj од влезот е броjот на елементи во низата N, а потоа во секоj ред
се дадени броевите. */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class vezba4 {
    static void PrintList (ArrayList<Integer> array) {
        for(int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i));
            if(i < array.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    static void CutList (ArrayList<Integer> array) {
        for(int i = 0; i < array.size() - 1; i++) {
            if(array.get(i).equals(array.get(i+1))) {
                array.remove(i);
                i--;
            }
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

       CutList(array);
        PrintList(array);
    }

}
