import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class zadacha4_hash {
    /* За различни градови дадени се мерењата на температурата (степени Целзиусови) во одредени временски интервали.
     Ваша задача е за даден град да се наjде наjтоплиот период од денот.
Влез: Во првиот ред од влезот е даден броjот на мерења 𝑁 (𝑁 <= 10000), а во секоj нареден ред е даден прво градот,
 потоа почеток на интервал, краj на интервал и температурата разделени со
празно место.
Во последниот ред е даден градот за коj треба да наjдете наjтопол период од денот и истиот
период да се испечати. Сложеноста на оваа операциjа треба да биде O(1).

Излез: Наjтоплиот период од денот за даден град. Да се испечати во следниов формат: G: HH:MM – XX:YY Z, каде што G е градот, HH:MM e почетокот на интервалот, XX:YY е краjот на интервалот, а Z e температурата во степени Целзиусови.
Пример:

Влез:
4
Ohrid,Macedonia 10:00 12:00 23.1
Skopje,Macedonia 09:00 10:30 24
Ohrid,Macedonia 12:00 13:00 25
Skopje,Macedonia 10:00 11:00 26.2
Ohrid,Macedonia

Излез:
Ohrid,Macedonia: 12:00 - 13:00 25.0*/

    static class Temperatura {
        String city;
        String starttime;
        String endtime;
        float temp;

        Temperatura(String city, String starttime, String endtime, float temp) {
            this.city = city;
            this.starttime = starttime;
            this.endtime = endtime;
            this.temp = temp;
        }

        @Override
        public String toString() {
            return city + ": " + starttime + " - " + endtime + " " + temp;
        }
    }

    static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        inp.nextLine();

        Map<String, Temperatura> temperaturi = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String line = inp.nextLine();
            String parts[] = line.split(" ");
            String city = parts[0];
            String starttime = parts[1];
            String endtime = parts[2];
            float temp = Float.parseFloat(parts[3]);

            Temperatura novatemp = new Temperatura(city,starttime,endtime,temp);

            Temperatura existing = temperaturi.get(novatemp.city);

            if(existing == null || novatemp.temp > existing.temp) {
                temperaturi.put(novatemp.city, novatemp);
            }
        }
        String barangrad = inp.nextLine();

        if(temperaturi.get(barangrad) != null) {
            Temperatura entry = temperaturi.get(barangrad);
            System.out.println(entry);
        } else  {
            System.out.println("NEMA");
        }
    }
}
