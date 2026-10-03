import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class mojzad8 {

    // Семафор кој гарантира дека само една нитка во даден момент пишува во заедничкиот фајл
    static Semaphore fileLock = new Semaphore(1);

    // Заедничкиот BufferedWriter во кој ќе пишуваат сите нитки
    static BufferedWriter sharedWriter;

    static class Analyst extends Thread {
        private String inputFileName;
        private String companyName;

        public Analyst(String companyName, String inputFileName) {
            this.companyName = companyName;
            this.inputFileName = inputFileName;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            int totalProfit = 0;

            try {
                // 1. Секоја нитка си отвора сопствен BufferedReader за својот фајл
                br = new BufferedReader(new FileReader(inputFileName));
                String line;

                // Читање на сумите ред по ред и нивно собирање
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        totalProfit += Integer.parseInt(line.trim());
                    }
                }

                System.out.println("[Процес] " + companyName + " заврши со пресметка. Вкупно: " + totalProfit);

                // 2. КРИТИЧЕН РЕГИОН: Запишување во заедничкиот фајл
                // Пред да пишемe во sharedWriter, мора да го земеме локот!
                fileLock.acquire();
                try {
                    sharedWriter.write("Компанија: " + companyName + " | Вкупен профит: " + totalProfit);
                    sharedWriter.newLine();
                    // flush() ги присилува податоците веднаш да се запишат на диск
                    sharedWriter.flush();
                } finally {
                    // ОБВЕЗНО го ослободуваме фајлот во finally за да може другата нитка да запише
                    fileLock.release();
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Грешка во нитката: " + e.getMessage());
            } finally {
                // Секоја нитка си го затвора својот BufferedReader
                try {
                    if (br != null) br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Го отвораме заедничкиот фајл во main нитката
            sharedWriter = new BufferedWriter(new FileWriter("izvestaj.txt"));

            // Креираме и стартуваме 2 нитки аналитичари
            Analyst a1 = new Analyst("Алкалоид", "firma1.txt");
            Analyst a2 = new Analyst("Макпетрол", "firma2.txt");

            a1.start();
            a2.start();

            // Ги чекаме двете нитки да завршат целосно со join()
            a1.join();
            a2.join();

            System.out.println("[Успех] Сите нитки завршија. Извештајот е генериран.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // На самиот крај, откако сите нитки завршиле, main ја затвора заедничката датотека
            try {
                if (sharedWriter != null) {
                    sharedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}