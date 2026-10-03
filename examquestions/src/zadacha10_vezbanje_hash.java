import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class zadacha10_vezbanje_hash {
    static class Spender {
        String name;
        String surname;
        int budget;
        String ip;
        String time;
        String city;
        int price;

        public Spender(String name, String surname, int budget, String ip, String time, String city, int price) {
            this.name = name;
            this.surname = surname;
            this.budget = budget;
            this.ip = ip;
            this.time = time;
            this.city = city;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        Map<String, Integer> mapata = new HashMap<>();
        Map<String, Spender> spenderlist = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = inp.next();
            String surname = inp.next();
            int budget = inp.nextInt();
            String ip = inp.next();
            String time = inp.next();
            String city = inp.next();
            int price = inp.nextInt();

            Spender bigspender = new Spender(name, surname, budget, ip, time, city, price);

            String parts[] = ip.split("\\.");
            String ipaddress = parts[0] + "." + parts[1] + "." + parts[2];

            String[] timestamp = time.split(":");
            int hours = Integer.parseInt(timestamp[0]);
            int minutes = Integer.parseInt(timestamp[1]);

            if (hours >= 12) {
                mapata.put(ipaddress, mapata.getOrDefault(ipaddress, 0) + 1);
                Spender existing = spenderlist.get(ipaddress);
                if (existing == null) {
                    spenderlist.put(ipaddress, bigspender);
                }
                if (existing != null) {
                    String[] existingtimestamp = existing.time.split(":");

                    int existinghours = Integer.parseInt(existingtimestamp[0]);
                    int existingminutes = Integer.parseInt(existingtimestamp[1]);
                    if (hours < existinghours) {
                        spenderlist.put(ipaddress, bigspender);
                    } else {
                        if (hours == existinghours) {
                            if (minutes < existingminutes) {
                                spenderlist.put(ipaddress, bigspender);
                            }
                        }
                    }
                }
            }
        }


        int m = inp.nextInt();
        for(int i = 0; i < m; i++) {
            String name = inp.next();
            String surname = inp.next();
            int budget = inp.nextInt();
            String ip = inp.next();
            String time = inp.next();
            String city = inp.next();
            int price = inp.nextInt();
            String parts[] = ip.split("\\.");
            String ipaddress = parts[0] + "." + parts[1] + "." + parts[2];
            System.out.println("IP network: "+ipaddress+" has the following number of users:");
            System.out.println(mapata.get(ipaddress));
            System.out.println("The user who logged on earliest after noon from that network is:");
            System.out.println(spenderlist.get(ipaddress).name +" "+ spenderlist.get(ipaddress).surname + " with salary "+spenderlist.get(ipaddress).budget + " from address "+spenderlist.get(ipaddress).ip + " who logged in at "+ spenderlist.get(ipaddress).time);
            System.out.println();
        }
    }
}