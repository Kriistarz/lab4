package lab4;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    Выберите номер теста:
                    1 - Box
                    2 - Storage<Integer> (null)
                    3 - Storage<Integer>
                    4 - Storage<String> (null)
                    5 - Storage<String>
                    6 - Search Maximum
                    7 - Transform
                    8 - Filter
                    9 - Reducer
                    10 - CollectorUtil
                    0 - Выход
                   
                    """);
            System.out.print("Введите номер: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число!");
                continue;
            }

            if (choice == 0) {
                System.out.println("Выход из программы...");
                break;
            }

            System.out.println("\nРезультат ");
            switch (choice) {
                case 1 -> testBox();
                case 2 -> testStorageIntegerNull();
                case 3 -> testStorageInteger();
                case 4 -> testStorageStringNull();
                case 5 -> testStorageString();
                case 6 -> testSearchMaximum();
                case 7 -> testTransform();
                case 8 -> testFilter();
                case 9 -> testReducer();
                case 10 -> testCollectorUtil();
                default -> System.out.println("Неверный номер теста.");
            }
            System.out.println("\n");
        }
    }

    // ========================== Тесты ==========================

    public static void testCollectorUtil() {
        // Разделяем числа на положительные и отрицательные
        List<Integer> numbers = List.of(1, -3, 7);
        Map<String, List<Integer>> groupedNums = CollectorUtil.collect(
                numbers,
                HashMap::new,
                (map, n) -> {
                    String key = n > 0 ? "positive" : "negative";
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(n);
                }
        );
        System.out.println(groupedNums);

        // Группируем строки по длине
        List<String> strings = List.of("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> groupedByLength = CollectorUtil.collect(
                strings,
                HashMap::new,
                (map, s) -> map.computeIfAbsent(s.length(), k -> new ArrayList<>()).add(s)
        );
        System.out.println(groupedByLength);

        // Собираем уникальные строки
        List<String> withDuplicates = List.of("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniqueStrings = CollectorUtil.collect(
                withDuplicates,
                HashSet::new,
                Set::add
        );
        System.out.println(uniqueStrings);
    }

    public static void testReducer() {
        // Склеиваем строки
        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        String combined = Reducer.reduce(strings, "", (a, b) -> a + b);
        System.out.println(combined);

        // Суммируем числа
        List<Integer> numbers = Arrays.asList(1, -3, 7);
        Integer sum = Reducer.reduce(numbers, 0, Integer::sum);
        System.out.println(sum);

        // Считаем количество элементов во вложенных списках
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6)
        );
        Integer totalCount = Reducer.reduce(
                listOfLists,
                Collections.emptyList(),
                (a, b) -> {
                    List<Integer> combinedNumbers = new ArrayList<>(a);
                    combinedNumbers.addAll(b);
                    return combinedNumbers;
                }
        ).size();
        System.out.println(totalCount);
    }

    public static void testFilter() {
        // Строки длиной >= 3
        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        List<String> filteredStrings = FilterUtil.filter(strings, s -> s.length() >= 3);
        System.out.println(filteredStrings);

        // Отрицательные числа
        List<Integer> numbers = Arrays.asList(1, -3, 7);
        List<Integer> filteredNumbers = FilterUtil.filter(numbers, n -> n <= 0);
        System.out.println(filteredNumbers);

        // Массивы без положительных чисел
        List<int[]> arrays = Arrays.asList(
                new int[]{1, -2, 3},
                new int[]{-5, -10},
                new int[]{0, -1, 2}
        );
        List<int[]> filteredArrays = FilterUtil.filter(arrays, arr -> {
            for (int num : arr) {
                if (num > 0) return false;
            }
            return true;
        });

        for (int[] arr : filteredArrays) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void testTransform() {
        // Длина каждой строки
        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        List<Integer> lengths = TransformUtil.transform(strings, String::length);
        System.out.println(lengths);

        // Все числа делаем положительными
        List<Integer> numbers = Arrays.asList(1, -3, 7);
        List<Integer> positives = TransformUtil.transform(numbers, Math::abs);
        System.out.println(positives);

        // Максимум в каждом массиве
        List<int[]> arrays = Arrays.asList(
                new int[]{1, 5, 2},
                new int[]{-3, -7, 0},
                new int[]{10, 2, 8}
        );
        List<Integer> maxValues = TransformUtil.transform(arrays, arr -> {
            int max = arr[0];
            for (int num : arr) {
                if (num > max) max = num;
            }
            return max;
        });
        System.out.println(maxValues);
    }

    public static void testSearchMaximum() {
        List<Box<? extends Number>> boxes = List.of(
                new Box<>(12),
                new Box<>(2.1),
                new Box<>(0.00001),
                new Box<>(0.000002),
                new Box<>(-90),
                new Box<>(67),
                new Box<>(67.1),
                new Box<>(2),
                new Box<>(32)
        );
        System.out.println(BoxUtil.maximum(boxes));
    }

    // Общий метод для тестов класса Storage
    private static <T> void testStorage(T storedValue, T defaultValue) {
        Storage<T> storage = new Storage<>(storedValue);
        System.out.println(storage.get(defaultValue));
    }

    public static void testStorageString() {
        testStorage("hello", "hello world");
    }

    public static void testStorageStringNull() {
        testStorage(null, "default");
    }

    public static void testStorageInteger() {
        testStorage(99, -1);
    }

    public static void testStorageIntegerNull() {
        testStorage(null, 0);
    }

    public static void testBox() {
        Box<Integer> box = new Box<>();
        box.set(3);
        Integer value = box.get(); // достаём значение из коробки
        System.out.println("Извлечённое значение: " + value);
        Integer value1 = box.get(); // достаём значение из коробки
        System.out.println("Извлечённое значение: " + value1);
    }
}
