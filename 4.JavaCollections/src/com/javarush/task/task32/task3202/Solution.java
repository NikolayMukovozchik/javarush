package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("c:/1/testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (is == null) return new StringWriter();

        StringWriter sw = new StringWriter();
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) > 0) {
            sw.write(new String(buffer), 0, len);
        }
        return sw;
    }
}
