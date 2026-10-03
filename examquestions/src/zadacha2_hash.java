import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class zadacha2_hash {
    static class Lecture {
        String date;
        String time;
        String place;
        Integer fee;

        public Lecture(String date, String time, String place, Integer fee) {
            this.date = date;
            this.time = time;
            this.place = place;
            this.fee = fee;
        }
    }

    public static void main(String[] args) {
        /*На еден светски познат предавач секојдневно му пристигнуваатпонуди да држи предавања.
         За секоја понуда се дадени датуми,време на почеток, градот и износот на хонорарот за предавањето(во долари).
          Ваша задача е за даден датум да го прикажетепредавањето кое би му донело најголема заработка напредавачот.
           Доколку нема понуди за дадениот датум да сеиспечати „No offers”.
Влез: Во првиот ред од влезот е даден бројот на понуди, а во секојнареден ред се дадени: датумот и времето на предавањето(формат dd/mm/yyyyhh:mm),
градот во кој ќе се одржувапредавањето и износот на хонорарот.
 Во последниот ред е дадендатумот за кој треба да испечатите која понуда е најдобра за тојдатум.

Излез: Деталите на понудата за тој датум. */

        /* Влез:
7
27/01/2016 14:00 NewYork 6000
28/01/2016 08:00 Paris 3000
28/01/2016 14:00 Munich 5000
27/01/2016 09:00 Beijing 8000
27/01/2016 08:00 Seattle 4000
28/01/2016 09:00 SaltLakeCity 10000
28/01/2016 09:00 Lagos 12000
27/01/2016
Излез:
09:00 Beijing 8000
         */

        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        inp.nextLine();

        Map<String, Lecture> predavanja = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Lecture lec = new Lecture(inp.next(), inp.next(), inp.next(), inp.nextInt());
            Lecture existing = predavanja.get(lec.date);
            if (existing == null || lec.fee > existing.fee) {
                predavanja.put(lec.date, lec);
            }

        }
        inp.nextLine();
        String datum = inp.nextLine();
        Lecture best = predavanja.get(datum);
        if(best == null) {
            System.out.println("NEMA");
        } else {
            System.out.println(best.time + " " +best.place + " " + best.fee);
        }


    }
}
