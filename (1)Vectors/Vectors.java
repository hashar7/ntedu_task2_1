package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vectors implements Serializable {

    /**
     * Method calculates the multiplication of a given {@link Vector} by a scalar.
     * @param vector — {@link Vector} that is needed to be multiplied.
     * @param scalar — scalar value.
     */
    public static Vector<Double> multiplicationOnScalar(Vector<Double> vector, Double scalar) {
        int bound = vector.size();
        Vector<Double> result = new Vector<>();
        IntStream.range(0, bound).forEachOrdered(i -> {
            Double newValue = vector.elementAt(i) * scalar;
            result.add(newValue);
        });
        return result;
    }

    /**
     * Method calculates sum of the given vectors {@link Vector}.
     * @param v1 — first {@link Vector}.
     * @param v2 — second {@link Vector}.
     * @return vector which is the sum of two given vectors {@link Vector}.
     * @throws IllegalArgumentException if dimension mismatch.
     */
    public static Vector<Double> add(Vector<Double> v1, Vector<Double> v2) throws IllegalArgumentException {
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("Different dimensions!");
        }
        int bound = v1.size();
        return IntStream.range(0, bound).mapToObj(i ->
                v1.elementAt(i) + v2.elementAt(i)).collect(Collectors.toCollection(Vector::new));
    }

    /**
     * Method calculates scalar product of two given vectors {@link Vector}.
     * @param v1 — first {@link Vector}.
     * @param v2 — second {@link Vector}.
     * @return scalar product of two given vectors {@link Vector}.
     * @throws IllegalArgumentException if dimension mismatch.
     */
    public static Double ScalarProduct(Vector<Double> v1, Vector<Double> v2) throws IllegalArgumentException {
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("Different dimensions!");
        }
        int bound = v1.size();
        return IntStream.range(0, bound).mapToDouble(i -> v1.elementAt(i) * v2.elementAt(i)).sum();
    }

    /**
     * Method writes given {@link Vector} in {@link OutputStream}.
     * @param v — {@link Vector} to write in {@link OutputStream}.
     * @param out — {@link OutputStream} in which given {@link Vector} should be written.
     * @throws IOException in case of error during writing in {@link OutputStream}.
     */
    public static void outputVector(Vector<Double> v, OutputStream out) throws IOException {
        int bound = v.size();
        String sb = IntStream.range(0, bound).mapToObj(i ->
                new StringBuilder().append(" ").append(v.elementAt(i)).toString()).
                collect(Collectors.joining("", String.valueOf(bound), ""));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        bufferedOutputStream.write(sb.getBytes(StandardCharsets.UTF_8));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        out.close();
    }

    /**
     * Method reads {@link Vector} from {@link InputStream}.
     * @param in — {@link InputStream} containing {@link Vector}.
     * @return — {@link Vector} with given values.
     * @throws IOException in case of errors during reading from {@link InputStream}.
     * @throws IllegalArgumentException in case of wrong values in {@link InputStream}.
     */
    public static Vector<Double> inputVector(InputStream in) throws IOException, IllegalArgumentException {
        return StreamTokenizerParser(new BufferedReader(new InputStreamReader(in)));
    }

    /**
     * Method writes {@link Vector} into {@link Writer}.
     * @param v — {@link Vector} to write.
     * @param out — {@link Writer} in which given {@link Vector} should be written.
     * @throws IOException in case of errors during writing into {@link Writer}.
     */
    public static void writeVector(Vector<Double> v, Writer out) throws IOException {
        String sb;
        int bound = v.size();
        sb = IntStream.range(0, bound).mapToObj(i ->
                new StringBuilder().append(" ").append(v.elementAt(i)).toString()).
                collect(Collectors.joining("", String.valueOf(bound), ""));
        out.write(sb);
        out.flush();
        out.close();;
    }

    /**
     * Method reads {@link Vector} from given {@link Reader}.
     * @param in {@link Reader} containing {@link Vector}.
     * @return {@link Vector} containing values from {@link Reader}.
     * @throws IOException in case of errors during reading from {@link Reader}.
     * @throws IllegalArgumentException in case of wrong input values.
     */
    public static Vector<Double> readVector(Reader in) throws IOException, IllegalArgumentException {
        return StreamTokenizerParser(in);
    }

    /**
     * Method reads {@link Vector} from given {@link Reader}.
     * @param in in {@link Reader} containing {@link Vector}.
     * @return {@link Vector} containing values from {@link Reader}.
     * @throws IOException in case of errors during reading from {@link Reader}.
     * @throws IllegalArgumentException in case of wrong input values.
     */
    private static Vector<Double> StreamTokenizerParser(Reader in) throws IOException, IllegalArgumentException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        int dimension = streamTokenizer.nextToken() != StreamTokenizer.TT_EOF ? (int) streamTokenizer.nval : -1;
        if (dimension <= 0) {
            throw new IllegalArgumentException();
        }
        Vector<Double> result = new Vector<>();
        for (int i = 0; i < dimension && streamTokenizer.nextToken() != StreamTokenizer.TT_EOF; i++) {
            if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                result.add(streamTokenizer.nval);
            } else {
                throw new IllegalArgumentException();
            }
        }
        in.close();
        return result;
    }
}
