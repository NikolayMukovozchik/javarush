package com.javarush.task.task31.task3109;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("c:/1/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("c:/1/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("c:/1/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try {
            if(fileName.toLowerCase().contains(".xml"))
                properties.loadFromXML(Files.newInputStream(Paths.get(fileName)));
            else
                properties.load(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            new Properties();
        }
        return properties;
    }
}
