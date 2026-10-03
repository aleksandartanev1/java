import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer n = sc.nextInt();

        Map<Integer, Integer> newmap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int number = sc.nextInt();
            newmap.put(number, newmap.getOrDefault(number, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(newmap.entrySet());
        for (int i = 0; i < entries.size(); i++) {
            Map.Entry<Integer, Integer> entry = entries.get(i);
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
