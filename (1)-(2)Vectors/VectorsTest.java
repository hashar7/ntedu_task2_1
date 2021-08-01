package com.company;

import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class VectorsTest {

    private Vector<Double> v1 = new Vector<>(List.of(11.0, 22.0, 33.0, 44.0, 55.0));
    private Vector<Double> v2 = new Vector<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0));

    @Test
    public void multiplicationOnScalarTest() {
        assertEquals(new Vector<>(List.of(22.0, 44.0, 66.0, 88.0, 110.0)),
                Vectors.multiplicationOnScalar(v1, 2.0));
    }

    @Test
    public void addTest() {
        assertEquals(new Vector<>(List.of(12.0, 24.0, 36.0, 48.0, 60.0)), Vectors.add(v1, v2));
    }

    @Test
    public void ScalarProductTest() {
        assertEquals(Double.valueOf(605.0), Vectors.ScalarProduct(v1, v2));
    }

    @Test
    public void OutputVectorTest() throws IOException {
        File file = new File("/Users/mikhail/Downloads/OutPutVectorTest.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream("/Users/mikhail/Downloads/OutPutVectorTest.txt");
        Vectors.outputVector(v1, fos);
        fos.close();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        fr.close();
        reader.close();
        assertEquals("5 11.0 22.0 33.0 44.0 55.0", line);
    }

    @Test
    public void InputVectorTest() throws IOException {
        TestHelper();
        FileInputStream fis = new FileInputStream("/Users/mikhail/Downloads/OutPutVectorTest.txt");
        assertEquals(v1, Vectors.inputVector(fis));
    }

    @Test
    public void WriteVectorTest() throws IOException {
        File file = new File("/Users/mikhail/Downloads/OutPutVectorTest.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fw);
        Vectors.writeVector(v1, writer);
        writer.close();
        fw.close();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        fr.close();
        reader.close();
        assertEquals("5 11.0 22.0 33.0 44.0 55.0", line);
    }

    @Test
    public void ReadVectorTest() throws IOException {
        TestHelper();
        File file = new File("/Users/mikhail/Downloads/OutPutVectorTest.txt");
        FileReader fr = new FileReader(file);
        assertEquals(v1, Vectors.readVector(fr));
    }

    private void TestHelper() throws IOException {
        File file = new File("/Users/mikhail/Downloads/OutPutVectorTest.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("5 11.0 22.0 33.0 44.0 55.0");
        writer.flush();
        fw.close();
        writer.close();
    }

}
