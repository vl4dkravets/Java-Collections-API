import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ProrityQueueTest {
    public static void main(String[] args) {
        System.out.println("Queue - LL\n");
        Queue<Integer> q = new LinkedList<>();

        q.offer(5);
        q.offer(4);
        q.offer(3);
        q.offer(2);
        q.offer(1);

        while(!q.isEmpty()) {
            System.out.println(q.poll());
        }

        /**
         * Priority = order (based on comparison)
         */
        System.out.println("\nQueue - Priority Queue\n");
        Queue<Integer> q1 = new PriorityQueue<>();

        q1.offer(5);
        q1.offer(4);
        q1.offer(3);
        q1.offer(2);
        q1.offer(1);

        while(!q1.isEmpty()) {
            System.out.println(q1.poll());
        }

        System.out.println("\nQueue - Priority Queue which selects even numbers first\n");
        Queue<Integer> q2 = new PriorityQueue<>(new Comparator<Integer>() {
                                    @Override
                                    public int compare(Integer o1, Integer o2) {
                                        if(o1 % 2 == 0 && o2 % 2 == 0) {return o1-o2;}
                                        else if(o1 % 2 == 0) { return -1;}
                                        else if(o2 % 2 == 0) { return 1;}
                                        else { return o1-o2;}
                                    }
                                }
                            );

        q2.offer(5);
        q2.offer(2);
        q2.offer(1);
        q2.offer(4);

        while(!q2.isEmpty()) {
            System.out.println(q2.poll());
        }

    }
}
