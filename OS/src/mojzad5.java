import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class mojzad5 {

    // TODO: Дефинирај ги твоите семафори тука
    static Semaphore masa = new Semaphore(3);
    static Semaphore kelner = new Semaphore(0);
    static Semaphore meni = new Semaphore(0);

    public static void init() {
        // TODO: Иницијализирај ги семафорите со точен број на дозволи (permits)

    }

    static class Guest extends Thread {
        private int id;

        public Guest(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Гостин " + id + " пристигна пред ресторанот.");

                // 1. Гостинот се обидува да влезе и да заземе маса
                // ----------------------------------------
                masa.acquire();

                System.out.println("-> Гостин " + id + " седна на маса.");

                // 2. Гостинот го повикува келнерот
                // ----------------------------------------

                System.out.println("-> Гостин " + id + " го чека келнерот за мени...");
                kelner.release();

                // 3. Гостинот чека келнерот да му го донесе менито пред да почне да јаде
                // ----------------------------------------
                meni.acquire();

                // Критичен регион: Гостинот јаде
                System.out.println(">>> Гостин " + id + " ЈАДЕ...");
                Thread.sleep(1000); // Симулација на јадење

                System.out.println("<- Гостин " + id + " заврши со јадење и ја ослободува масата.");

                // 4. Ослободи ја масата
                // ----------------------------------------
                masa.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Waiter extends Thread {
        @Override
        public void run() {
            // Келнерот работи во бесконечен циклус
            while (true) {
                try {
                    // 1. Келнерот спие/чека додека некој гостин не го повика
                    // ----------------------------------------
                    kelner.acquire();

                    System.out.println("[Келнер] Се разбудив, му носам мени на гостинот.");
                    Thread.sleep(200); // Симулација на носење мени

                    // 2. Келнерот му сигнализира на гостинот дека менито е доставено
                    // ----------------------------------------
                    meni.release();

                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        init();

        Waiter waiter = new Waiter();
        waiter.setDaemon(true); // За да се угаси кога ќе завршат гостите
        waiter.start();

        // Пуштаме 5 гости одеднаш (а има само 3 маси)
        Guest[] guests = new Guest[5];
        for (int i = 0; i < 5; i++) {
            guests[i] = new Guest(i + 1);
            guests[i].start();
        }

        for (int i = 0; i < 5; i++) {
            guests[i].join();
        }

        System.out.println("Сите гости си заминаа. Ресторанот се затвора.");
    }
}