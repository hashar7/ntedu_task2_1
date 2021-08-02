package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeMyClassToBePersisted {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        MyClassToBePersisted mctbp = new MyClassToBePersisted("Mikhail Uspenskiy", "416 CS MSU");
        System.out.println("Serializing object: " + mctbp.toString());
        FileOutputStream outputStream = new FileOutputStream("/Users/mikhail/Downloads/mctbp.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(mctbp);
        objectOutputStream.close();
        outputStream.close();
    }
}
