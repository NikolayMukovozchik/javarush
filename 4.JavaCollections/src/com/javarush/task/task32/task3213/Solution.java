package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if(reader == null) return new String();
        StringWriter sw = new StringWriter();
        int c = 0;
        while((c = reader.read()) > 0){
            c += key;
            sw.write(c);
        }
        return sw.toString();
    }
}
