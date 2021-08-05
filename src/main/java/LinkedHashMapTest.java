import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
       Map<Integer, Character> map = new HashMap<>();

        /**
         * In HashMap, insertion isn't guaranteed
         * In this case, it uses hashCode() from Integer wrapper,
         * which simply returns its int; hence it will be ordered
         */

        System.out.println("HashMap Order: ");
       map.put(5, 'a');
       map.put(4, 'b');
       map.put(3, 'c');
       map.put(2, 'd');
       map.put(1, 'e');

       map.forEach((k,v) -> System.out.println("Key: " + k + " - Value: "+ v));

       Map<Integer, Character> linkedHashMap = new LinkedHashMap<>(5, 1, true);

        /**
         * In LinkedHashMap, the order in which elements were inserted is retained
         *
         * There is also an interesting field in LinkedHashMap - accessOrder
         * If it's set to true, the Map will order its elements based on their
         * access frequency - sounds pretty useful
         */
        System.out.println("\nLinkedHashMap Order: ");
        linkedHashMap.put(5, 'a');
        linkedHashMap.put(4, 'b');
        linkedHashMap.put(3, 'c');
        linkedHashMap.put(2, 'd');
        linkedHashMap.put(1, 'e');

        linkedHashMap.forEach((k,v) -> System.out.println("Key: " + k + " - Value: "+ v));


        /**
         * The oreder will be:
         * Key: 4 - Value: b
         * Key: 2 - Value: d
         * Key: 3 - Value: c
         * Key: 5 - Value: a
         * Key: 1 - Value: e
         *
         * Since 1 was the last element which was accessed, it is the closest to
         * the end
         *
         * Also, the LinkedHashMap has method removeEldestEntry() which would remove
         * the entry which is accessed the least amount of time
         */
        System.out.println("\nLinkedHashMap accessOrder property: ");
        linkedHashMap.get(3);
        linkedHashMap.get(5);
        linkedHashMap.get(1);
        linkedHashMap.forEach((k,v) -> System.out.println("Key: " + k + " - Value: "+ v));

        System.out.println("\nSimpleLRUCache order with removeEldestEntry(): ");

        Map<Integer, Character> simpleLRUCache = new SimpleLRUCache<>(2);
        simpleLRUCache.put(1, 'a');
        simpleLRUCache.put(2, 'b');
        simpleLRUCache.put(3, 'c');
        simpleLRUCache.get(2);
        simpleLRUCache.put(9, 'd');

        simpleLRUCache.forEach((k,v) -> System.out.println("Key: " + k + " - Value: "+ v));





    }
}
