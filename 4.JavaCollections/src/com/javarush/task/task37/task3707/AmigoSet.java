package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mukovozchik on 30.05.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    public AmigoSet() {
        map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int) (collection.size()/.75f + 1));
        map = new HashMap<E, Object>(capacity);
        addAll(collection);
    }

    public boolean add(E e){
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean isEmpty(){
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o){
        return map.containsValue(o);
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public boolean remove(Object o){
        return map.remove(o) != null;
    }

    @Override
    public Object clone(){
        try{
            AmigoSet<E> amigoSet = (AmigoSet)super.clone();
            amigoSet.map = (HashMap) map.clone();
            return amigoSet;
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
