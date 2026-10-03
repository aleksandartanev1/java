import java.util.concurrent.Semaphore;

class SwimmingPool {
    // Го ограничуваме пристапот на максимум 2 нитки истовремено
    private final Semaphore lanes = new Semaphore(2);

    public void swim(String swimmerName) {
        try {
            System.out.println(swimmerName + " пристигна и чека слободна патека.");

            // Нитката се обидува да земе дозвола (permit).
            // Ако нема слободна, тука блокира (чека).
            lanes.acquire();

            System.out.println(">>> " + swimmerName + " ВЛЕЗЕ во базен (Патека зафатена).");

            // Симулираме пливање од 2 секунди
            Thread.sleep(2000);

            System.out.println("<<< " + swimmerName + " заврши со пливање и ИЗЛЕЗЕ.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // МНОГУ ВАЖНО: Секогаш го ослободуваме семафорот во finally блок
            // за да не остане заклучен засекогаш ако се случи дефект.
            lanes.release();
        }
    }
}

public class mojzad4{
    public static void main(String[] args) {
        SwimmingPool pool = new SwimmingPool();

        // Креираме 4 пливачи (нитки) кои сакаат да пливаат во исто време
        Thread t1 = new Thread(() -> pool.swim("Пливач 1"));
        Thread t2 = new Thread(() -> pool.swim("Пливач 2"));
        Thread t3 = new Thread(() -> pool.swim("Пливач 3"));
        Thread t4 = new Thread(() -> pool.swim("Пливач 4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}