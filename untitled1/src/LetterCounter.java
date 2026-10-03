import java.lang.runtime.ObjectMethods;
import java.util.*;

public class LetterCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine(); // чита збор

        Map<Character, Integer> lettercount = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            lettercount.put(letter, lettercount.getOrDefault(letter, 0) + 1);
        }


        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(lettercount.entrySet());
        for(int i = 0; i < entries.size(); i++) {
            Map.Entry<Character, Integer> entry = entries.get(i);
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
