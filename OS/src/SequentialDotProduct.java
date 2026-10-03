import java.util.Scanner;

public class SequentialDotProduct {

    static class ParallelDotProduct extends Thread {
        double[] a;
        double[] b;
        int start;
        int end;
        double partialresult;

        ParallelDotProduct(double[] a, double[] b, int start, int end) {
            this.a = a;
            this.b = b;
            this.start = start;
            this.end = end;
        }

        public void run () {
            for (int i = start; i < end; i++) {
                partialresult += a[i] * b[i];
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 1_000_000;
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();

        double[] a = new double[n];
        double[] b = new double[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = i + 1;
        }
        ParallelDotProduct [] threads = new ParallelDotProduct[m];
        double result = 0;
        int size = n / m;
        for (int i = 0; i < m; i++) {
            int start = i * size;
            int end = start + size;
            threads[i] = new ParallelDotProduct(a,b,start,end);

            threads[i].start();
        }

        for (ParallelDotProduct t : threads) {
            t.join();
            result += t.partialresult;
        }

        System.out.println("Dot product = " + result);
    }
}