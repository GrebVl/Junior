package com.gb.lesson03.hw03;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable,
 * и сохраняющие их в файл.
 * Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 *
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString()
 * и загружает объект из файла и удаляет этот файл.
 *
 * Что делать в ситуациях, когда файла нет
 * или в нем лежит некорректные данные - подумать самостоятельно.
 */

public class MainHw03 {
    public static void main(String[] args) {
        Animal cat = new Animal("Пушок");
        Path myFile = Path.of(cat.getClass().getName() + "_" + UUID.randomUUID().toString());
        try {
            Files.createFile(myFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        serialObj(cat, myFile);

        Animal cat1 = deSerialObj(myFile);
        System.out.println(cat1);


    }

    public static void serialObj(Object o, Path myFile){

        try (OutputStream outputStream = Files.newOutputStream(myFile)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Animal deSerialObj(Path myFile) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(myFile))) {
            Files.delete(myFile);
            return (Animal) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
