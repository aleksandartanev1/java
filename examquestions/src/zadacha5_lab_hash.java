import java.util.HashMap;
import java.util.Scanner;

public class zadacha5_lab_hash {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        inp.nextLine();

        HashMap<String, Integer> mrezhi = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String line = inp.nextLine();
            String parts[] = line.split("\\.");
            String key = parts[0] + "." + parts[1] + "." + parts[2];
            mrezhi.put(key, mrezhi.getOrDefault(key,0) + 1);
        }

        int m = inp.nextInt();
        inp.nextLine();
        for(int i = 0; i < m; i++) {
            String line = inp.nextLine();
            String parts[] = line.split("\\.");
            String query = parts[0] + "." + parts[1] + "." + parts[2];

            System.out.println(mrezhi.getOrDefault(query,0));
        }

    }
}
