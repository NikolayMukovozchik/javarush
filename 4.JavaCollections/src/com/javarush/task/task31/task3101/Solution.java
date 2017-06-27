package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        List<File> files = getFiles(path);
        List<File> smallFiles = new ArrayList<>();

        if((files != null) & !files.isEmpty()){
            for(File file:files){
                if(file.length() > 50) FileUtils.deleteFile(file);
                else if((file.length() <= 50) & (file.length() > 0)){
                    smallFiles.add(file);
                }
            }
        }

        Collections.sort(smallFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        String allFilesContent = resultFileAbsolutePath.getParent() + "\\allFilesContent.txt";
        FileUtils.renameFile(resultFileAbsolutePath, new File(allFilesContent));


        try(BufferedWriter out = new BufferedWriter(new FileWriter(allFilesContent))){
            for(int i = 0; i < smallFiles.size(); i++){
                File file = smallFiles.get(i);
                BufferedReader in = new BufferedReader(new FileReader(file));
                while(in.ready())
                    out.write(in.read());
                out.write(System.lineSeparator());
                in.close();
            }
        } catch (IOException e){}
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    private static List<File> getFiles(File file){
        List<File> list = new ArrayList<>();
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if((files != null) & (files.length > 0)) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory())
                        list.addAll(getFiles(files[i]));
                    else {
                        list.add(files[i]);
                    }
                }
            }
        }
        return list;
    }
}
