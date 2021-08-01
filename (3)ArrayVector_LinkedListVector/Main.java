package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayVector a1 = new ArrayVectorImpl();
        a1.set(1.0, 22.2, 333.0, 4444.4);
        System.out.println("ArrayVector to serialize: " + a1.toString());

        /*ArrayVector Serialization*/
        FileOutputStream fos = new FileOutputStream("/Users/mikhail/Downloads/AVSer.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a1);
        oos.close();

        /*ArrayVector Deserialization*/
        FileInputStream fin = new FileInputStream("/Users/mikhail/Downloads/AVSer.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        ArrayVector a2 = (ArrayVector) ois.readObject();
        System.out.println("Object uploaded from file: " + a2.toString());
        System.out.println("Are they equal? : " + a1.equals(a2));

        /*------------------------------------------------------------------------------------------------------------*/

        LinkedListVector<Integer> l1 = new LinkedListVector<Integer>(1, 22, 333, 4444, 5555);
        System.out.println("LinkedListVector to serialize: " + l1.toString());

        /*LinkedListVector Serialization*/
        FileOutputStream fos1 = new FileOutputStream("/Users/mikhail/Downloads/LLSer.txt");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        oos1.writeObject(l1);
        oos1.close();

        /*LinkedListVector Deserialization*/
        FileInputStream fin1 = new FileInputStream("/Users/mikhail/Downloads/LLSer.txt");
        ObjectInputStream ois1 = new ObjectInputStream(fin1);
        LinkedListVector<Integer> l2 = (LinkedListVector<Integer>) ois1.readObject();
        System.out.println("Object uploaded from file: " + l2.toString());
        System.out.println("Are they equal? : " + l1.equals(l2));
    }
}

