package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        Random random = new Random();
        int num = 48 + random.nextInt(10);
        int up = 65 + random.nextInt(26);
        int down = 97 + random.nextInt(26);

        array.write(num);
        array.write(up);
        array.write(down);

        for(int i = 0; i < 5; i++){
            int rnd = 1 + random.nextInt(3);
            switch (rnd){
                case 1: array.write(48 + (int)(Math.random() * 10));
                break;
                case 2: array.write(65 + ((int)Math.random() * 26));
                break;
                case 3: array.write(97 + ((int)Math.random() * 26));
                break;
            }
        }

        return array;
    }


}