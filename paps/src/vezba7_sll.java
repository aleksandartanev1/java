/*Задача 2. Замени соседи
Дадена е еднострано поврзана листа чии што jазли содржат по еден природен
броj. Да се трансформира листата така што секоj соседен пар jазли ´ке си ги
заменат местата (првиот со вториот, па третиот со четвртиот итн...).

Влез: Во првата линиjа е даден броjот на елементи n. Во следните n линии
се дадени елементите на листата.

Излез: На излез треба да се испечатат jазлите на резултантната листа.
Пример.
Влез:
4
1
2
3
4
Излез:
2->1->4->3 */


import java.util.Scanner;

public class vezba7_sll {

    public static void main() {
        Scanner input = new Scanner(System.in);
        Integer N = input.nextInt();

        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < N; i++) {
            list.insertLast(input.nextInt());
        }

            SLLNode<Integer> X = list.getFirst(); // X e prviot jazol od listata
        while (X != null && X.succ != null) { // se dodeka x ne e null i naredniot jazol ne e null
           Integer temp = X.element; // temp e elementot na X
            X.element = X.succ.element;// elementot na X stanuva elementot na naredniot jazol
            X.succ.element = temp;// elementot na naredniot jazol stanuva temp
            X = X.succ.succ;// X stanuva na naredniot jazol negoviot narednik.
        }

    System.out.println(list.toString());
    }
}
