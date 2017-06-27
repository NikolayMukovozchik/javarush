package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mukovozchik on 30.05.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>{
    private static final Object PRESENT = new Object();
    private transient HashMap map;

    @Override
    public Iterator iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    public AmigoSet() {
        this.map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = (int) Math.max(16, (collection.size()/.75f + 1));
        this.map = new HashMap<>(capacity);
        this.addAll(collection);
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
        AmigoSet<E> amigoSet = new AmigoSet();
        try{
            amigoSet.addAll(this);
            amigoSet.map.putAll((Map) this.map.clone());
        } catch (Exception e){
            throw new InternalError();
        }
        return amigoSet;

    }
}
