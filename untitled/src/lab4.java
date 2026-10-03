import java.util.*;
import java.util.NoSuchElementException;
public class lab4 {

    public static class SLLNode<E> {
        protected E element;
        protected SLLNode<E> succ;

        public SLLNode(E elem, SLLNode<E> succ) {
            this.element = elem;
            this.succ = succ;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }


    public static class StringQueue<E> implements Queue<E> {

        // Redicata e pretstavena na sledniot nacin:
        // length go sodrzi brojot na elementi.
        // Elementite se zachuvuvaat vo jazli dod SLL
        // front i rear se linkovi do prviot i posledniot jazel soodvetno.
        SLLNode<E> front, rear;
        int length;

        // Konstruktor ...

        public StringQueue() {
            clear();
        }

        public boolean isEmpty () {
            // Vrakja true ako i samo ako redicata e prazena.
            return (length == 0);
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        public int size () {
            // Ja vrakja dolzinata na redicata.
            return length;
        }

        @Override
        public boolean add(E e) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean offer(E e) {
            return false;
        }

        @Override
        public E remove() {
            return null;
        }

        @Override
        public E poll() {
            return null;
        }

        @Override
        public E element() {
            return null;
        }

        public E peek () {
            // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
            if (front == null)
                throw new NoSuchElementException();
            return front.element;
        }

        public void clear () {
            // Ja prazni redicata.
            front = rear = null;
            length = 0;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        public void enqueue (E x) {
            // Go dodava x na kraj od redicata.
            SLLNode<E> latest = new SLLNode<E>(x, null);
            if (rear != null) {
                rear.succ = latest;
                rear = latest;
            } else
                front = rear = latest;
            length++;
        }

        public E dequeue () {
            // Go otstranuva i vrakja pochetniot element na redicata.
            if (front != null) {
                E frontmost = front.element;
                front = front.succ;
                if (front == null)  rear = null;
                length--;
                return frontmost;
            } else
                throw new NoSuchElementException();
        }
        public SLLNode<E> getFrontNode() {
            return front;
        }


    }



    static boolean isValid(StringQueue<String> newqueue) {
        StringQueue<String> funqueue = new StringQueue<>();
        StringQueue<String> endqueue = new StringQueue<>();
        while (!newqueue.isEmpty()) {
            String word = newqueue.peek();
            if (word.startsWith("end")) {
                endqueue.enqueue(newqueue.dequeue());

            } else {
                funqueue.enqueue(newqueue.dequeue());

            }
        }

        if(funqueue.size() < endqueue.size() || funqueue.size() > endqueue.size()) {
            return false;
        }
            SLLNode<String> funtemp = funqueue.getFrontNode();
            int counter = 0;
            while (funtemp !=null) {
                SLLNode<String> endtemp = endqueue.getFrontNode();
                while (endtemp != null) {
                    if(endtemp.element.equals("end" + funtemp.element)) {
                        counter++;
                        break;
                    } else endtemp = endtemp.succ;
                }
                funtemp = funtemp.succ;
            }
        if(counter == funqueue.size()) {
            return true;
        } else return false;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringQueue<String> newqueue = new StringQueue<>();

        String line;
        while(!(line = input.nextLine()).equals("x")) {
            newqueue.enqueue(line);
        }

        if(isValid(newqueue)) {
            System.out.println("Valid");
        } else System.out.println("Invalid");

    }

}
