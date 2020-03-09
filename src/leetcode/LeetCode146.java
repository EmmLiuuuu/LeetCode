package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
    }

    static class LRUCache extends LinkedHashMap<Integer, Integer> {

        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return super.size() > this.capacity;
        }

        public int get(int key) {
            Integer get = super.get(key);
            if (get == null) {
                return -1;
            }
            return get;
        }

        public void put(int key, int value) {
            super.put(key, value);
        }
    }

}
