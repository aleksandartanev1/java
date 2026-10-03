/*Задача 1. Просек на низа
За дадена низа од N (1<=N<=50) природни броеви, да се наjде броjот коj е наjблиску до нивниот просек. Ако постоjат два броjа со исто растоjание до просекот,
да се врати помалиот од нив. Во низата може да има дупликати.

На пример за низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5
= 3, што значи дека броjот коj треба да се врати и е наjблиску до просекот е 3.

За низата 1, 2, 3, 4, 5, 6 просекот е 3.5 и двата броjа 3 и 4 се на исто растоjание
од просекот. Точната вредност коjа треба да се врати е помалиот од нив, а тоа е
3.

Првиот броj од влезот е броjот на елементи во низата N, а потоа во секоj ред
се дадени броевите. */
import java.util.*;
import java.lang.Math;

public class vezba2 {

    public static int ReturnClosest(ArrayList<Integer> arr) {
        int sum = 0;
        for(int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }

        float average = (float) sum / arr.size();

        int closest = arr.get(0);
        double minDiff = Math.abs(closest - average);

        for (int i = 1; i < arr.size(); i++) {
            int current = arr.get(i);
            double diff = Math.abs(current - average);

            if (diff < minDiff || (diff == minDiff && current < closest)) {
                closest = current;
                minDiff = diff;
            }
        }

        return closest;
    }



    public static void main() {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < N; i++) {
        array.add(input.nextInt());
        }
        System.out.println(ReturnClosest(array));
    }


}


