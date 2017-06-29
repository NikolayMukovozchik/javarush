package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();

        // Получить список файлов в указанном каталоге
        File file = new File(pathToAnimals);
        File[] files = file.listFiles();

        // В цикле
        for(int i = 0; i < files.length; i++) {
            if (files[i].isFile() && files[i].getName().endsWith(".class")) {
                // попробовать их загрузить
                String packageName = Solution.class.getPackage().getName() + ".data";
                Class clazz = new ClassFromPath().load(files[i].toPath(), packageName);

                // проверить, наследуются ли от Animal
                for (Class cls : clazz.getInterfaces()) {
                    if (cls.getSimpleName().equals("Animal")) {
                        // проверить, есть ли загрузчик по умолчанию
                        Constructor[] allConstructors = clazz.getConstructors();
                        for (Constructor ctor : allConstructors) {
                            Class[] pType = ctor.getParameterTypes();
                            if (pType.length == 0) {
                                // создать объект и добавить в множество
                                try {
                                    set.add((Animal) clazz.newInstance());
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        // вернуть множество
        return set;
    }

    public static class ClassFromPath extends ClassLoader {
        public Class<?> load(Path path, String packageName) {
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] b = Files.readAllBytes(path);
                return defineClass(className, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
