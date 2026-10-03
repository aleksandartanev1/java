public class ParallelDotProduct {

    static class DotProductThread extends Thread {
        double[] a, b;
        int start, end;
        double partialResult = 0;

        DotProductThread(double[] a, double[] b, int start, int end) {
            this.a = a;
            this.b = b;
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                partialResult += a[i] * b[i];
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 1_000_000;
        int m = 4;

        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = i + 1;
        }

        int chunkSize = n / m;
        DotProductThread[] threads = new DotProductThread[m];

        for (int t = 0; t < m; t++) {
            int start = t * chunkSize;
            int end = start + chunkSize;
            if (t == m - 1) end = n;
            threads[t] = new DotProductThread(a, b, start, end);
            threads[t].start();
        }

        double result = 0;
        for (DotProductThread thread : threads) {
            thread.join();
            result += thread.partialResult;
        }

        System.out.println("Dot product = " + result);
    }
}