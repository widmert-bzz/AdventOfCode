package Dez4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class P2 {

    static ArrayList<char[]> list = new ArrayList<>();

    static int[] dirX = {-1, -1, 1, 1};
    static int[] dirY = {-1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        readFile();
        System.out.println(countXMas());
    }

    static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/gd860/IdeaProjects/AoC24/src/Dez4/P1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.toCharArray());
            }
        }
    }

    static int countXMas() {
        int result = 0;
        for (int x = 0; x < list.size(); x++) {
            for (int y = 0; y < list.get(x).length; y++) {
                if (checkDir(x, y)) {
                    result++;
                }
            }
        }
        return result;
    }


    static boolean checkDir(int x, int y) {
        boolean isValid = false;
        if (list.get(x)[y] == 'A') {
            if (!isValidPos(x + 1, y + 1)) {
                return false;
            }
            if (!isValidPos(x - 1, y + 1)) {
                return false;
            }
            if (!isValidPos(x + 1, y - 1)) {
                return false;
            }
            if (!isValidPos(x - 1, y - 1)) {
                return false;
            }

            char lastChar = 'Q';
            int countM = 0;
            for (int i = 0; i < 4; i++) {
                char current = list.get(x + dirX[i])[y + dirY[i]];
                if (current == 'M' || current == 'S') {
                    if (lastChar == current) {
                        isValid = true;
                    }
                    lastChar = current;
                    if (current == 'M') {
                        countM++;
                    }
                } else {
                    return false;
                }
            }
            return isValid && countM == 2;
        }
        return false;
    }

    static boolean isValidPos(int x, int y) {
        return x >= 0 && x < list.size() && y >= 0 && y < list.get(0).length;
    }
}
