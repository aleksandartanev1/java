import java.util.LinkedList;
import java.util.Queue;

class VideoBuffer {
    private Queue<String> buffer = new LinkedList<>();
    private final int CAPACITY = 3; // Максимум 3 фрејмови во баферот

    // Метода за додавање фрејмови (од Продуцентот)
    public synchronized void produceFrame(String frame) throws InterruptedException {
        // Додека баферот е полн, чекај и ослободи го локот
        while (buffer.size() == CAPACITY) {
            System.out.println("Баферот е полн. Продуцентот чека...");
            wait();
        }

        buffer.add(frame);
        System.out.println("Продуциран: " + frame + " | Големина: " + buffer.size());

        // Извести го конзументот дека има нов фрејм за читање
        notifyAll();
    }

    // Метода за земање фрејмови (од Конзументот/Плеерот)
    public synchronized String consumeFrame() throws InterruptedException {
        // Додека баферот е празен, чекај
        while (buffer.isEmpty()) {
            System.out.println("Баферот е празен. Плеерот чека видео податоци...");
            wait();
        }

        String frame = buffer.poll();
        System.out.println("Прикажан на екран: " + frame + " | Големина: " + buffer.size());

        // Извести го продуцентот дека се ослободи место во баферот
        notifyAll();
        return frame;
    }
}

public class mojzad2 {
    public static void main(String[] args) {
        VideoBuffer videoBuffer = new VideoBuffer();

        // Нитка Продуцент (Интернет стриминг)
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    videoBuffer.produceFrame("Frame-" + i);
                    Thread.sleep(200); // Симулира преземање
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        // Нитка Конзумент (Видео Плеер)
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    videoBuffer.consumeFrame();
                    Thread.sleep(500); // Симулира рендерирање (побавно е)
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        });

        producer.start();
        consumer.start();
    }
}