import javax.xml.crypto.Data;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        Map<SampleData, String> map = new WeakHashMap<>();
        SampleData data = new SampleData();
        map.put(data, "information");

        data = null;
        System.gc();

        for(int i = 1; i < 10000; i++) {
            if(map.isEmpty()) {
                System.out.println("Empty on step " + i);
                break;
            }
        }
    }

    static class SampleData {}
}
