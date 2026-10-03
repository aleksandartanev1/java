import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

// 1. Класата за споделениот бафер (од претходниот одговор)
class BoundedBuffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 10;

    static Semaphore mutex = new Semaphore(1);
    static Semaphore emptyPlaces = new Semaphore(10);
    static Semaphore fullPlaces = new Semaphore(0);

    public void produce(int item) throws InterruptedException {
        emptyPlaces.acquire();
        mutex.acquire();

        buffer.add(item);
        System.out.println(Thread.currentThread().getName() + " ПРОДУЦИРАШЕ: " + item + " | Големина: " + buffer.size());

        mutex.release();
        fullPlaces.release();
    }

    public int consume() throws InterruptedException {
        fullPlaces.acquire();
        mutex.acquire();

        int item = buffer.remove();
        System.out.println(Thread.currentThread().getName() + " ПОТРОШИ: " + item + " | Големина: " + buffer.size());

        mutex.release();
        emptyPlaces.release();
        return item;
    }
}

// 2. Класа за нитката Производител
class Producer extends Thread {
    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buffer, String name) {
        super(name); // Поставување име на нитката за подобар приказ во конзола
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int item = 0;
        try {
            while (true) {
                // Секој производител генерира различен број базиран на неговото име
                int value = (int) (Math.random() * 100);
                buffer.produce(value);

                // Кратка пауза за да се симулира време за продукција
                Thread.sleep((int) (Math.random() * 800));
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " е прекинат.");
        }
    }
}

// 3. Класа за нитката Потрошувач
class Consumer extends Thread {
    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer, String name) {
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();

                // Кратка пауза за да се симулира време за трошење на елементот
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " е прекинат.");
        }
    }
}

// 4. Главната класа со main методо
public class mojzad9 {
    public static void main(String[] args) {
        // Креираме ОДНОСНО ЕДЕН споделен бафер
        BoundedBuffer sharedBuffer = new BoundedBuffer();

        // Креираме 3 нитки производители
        Producer p1 = new Producer(sharedBuffer, "Producer-1");
        Producer p2 = new Producer(sharedBuffer, "Producer-2");
        Producer p3 = new Producer(sharedBuffer, "Producer-3");

        // Креираме 2 нитки потрошувачи
        Consumer c1 = new Consumer(sharedBuffer, "Consumer-1");
        Consumer c2 = new Consumer(sharedBuffer, "Consumer-2");

        System.out.println("--- Стартува симулацијата (Притисни Ctrl+C за стоп) ---");

        // Ги стартуваме сите нитки во заднина
        p1.start();
        p2.start();
        p3.start();

        c1.start();
        c2.start();
    }
}