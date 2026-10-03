import java.util.*;
static class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    public E getElement() {
        return element;
    }

    public SLLNode<E> getSucc() {
        return succ;
    }
}
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

public static void main(String[] args) {
   /* Во Заводот на статистика се прави ново истражувањекаде што се открива бројот на луѓе родени во секојмесец.
    Ваша задача е за даден месец да прикажетеколку луѓе се родени во тој месец.

    Влез: Во првиот ред од влезот е даден бројот на луѓеN, а во секој нареден ред е даден датумот на раѓање.
    Во последниот ред е даден месецот за кој треба да сеприкаже бројот на луѓе родени во тој месец.

    Излез: Број на луѓе кои се родени во тој месец.Доколку нема луѓе родени во тој месец да се испечати„Empty”. */

    /*Пример
Влез:
4
20.7.1976
16.7.1988
18.7.1966
5.6.1988
7
Излез:
3 */

    Scanner inp = new Scanner(System.in);
    int n = inp.nextInt();
    inp.nextLine();

    Map<String, Integer> birthdays = new HashMap<>();

    for(int i = 0; i < n; i++) {
        String entry = inp.nextLine();
        String parts[] = entry.split("\\.");
        birthdays.put(parts[1], birthdays.getOrDefault(parts[1], 0) + 1);
    }

    String query = inp.nextLine();
    System.out.println(birthdays.getOrDefault(query, 0));



}