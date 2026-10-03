import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class mojzad7 {

    public static void main(String[] args) {
        // Референците ги дефинираме на почеток како null
        BufferedReader br = null;
        BufferedWriter bw = null;

        int countPolozeni = 0;

        try {
            // 1. Иницијализација на BufferedReader за читање
            br = new BufferedReader(new FileReader("studenti.txt"));

            // 2. Иницијализација на BufferedWriter за запишување
            bw = new BufferedWriter(new FileWriter("polozeni.txt"));

            String line;

            // 3. Циклус кој чита ред по ред сè додека има податоци (line не е null)
            while ((line = br.readLine()) != null) {
                // Ако линијата е празна, прескокни ја
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Секој ред го делиме на два дела според празното место: [0]=Име, [1]=Поени
                String[] parts = line.split(" ");
                String ime = parts[0];
                int poeni = Integer.parseInt(parts[1]);

                // Проверка на условот: дали има 50 или повеќе поени
                if (poeni >= 50) {
                    // Го запишуваме студентот во новата датотека
                    bw.write(ime + " " + poeni);
                    bw.newLine(); // ОБВЕЗНО: Оди во нов ред во фајлот
                    countPolozeni++; // Зголеми го бројачот
                }
            }

            // 4. Запишување на вкупниот број на крајот од фајлот
            bw.write("Вкупно положени: " + countPolozeni);
            bw.newLine();

        } catch (IOException e) {
            // Сега компајлерот е среќен бидејќи br.readLine() и new FileReader() фрлаат IOException
            System.out.println("Се случи грешка при работа со датотеките: " + e.getMessage());
        } finally {
            // 5. ОБВЕЗНО затворање на стримовите во finally блок
            // Секој стрим се затвора во посебен try-catch за да бидеме сигурни дека
            // ако едниот пукне, другиот сепак ќе се обиде да се затвори.
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bw != null) {
                    bw.close(); // Ако не го затвориш bw, податоците нема да се запишат во фајлот!
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bw);
    }
}