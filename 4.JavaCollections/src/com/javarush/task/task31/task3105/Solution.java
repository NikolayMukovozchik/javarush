package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get(args[0]);
        Path zip = Paths.get(args[1]);
        String zipFile = args[1];

        String newFileName = "new/" + file.getFileName();

        if (!Files.isRegularFile(file) || !Files.isRegularFile(zip)) {
            return;
        }

        Map<String, byte[]> map = new HashMap<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                long entrySize = zipEntry.getSize();
                byte[] buffer = new byte[(int) entrySize];
                int counter = 0;

                while(counter < entrySize){
                    buffer[counter] = (byte) zipInputStream.read();
                    counter++;
                }
                map.put(zipEntry.getName(), buffer);
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))){
            boolean checkExisted = false;
            for(Map.Entry<String, byte[]> en : map.entrySet()){
                if(!en.getKey().equals(newFileName)){
                    zipOutputStream.putNextEntry(new ZipEntry(en.getKey()));
                    zipOutputStream.write(en.getValue());
                    zipOutputStream.closeEntry();
                } else {
                    checkExisted = true;
                    zipOutputStream.putNextEntry(new ZipEntry(newFileName));
                    Files.copy(file, zipOutputStream);
                    zipOutputStream.closeEntry();
                }
            }
            if(!checkExisted){
                zipOutputStream.putNextEntry(new ZipEntry(newFileName));
                Files.copy(file, zipOutputStream);
                zipOutputStream.closeEntry();
            }
        }
    }
}