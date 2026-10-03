import java.util.*;

public class lab5 {
    public static String prefix (String pr) {
      String parts[] =  pr.split("\\.");
      return parts[0] + "." + parts[1] + "." + parts[2];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, Integer> newmap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String ip = sc.nextLine();
            String network = prefix(ip);
            newmap.put(network, newmap.getOrDefault(network, 0) + 1);
        }

        int m = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < m; i++) {
            String ip = sc.nextLine();
            String network = prefix(ip);
            System.out.println(newmap.getOrDefault(network, 0));
        }

    }
}
