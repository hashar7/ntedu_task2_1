package com.company;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayVectorImpl implements Serializable, ArrayVector {

    /**
     * Уникальный идентификатор версии сериализованного класса.
     */
    @Serial
    private static final long serialVersionUID = 777L;

    /**
     * Массив с элементами верктора.
     */
    private double[] array;

    /**
     * Коструктор без параметров.
     */
    public ArrayVectorImpl() {
        array = new double[10];
    }

    /**
     * Конструктор от массива.
     * @param elements массив со значениями для {@link ArrayVectorImpl}.
     */
    public ArrayVectorImpl(double[] elements) {
        array = elements.clone();
    }

    /**
     * Конструктор, который создает {@link ArrayVectorImpl} с заданной вместимостью.
     * @param capacity — требуемая вместимость {@link ArrayVectorImpl}.
     */
    public ArrayVectorImpl(int capacity) {
        array = capacity > 0 ? new double[capacity] : new double[10];
    }

    /**
     * Задает все элементы вектора (определяет длину вектора).
     * Передаваемый массив не клонируется.
     *
     * @param elements Не равен null
     */
    @Override
    public void set(double... elements) {
        int length = elements.length;
        array = new double[length];
        System.arraycopy(elements, 0, array, 0, length);
    }

    /**
     * Возвращает все элементы вектора. Массив не клонируется.
     */
    @Override
    public double[] get() {
        return array;
    }

    /**
     * Возвращает копию вектора (такую, изменение элементов
     * в которой не приводит к изменению элементов данного вектора).<br/>
     * Рекомендуется вызвать метод clone() у самого массива или использовать
     * {@link System#arraycopy(Object, int, Object, int, int)}.
     */
    @Override
    public ArrayVector clone() {
        return new ArrayVectorImpl(array.clone());
    }

    /**
     * Возвращает число элементов вектора.
     */
    @Override
    public int getSize() {
        return this.array.length;
    }

    /**
     * Изменяет элемент по индексу.
     *
     * @param index В случае выхода индекса за пределы массива:<br/>
     *              а) если index<0, ничего не происходит;<br/>
     *              б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
     * @param value
     */
    @Override
    public void set(int index, double value) {
        if (index < 0) {
            return;
        } else if (index < array.length) {
            array[index] = value;
            return;
        }
        double[] new_array = new double[index + 1];
        System.arraycopy(array, 0, new_array, 0, array.length);
        array = new_array;
        array[index] = value;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index В случае выхода индекса за пределы массива
     *              должно генерироваться ArrayIndexOutOfBoundsException
     */
    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Возвращает максимальный элемент вектора.
     */
    @Override
    public double getMax() {
        return Arrays.stream(array).max().getAsDouble();
    }

    /**
     * Возвращает минимальный элемент вектора.
     */
    @Override
    public double getMin() {
        return Arrays.stream(array).min().getAsDouble();
    }

    /**
     * Сортирует элементы вектора в порядке возрастания.
     */
    @Override
    public void sortAscending() {
        Arrays.sort(array);
    }

    /**
     * Умножает вектор на число.<br/>
     * Замечание: не пытайтесь использовать безиндексный цикл foreach:
     * для изменения элемента массива нужно знать его индекс.
     *
     * @param factor
     */
    @Override
    public void mult(double factor) {
        int length = array.length;
        IntStream.range(0, length).forEachOrdered(i -> array[i] *= factor);
    }

    /**
     * Складывает вектор с другим вектором, результат запоминает в элементах данного вектора.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются<br/>
     * (если данный вектор - больший, его размер менять не надо, просто не меняйте последние элементы).
     *
     * @param anotherVector Не равен null
     * @return Ссылка на себя (результат сложения)
     */
    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        int length = Math.min(array.length, anotherVector.getSize());
        IntStream.range(0, length).forEachOrdered(i -> array[i] += anotherVector.get(i));
        return this;
    }

    /**
     * Возвращает скалярное произведение двух векторов.<br/>
     * Если векторы имеют разный размер, последние элементы большего вектора не учитываются.
     *
     * @param anotherVector Не равен null
     */
    @Override
    public double scalarMult(ArrayVector anotherVector) {
        int length = Math.min(array.length, anotherVector.getSize());
        return IntStream.range(0, length).mapToDouble(i -> array[i] * anotherVector.get(i)).sum();
    }

    /**
     * Возвращает евклидову норму вектора (длину вектора
     * в n-мерном евклидовом пространстве, n={@link #getSize()}).
     * Это можно подсчитать как корень квадратный от скалярного произведения вектора на себя.
     */
    @Override
    public double getNorm() {
        return Math.sqrt(this.scalarMult(this));
    }

    @Override
    public String toString() {
        return "ArrayVectorImpl{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayVectorImpl)) return false;
        ArrayVectorImpl that = (ArrayVectorImpl) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}