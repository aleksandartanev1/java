import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class zadacha3_hash {
    public static void main(String[] args) {
        /*Во рамки на една хуманитарна организација,потребно е да се направи статистика за крвните групикои се на располагање за донација,
         и од коидонатори. Подгрупите А1+, А2+ припаѓаат на крвнагрупа А+, додека А1- , А2- припаѓаат на група А-.

Влез: Во првиот ред од влезот е даден бројот напарови N, а во секој нареден ред се дадени паровите(донатор, крвна група).

Излез: Да се испечати по колку донатори има одсекоја крвна група согласно внесените податоци */

        /* Влез:
5
Alek A1+
Dejan B-
Sandra A+
Trajce 0+
Rebeka A1-
Излез:
A+ = 2
B- = 1
0+ = 1
A- = 1 */

        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        inp.nextLine();

        Map<String, Integer> types = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = inp.next();
            String key = inp.next().replaceAll("[1-2]", "");
            types.put(key, types.getOrDefault(key, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : types.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }
}
