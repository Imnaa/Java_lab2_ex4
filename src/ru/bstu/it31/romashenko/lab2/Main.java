package ru.bstu.it31.romashenko.lab2;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.*;

/**
 * <p>Главный класс программы.</p>
 *
 * @author Ромащенко Н.А.
 * @version 1.0
 * Дата: 21.02.19
 *
 * Имя класса: Main
 * Назначение: Дана последовательность целых чисел а1,а2,...,ап. Выяснить, какое число встречается раньше — положительное или отрицательное.
 */
public class Main {
    /** */
    static final Logger Logger = LogManager.getLogger(Main.class);

    /**
     * <p>Точка входа в программу</p>
     *
     * @param args - аргументы для метода
     */
    public static void main(String[] args) throws IOException {
        Logger.debug("Lets");

        System.out.println("Работа с массивом.");
        System.out.println("\t> 1. Ввести с клавиатуры;");
        System.out.println("\t> 2. Считать из файла;");
        System.out.println("\t> 9. Выход.");
        // Режим ввода данных
        // 1 - клавиатура
        // 2 - файл
        // 9 - выход

        int intArray[];

        int k = 0;

        // Инициализация объекта "Сканер"
        Logger.debug("Инициализация сканера");
        Scanner scanner = new Scanner(System.in);

        int mode = scanner.nextInt();
        Logger.debug("Пользователь ввел mode = " + mode);

        // Обработка режима работы
        switch (mode) {
            // Клавиатура
            case 1: {
                Logger.info("Пользователь выбрал режим работы 'чтение с консоли'");
                // Ввод сторон треугольника
                System.out.print("Количество символов в массиве: ");
                //
                k = scanner.nextInt();
                Logger.debug("Пользователь ввел k = " + k);
                //
                scanner.close();
                //
                intArray = new int[k];
                Logger.debug("Выделена память под массив из k = " + k);
                //
                intArray = getArrayRand(k, -100, 100);
                Logger.debug("Инициализирован массив");
                break;
            }

            // Файл
            case 2: {
                Logger.info("Пользователь выбрал режим работы 'чтение из файла'");
                // Функция для считывания из файла
                FileInputStream inFile = new FileInputStream("ex4.txt");
                //
                byte[] str = new byte[inFile.available()];
                //
                inFile.read(str);
                //
                String text = new String(str);
                //
                //String[] numbers = text.split("([^-\\d])|(-\\D)");
                String[] numbers = text.split("([\\s])");
                //
                k = Integer.parseInt(numbers[0]);
                Logger.debug("Считано из файла: k = " + k);
                //
                intArray = new int[k];
                Logger.debug("Выделена память под массив из k = " + k);
                //
                int pos = 1;
                //
                for (int i = 0; i < intArray.length; ++i, ++pos) {
                    //
                    if (!"".equals(numbers[pos])) {
                        //
                        intArray[i] = Integer.parseInt(numbers[pos]);
                    }
                }
                Logger.debug("Инициализирован массив");
                break;
            }

            // Выход
            case 9: {
                Logger.info("Пользователь выбрал режим работы 'выход'");
                return;
            }

            // Ошибка ввода
            default: {
                Logger.warn("Выбран не верный параметр, программа завершила свою работу.");
                System.out.println("Неправильный ввод.");
                return;
            }
        }
        printArrayConsole(intArray, k);
        //
        checkEx(intArray);
        Logger.debug("The end.");
    }

    /**
     * Метод для получения массива
     *
     * @param size размер массива
     * @param rMin левая граница рандома
     * @param rMax правая граница рандома
     * @return возврат массива рандомного
     */
    private static int[] getArrayRand(int size, int rMin, int rMax) {
        Logger.debug("Начало генерации случайных чисел: [" + rMin + ";" + rMax + "].");
        int intArray[] = new int[size];

        for (int i = 0; i < intArray.length; i++) {
            // рандом в рэндже [rMin;rMax]
            intArray[i] = (int) (Math.random() * (rMax - rMin + 1) + rMin);
        }
        Logger.debug("Успешная генерация");
        return intArray;
    }

    /**
     * Метод для вывода в консоль массива
     *
     * @param arr  одномерный массив
     * @param size размер массива
     */
    private static void printArrayConsole(int[] arr, int size) {
        Logger.debug("Начало вывода элементов массива на консоль");
        for (int i = 0; i < arr.length; ++i) {
            if ((i + 1) % 5 == 0) {
                System.out.println("A[" + i + "] = " + arr[i] + '\t');
            } else {
                System.out.print("A[" + i + "] = " + arr[i] + '\t');
            }
        }
        System.out.println();
    }

    /**
     * Метод для проверки массива согласно заданию
     *
     * @param intArray одномерный массив
     */
    private static void checkEx(int[] intArray) {
        Logger.debug("Проверка на положительность или отрицатльность началась");

        for (int i = 0; i < intArray.length; ++i) {
            if (0 != intArray[i]) {
                if (0 > intArray[i]) {
                    Logger.debug("Первее встречается отрицательное число. Индекс: " + i);
                    System.out.println("Первее встречается отрицательное число. Индекс: " + i);
                } else {
                    Logger.debug("Первее встречается положительное число. Индекс: " + i);
                    System.out.println("Первее встречается положительное число. Индекс: " + i);
                }
                return;
            }
        }
        Logger.warn("В массиве одни нули!");
        System.out.println("В массиве одни нули.");
    }
}