package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    static int totalFolders = 0;
    static int totalFiles = 0;
    static long totalBytes = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String folderName = bufferedReader.readLine();
        bufferedReader.close();

        Path folderPath = Paths.get(folderName);

        if(!Files.isDirectory(folderPath)){
            System.out.println(folderName + " - не папка");
            return;
        }

        Files.walkFileTree(Paths.get(folderName), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(attrs.isRegularFile())
                    totalFiles++;
                totalBytes += attrs.size();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                totalFolders++;
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("Всего папок - " + (totalFolders-1));
        System.out.println("Всего файлов - " + totalFiles);
        System.out.println("Общий размер - " + totalBytes);
    }
}
