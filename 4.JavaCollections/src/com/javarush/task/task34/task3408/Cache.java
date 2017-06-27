package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V vObj = cache.get(key);
        if (vObj == null){
            vObj = (V) clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, vObj);
            return vObj;
        }

        return vObj;
    }

    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            Object key = method.invoke(obj);
            cache.put((K) key, obj);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {}
        return false;
    }

    public int size() {
        return cache.size();
    }
}
