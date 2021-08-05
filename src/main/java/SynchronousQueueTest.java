import java.util.Queue;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    /**
     * SynchronousQueue, when performing either put() or take()
     * operations on it, will fall asleep/block, thus waiting on counter operation
     * Meaning, when you put an elements, a thread block & waits when other thread will take it;
     * Or when a thread takes an element, it blocks till someone put another elements in synchronous queue
     *
     * The only thing - 2 threads are synchronized when sharing a single element
     */


    private static SynchronousQueue<Integer> queue = new SynchronousQueue<>();

    public static void main(String[] args) {
        int times = 3;
        new Thread(new Producer(times)).start();
        new Thread(new Consumer(times)).start();

    }

    static class Producer implements Runnable {
        private int times;
        private Random r;

        public Producer(int times) {
            this.times = times;
            r = new Random();
        }
        @Override
        public void run() {
            try {
                for(int i = 0; i < times; i++){
                    System.out.println("Producer sleeps");
                    Thread.sleep(3000);
                    queue.put(1);
                    System.out.println("Producer completed");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer implements Runnable {
        private int times;
        private Random r;

        public Consumer(int times) {
            this.times = times;
            r = new Random();
        }

        @Override
        public void run() {
            try {
                for(int i = 0; i < times; i++){
                    System.out.println("Consumer sleeps");
                    queue.take();
                    System.out.println("Consumer completed");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
