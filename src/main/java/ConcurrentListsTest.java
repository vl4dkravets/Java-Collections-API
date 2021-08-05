import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentListsTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        List<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();

        for(int i = 1; i <= 100; i++) {
            synchronizedList.add(i);
            copyOnWriteList.add(i);
        }
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Future<Long> synchronizedListRes1 = pool.submit(new MyListIterator(countDownLatch, 1, 50, synchronizedList));
        Future<Long> synchronizedListRes2 = pool.submit(new MyListIterator(countDownLatch, 51, 100, synchronizedList));

        Future<Long> copyOnWriteListRes1 = pool.submit(new MyListIterator(countDownLatch, 1, 50, copyOnWriteList));
        Future<Long> copyOnWriteListRes2 = pool.submit(new MyListIterator(countDownLatch, 51, 100, copyOnWriteList));

        // Start the thread all at once
        countDownLatch.countDown();

        System.out.println("synchronizedList1 time: " + synchronizedListRes1.get()/1000);
        System.out.println("synchronizedList2 time: " + synchronizedListRes2.get()/1000);
        System.out.println("copyOnWriteListRes1 time: " + copyOnWriteListRes1.get()/1000);
        System.out.println("copyOnWriteListRes2 time: " + copyOnWriteListRes2.get()/1000);
    }



    static class MyListIterator implements Callable<Long> {
        private CountDownLatch countDownLatch;
        private int start;
        private int end;
        private List<Integer> list;

        public MyListIterator(CountDownLatch countDownLatch,
                              int start,
                              int end,
                              List<Integer> list) {
            this.countDownLatch = countDownLatch;
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        public Long call() throws Exception {

            countDownLatch.await();

            Long counter = System.nanoTime();
            for(int i = start; i < end; i++) {
                list.get(i);
            }
            long res = System.nanoTime()-counter;
            return res;
        }
    }
}
