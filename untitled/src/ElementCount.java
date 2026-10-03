import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ElementCount {

    static long count = 0;

    static final BoundedRandomGenerator random = new BoundedRandomGenerator();
    private static final int ARRAY_LENGTH = 10000;
    private static final int NUM_THREADS = 10;

    private static final int SEARCH_TARGET = 5;

    // TODO: Define synchronization elements and initialize
    private static final Lock lock = new ReentrantLock();

    // DO NOT CHANGE
    public static int[] getSubArray(int[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end);
    }

    public static void main(String[] args) throws InterruptedException {

        int[] arr = ArrayGenerator.generate(ARRAY_LENGTH, SEARCH_TARGET);

        // TODO: Make the CountThread class a thread and start 10 instances
        // Each instance should take a subarray from the original array with equal length
        CountThread[] threads = new CountThread[NUM_THREADS];
        int subArrayLength = ARRAY_LENGTH / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * subArrayLength;
            int end = start + subArrayLength;
            int[] subArray = getSubArray(arr, start, end);

            threads[i] = new CountThread(subArray, SEARCH_TARGET);
            threads[i].start();
        }

        // all threads done
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        // DO NOT CHANGE

        System.out.println("The number of total counted elements is: " + count);
        System.out.println("The generated number of elements is: " + ArrayGenerator.elementCount);

    }

    // TO DO: Make the CountThread class a thread
    static class CountThread extends Thread {

        private int[] arr;
        private int target;

        public CountThread(int[] arr, int target) {
            this.arr = arr;
            this.target = target;
        }

        @Override
        public void run() {

            int localCount = 0;

            for (int num : this.arr) {

                if (num == target) {
                    localCount++;
                }
            }

            // TODO: Add localCount to global count using lock
            lock.lock();
            try {
                count += localCount;
            } finally {
                lock.unlock();
            }
        }
    }


    //************ DO NOT CHANGE ************//
    static class BoundedRandomGenerator {

        static final Random random = new Random();
        static final int RANDOM_BOUND = 100;

        public int nextInt() {
            return random.nextInt(RANDOM_BOUND);
        }

    }

    static class ArrayGenerator {

        static int elementCount;

        static int[] generate(int length, int target) {

            int[] array = new int[length];

            for (int i = 0; i < length; i++) {

                int element = ElementCount.random.nextInt();

                if (element == target) {
                    elementCount++;
                }

                array[i] = element;
            }

            return array;
        }
    }
}