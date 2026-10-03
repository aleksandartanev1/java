public class mojzad3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Главната нитка (main) започнува.");

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Нитката t1 започна со долга пресметка...");
                Thread.sleep(2000); // Симулира работа од 2 секунди
                System.out.println("Нитката t1 ЗАВРШИ со работата!");
            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        t1.start(); // Ја стартуваме нитката t1

        // БЕЗ JOIN: Главната нитка не ја чека t1 и веднаш печати:
        t1.join();
        System.out.println("Главната нитка (main) ЗАВРШИ.");
    }
}