package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path resultFile = Paths.get(args[0]);

        Set<String> files = new TreeSet<String>();
        for (int i = 1; i < args.length; i++) {
            files.add(args[i]);
        }

        List<FileInputStream> list = new ArrayList<>();
        for (String fname : files) {
            FileInputStream fis = new FileInputStream(fname);
            list.add(fis);
        }

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(list));
        ZipInputStream zipInputStream = new ZipInputStream(sis);
        FileOutputStream os = new FileOutputStream(args[0]);
//        ZipEntry zipEntry = zipInputStream.getNextEntry();
        byte[] buffer = new byte[8 * 1024];
        while (zipInputStream.getNextEntry() != null) {
            int count;
            while ((count = zipInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
        }

        os.close();
        sis.close();
        zipInputStream.close();
    }
}
