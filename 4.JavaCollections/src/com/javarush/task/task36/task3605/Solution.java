package com.javarush.task.task36.task3605;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        TreeSet<Character> tree = new TreeSet<>();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String s;
        while((s = br.readLine()) != null) {
            s = s.toLowerCase();
            char[] chars = s.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                if( (!tree.contains(chars[i])) && (chars[i] >= 'a') && (chars[i] <= 'z')  ){
                    tree.add(chars[i]);
                }
            }
        }
        br.close();
        fr.close();

        Iterator<Character> itr= tree.iterator();
        int size = 5;
        while(itr.hasNext() && (size > 0) ){
            System.out.print(itr.next());
            size--;
        }
    }
}

