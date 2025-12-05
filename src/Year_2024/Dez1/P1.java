package Year_2024.Dez1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class P1 {

    static ArrayList<Integer> left = new ArrayList<>();
    static ArrayList<Integer> right = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFile();
        sort(left);
        sort(right);
        calc_distance(left, right);
    }

    static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\widme\\IdeaProjects\\AdventOfCode24\\src\\Dez1\\P1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                if (numbers.length == 2) {
                    try {
                        left.add(Integer.parseInt(numbers[0]));
                        right.add(Integer.parseInt(numbers[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format: " + line);
                    }
                } else {
                    System.out.println("Unexpected line format: " + line);
                }
            }
        }
    }

    public static void sort(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    public static void calc_distance(ArrayList<Integer> arr, ArrayList<Integer> arr2) {
        int result = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) >= arr2.get(i)) {
                result += (arr.get(i) - arr2.get(i));
            } else {
                result += arr2.get(i) - arr.get(i);
            }
        }
        System.out.print(result);
        ;
    }
}