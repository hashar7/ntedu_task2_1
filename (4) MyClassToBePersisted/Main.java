package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializeMyClassToBePersisted.main(null);
        DeserializeMyClassToBePersisted.main(null);
        /* OUTPUT:

        Serializing object: MyClassToBePersisted{
        profile='Mikhail Uspenskiy',
        group='416 CS MSU',
        }

        Deserialized object: MyClassToBePersisted{
        profile='Mikhail Uspenskiy',
        group='416 CS MSU',
        }

         */
    }
}

