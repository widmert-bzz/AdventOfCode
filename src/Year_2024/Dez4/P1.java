package Year_2024.Dez4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class P1 {

    static ArrayList<char[]> list = new ArrayList<>();

    static int[] dirX = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dirY = {1, 1, 1, 0, 0, -1, -1, -1};

    static char[] word = {'X', 'M', 'A', 'S'};

    public static void main(String[] args) throws IOException {
        readFile();
        System.out.println(countXMas());
    }

    static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/gd860/IdeaProjects/AoC24/src/Year_2024.Dez4/P1.txt"))) {
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
                for (int dir = 0; dir < 8; dir++) {
                    if (checkDir(x, y, 0, dir)) {
                        result++;
                    }
                }

            }
        }
        return result;
    }


    static boolean checkDir(int x, int y, int index, int dir) {
        if (index >= word.length) {
            return true;
        }
        if (!isValidPos(x, y)) {
            return false;
        }

        if (list.get(x)[y] == word[index]) {
            return checkDir(x + dirX[dir], y + dirY[dir], index + 1, dir);
        } else {
            return false;
        }
    }

    static boolean isValidPos(int x, int y) {
        return x >= 0 && x < list.size() && y >= 0 && y < list.get(0).length;
    }
}
