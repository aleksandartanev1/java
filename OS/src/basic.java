class ThreadA extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("A:" + i);
        }
        System.out.println("A DONE");
    }
}

class ThreadB extends Thread {
    public void run() {
    for (int i = -1; i > -5; i--) {
        System.out.println("B:" + i);
        }
        System.out.println("B DONE");
    }
}

public class basic{
    public static void main(String[] args) {
        ThreadA ta = new ThreadA();
        ThreadB tb = new ThreadB();
        ta.start();
        tb.start();
        System.out.println("MAIN DONE");
    }
}
