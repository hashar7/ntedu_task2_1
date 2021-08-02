package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeMyClassToBePersisted {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/mikhail/Downloads/mctbp.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MyClassToBePersisted mctbp = (MyClassToBePersisted) objectInputStream.readObject();
        fileInputStream.close();
        objectInputStream.close();
        System.out.println("Deserialized object: " + mctbp.toString());
    }
}
