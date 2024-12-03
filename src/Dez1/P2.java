package Dez1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class P2 {

    static ArrayList<Integer> left = new ArrayList<>();
    static ArrayList<Integer> right = new ArrayList<>();
    static ArrayList<Integer> amountInRightList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFile();
        countInRight();
        calcSolution();
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

    static void countInRight() {
        for (int i = 0; i < left.size(); i++) {
            amountInRightList.add(0);
            for (Integer integer : right) {
                if (Objects.equals(left.get(i), integer)) {
                    amountInRightList.set(i, amountInRightList.get(i) + 1);
                }
            }
        }
    }

    private static void calcSolution() {
        int result = 0;
        for (int i = 0; i < left.size(); i++) {
            result += amountInRightList.get(i) * left.get(i);
        }
        System.out.print(result);
    }

}
