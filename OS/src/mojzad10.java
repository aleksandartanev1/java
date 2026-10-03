import java.util.LinkedList;
import java.util.Queue;

class VideoBuffer2 {
    private Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void downloadChunk(int chunk) throws InterruptedException {
        while (buffer.size() == CAPACITY) {
            System.out.println("Баферот е полн. Симнувачот чека...");
            wait();
        }
        buffer.add(chunk);
        System.out.println("Симнато парче: " + chunk);
        notify();
    }

    public synchronized int playChunk() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Баферот е празен. Плеерот чека (Buffering)...");
            wait();
        }
        int chunk = buffer.remove();
        System.out.println("Репродуцирано парче: " + chunk);
        notify();
        return chunk;
    }
}

class Downloader extends Thread {
    private VideoBuffer2 buffer;

    public Downloader(VideoBuffer2 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int chunk = 1;
        try {
            while (true) {
                Thread.sleep(500);
                buffer.downloadChunk(chunk++);
            }
        } catch (InterruptedException e) {
            System.out.println("Downloader е прекинат.");
        }
    }
}

class Player extends Thread {
    private VideoBuffer2 buffer;

    public Player(VideoBuffer2 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                buffer.playChunk();
            }
        } catch (InterruptedException e) {
            System.out.println("Player е прекинат.");
        }
    }
}

public class mojzad10 {
    public static void main(String[] args) {
        VideoBuffer2 buffer = new VideoBuffer2();

        Downloader downloader = new Downloader(buffer);
        Player player = new Player(buffer);

        System.out.println("--- Стартува симулацијата на видео стриминг ---");
        downloader.start();
        player.start();
    }
}