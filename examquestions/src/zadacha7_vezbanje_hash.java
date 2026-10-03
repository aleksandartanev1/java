import java.util.Scanner;

public class zadacha7_vezbanje_hash {
    static class MapEntry<K, E> {
        // Each MapEntry object is a pair consisting of a key
        // and a value (an arbitrary object).
        public K key;
        public E value;

        public MapEntry(K key, E val) {
            this.key = key;
            this.value = val;
        }

        public String toString() {
            return "<" + key + "," + value + ">";
        }
    }
    static class SLLNode<E> {
        public E element;
        public SLLNode<E> succ;

        public SLLNode(E elem, SLLNode<E> succ) {
            this.element = elem;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    static class CBHT<K, E> {

        // An object of class CBHT is a closed-bucket hash table, containing
        // entries of class MapEntry.
        private SLLNode<MapEntry<K, E>>[] buckets;

        @SuppressWarnings("unchecked")
        public CBHT(int m) {
            // Construct an empty CBHT with m buckets.
            buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
        }

        private int hash(K key) {
            // Translate key to an index of the array buckets.
            return Math.abs(key.hashCode()) % buckets.length;
        }

        public SLLNode<MapEntry<K, E>> search(K targetKey) {
            // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
            // Return a link to that node (or null if there is none).
            int b = hash(targetKey);
            SLLNode<MapEntry<K, E>> currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, E> currEntry = currNode.element;
                if (currEntry.key.equals(targetKey)) return currNode;
                else currNode = currNode.succ;
            }
            return null;
        }

        public void insert(K key, E val) {
            // Insert the entry <key, val> into this CBHT.
            // If entry with same key exists, overwrite it.
            MapEntry<K, E> newEntry = new MapEntry<>(key, val);
            int b = hash(key);
            SLLNode<MapEntry<K, E>> currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, E> currEntry = currNode.element;
                if (currEntry.key.equals(key)) {
                    // Make newEntry replace the existing entry ...
                    currNode.element = newEntry;
                    return;
                } else currNode = currNode.succ;
            }
            // Insert newEntry at the front of the SLL in bucket b ...
            buckets[b] = new SLLNode<>(newEntry, buckets[b]);
        }

        public void delete(K key) {
            // Delete the entry (if any) whose key is equal to key from this CBHT.
            int b = hash(key);
            SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
            while (currNode != null) {
                MapEntry<K, E> currEntry = currNode.element;
                if (currEntry.key.equals(key)) {
                    if (predNode == null) buckets[b] = currNode.succ;
                    else predNode.succ = currNode.succ;
                    return;
                } else {
                    predNode = currNode;
                    currNode = currNode.succ;
                }
            }
        }

        public String toString() {
            String temp = "";
            for (int i = 0; i < buckets.length; i++) {
                temp += i + ":";
                SLLNode<MapEntry<K, E>> curr = buckets[i];
                while (curr != null) {
                    temp += curr.element.toString() + " ";
                    curr = curr.succ;
                }
                temp += "\n";
            }
            return temp;
        }
    }

    static class Vraboten {
        String name;
        int age;

        Vraboten(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode() {
            return age * (int)name.charAt(0);
        }

        @Override
        public String toString() {
            return "<" +name+", "+age+">";
        }
        @Override
        public boolean equals(Object obj) {
            Vraboten other = (Vraboten) obj;
            return this.age == other.age && this.name.equals(other.name);
        }

    }

    static class Project {
        int time;
        int rate;


        Project(int time, int rate) {
            this.time = time;
            this.rate = rate;
        }

       public int paycheck() {
            return rate * time;
        };

        @Override
        public String toString() {
            return "<" +time+", "+rate+">";
        }
    }

    public static void main(String[] args) {
        CBHT<Vraboten, Project> map = new CBHT<>(10);
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        inp.nextLine();

        for(int i = 0; i < n; i++) {
            String name = inp.next();
            int age = inp.nextInt();
            int time = inp.nextInt();
            int rate = inp.nextInt();
            Vraboten nov = new Vraboten(name, age);
            Project von = new Project(time, rate);


            SLLNode<MapEntry<Vraboten, Project>> exist = map.search(nov);
            if(exist != null) {
                if(exist.element.value.paycheck() < von.paycheck()) {
                    map.insert(nov, von);
                }
            } else map.insert(nov, von);

        }


        System.out.println(map.toString());
    }


}
