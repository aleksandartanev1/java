import java.util.*;

public class zadacha6_lab_hash {
    static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        inp.nextLine();
        Map<String, Set<String>> wizards = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String line = inp.nextLine();
            String parts[] = line.split(" ");
            wizards.putIfAbsent(parts[0], new HashSet<>());
            wizards.get(parts[0]).add(parts[1]);
        }

        int m = inp.nextInt();
        inp.nextLine();
        for(int i = 0; i < m; i++) {
            String line = inp.nextLine();
            String parts[] = line.split(" ");
            if(wizards.get(parts[0]).contains(parts[1]) && wizards.get(parts[0]).contains(parts[2]) && wizards.get(parts[0]).contains(parts[3])) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
