package Dez2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class P1 {

    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int result = 0;

        readFile();
        for (int i = 0; i < list.size(); i++) {
            if (checkIfSafe(list.get(i))) {
                result++;
            }
        }

        System.out.print(result);
    }

    static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\widme\\IdeaProjects\\AdventOfCode24\\src\\Dez2\\P1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                int[] intArray = Arrays.stream(numbers)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                list.add(intArray);
            }
        }
    }

    static boolean checkIfSafe(int[] arr) {
        boolean isAsc = arr[0] < arr[1];

        //0 1

        for (int i = 0; i < arr.length - 1; i++) {
            if (isAsc) {
                if (!(arr[i] < arr[i + 1] && arr[i] > arr[i + 1] - 4)) {
                    return checkIfSafeDampener(removeElement(arr, i)) || checkIfSafeDampener(removeElement(arr, i + 1));
                }
            } else {
                if (!(arr[i] > arr[i + 1] && arr[i] < arr[i + 1] + 4)) {
                    return checkIfSafeDampener(removeElement(arr, i)) || checkIfSafeDampener(removeElement(arr, i + 1));
                }
            }
        }
        return true;
    }

    static boolean checkIfSafeDampener(int[] arr) {
        boolean isAsc = arr[0] < arr[1];

        for (int i = 0; i < arr.length - 1; i++) {
            if (isAsc) {
                if (!(arr[i] < arr[i + 1] && arr[i] > arr[i + 1] - 4)) {
                    return false;
                }
            } else {
                if (!(arr[i] > arr[i + 1] && arr[i] < arr[i + 1] + 4)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int[] removeElement(int[] arr, int index) {
        int[] newArr = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[j++] = arr[i];
            }
        }

        return newArr;
    }
}
