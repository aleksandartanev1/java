import java.util.concurrent.Semaphore;

public class mojzad6 {

    // 1. Семафор за столчиња во чекална (максимум 5 пациенти внатре)
    static Semaphore cekalna = new Semaphore(5);

    // 2. Семафор за ексклузивен влез во кабината (строго 1 пациент)
    static Semaphore kabina = new Semaphore(1);

    // 3. Семафори за двонасочна комуникација (двата почнуваат со 0)
    static Semaphore pacientSpremen = new Semaphore(0); // Сигнал од Пациент до Доктор
    static Semaphore doktorGotov = new Semaphore(0);   // Сигнал од Доктор до Пациент

    public static void init() {
        // Празно бидејќи се иницијализирани горе
    }

    static class Patient extends Thread {
        private int id;

        public Patient(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Пациент " + id + " пристигна во пунктот.");

                // 1. Пациентот бара слободно столче во чекалната
                cekalna.acquire();
                System.out.println("-> Пациент " + id + " седна во чекалната.");

                // 2. Пациентот се обидува да влезе во кабината кај докторот
                kabina.acquire();
                System.out.println(">>> Пациент " + id + " ВЛЕЗЕ во кабината.");

                // 3. Му сигнализира на докторот дека е спремен за вакцина
                pacientSpremen.release(); // Го будиме докторот

                // 4. Чека докторот да заврши со боцкањето
                doktorGotov.acquire(); // Пациентот блокира тука сè додека докторот не му сигнализира

                System.out.println("<<< Пациент " + id + " прими вакцина и ИЗЛЕЗЕ од кабината.");

                // 5. Пациентот ја напушта кабината и го ослободува столчето во чекалната
                kabina.release();
                cekalna.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Doctor extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 1. Докторот чека пациент да влезе и да му сигнализира дека е спремен
                    pacientSpremen.acquire(); // Докторот спие тука додека некој пациент не направи release()

                    System.out.println("[Доктор] Боцкам пациент, аплицирам вакцина...");
                    Thread.sleep(300); // Симулација на вакцинација

                    // 2. Докторот му сигнализира на пациентот дека е готов
                    doktorGotov.release(); // Му праќа сигнал на пациентот кој чека во кабината

                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        init();

        Doctor doctor = new Doctor();
        doctor.setDaemon(true);
        doctor.start();

        // Пуштаме 8 пациенти во исто време
        Patient[] patients = new Patient[8];
        for (int i = 0; i < 8; i++) {
            patients[i] = new Patient(i + 1);
            patients[i].start();
        }

        for (int i = 0; i < 8; i++) {
            patients[i].join();
        }

        System.out.println("Сите пациенти се вакцинирани. Пунктот се затвора.");
    }
}