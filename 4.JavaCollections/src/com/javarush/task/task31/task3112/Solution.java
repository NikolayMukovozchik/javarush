package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("http://lib.ru/ADAMS/copyright.txt", Paths.get("C:/App"));

        for (String line : Files.readAllLines(passwords, Charset.defaultCharset())) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        Path tempFile = Files.createTempFile("temp", ".txt");
        Files.copy(inputStream, tempFile);

        Path downloadedFile = Paths.get(downloadDirectory.toString() + urlString.substring(urlString.lastIndexOf("/")));
        Files.move(tempFile, downloadedFile);
        return downloadedFile;
    }
}