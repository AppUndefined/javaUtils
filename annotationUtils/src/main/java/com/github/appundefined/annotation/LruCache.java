package com.github.appundefined.annotation;

import java.util.ArrayDeque;
import java.util.HashSet;

public class  LruCache {
    // 存储值
    private ArrayDeque<Object> queue = new ArrayDeque<>();
    private HashSet<Object> hashSet = new HashSet<>();
    // 缓存大小
    private int cacheSize;
    public LruCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    // 判断队列是否已满
    private boolean isQueueFull() {
        return queue.size() == cacheSize;
    }

    private void enqueue(Object obj,LruInterface lruInterface) {
        if (isQueueFull()) {
            lruInterface.remove(queue.getLast());
            hashSet.remove(queue.getLast());
            // 移除最后一个 如果使用  queue.removeLast(); 如果是 null 会抛出异常 使用 queue.pollLast();如果是 null 则返回 null
            queue.pollLast();
        }
        queue.addFirst(obj);
        hashSet.add(obj);
    }

    /**
     * 有则移动到首位无则添加到首位
     * @param obj 任意数据对象
     */
    public void add(Object obj,LruInterface lruInterface) {
        if (!hashSet.contains(obj)) {
            // 如果没有缓存则添加进去
            enqueue(obj,lruInterface);
        } else if (obj != queue.getFirst()) {
            // 如果在缓存中则移动到队首
            queue.remove(obj);
            queue.addFirst(obj);
        }
    }

    public void printQueue() {
        while (!queue.isEmpty()) {
            System.out.println(queue.pop() + " ");
        }
    }
}
