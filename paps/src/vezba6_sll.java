/*Дадена е еднострано поврзана листа чии што jазли содржат цели броеви.
За даден броj од тастатура, потребно е да се отстрани неговото последно поjавување
(да се избрише jазолот што го содржи броjот).

Влез: Во првата линиjа е даден броjот на елементи n. Во следните n линии
се дадени елементите на листата. Во последната линиjа е даден броjот коj треба
да се отстрани (неговото последно поjавување).

Излез: На излез треба да се испечатат jазлите на резултантната листа.

Пример.
Влез:
5
4
6
4
9
3
4
Излез:
4->6->9->3 */

import java.util.*;

public class vezba6_sll {


    public static void main() {
        Scanner input = new Scanner(System.in);
        Integer N = input.nextInt();

        SLL<Integer> list = new SLL<>();
        for(int i = 0; i < N; i++) {
            list.insertLast(input.nextInt());
        }
        Integer X = input.nextInt();

        SLLNode<Integer> node = list.getFirst();
        SLLNode<Integer> brisi = null;

        while(node != null) {
            if(node.element == X) {
                brisi = node;
            }
            node = node.succ;
        }

        while(brisi != null) {
            list.delete(brisi);
                    brisi = null;
        }

        System.out.println(list.toString());
    }
}
