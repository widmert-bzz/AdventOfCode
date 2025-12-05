package Year_2024.Dez3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P1 {

    static String file = "";
    static String regex = "mul\\(([1-9][0-9]{0,2}),([1-9][0-9]{0,2})\\)|do\\(\\)|don't\\(\\)";

    public static void main(String[] args) throws IOException {
        int result = 0;

        readFile();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(file);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        boolean isEnabled = true;
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).equals("don't()")) {
                isEnabled = false;
            } else if (matches.get(i).equals("do()")) {
                isEnabled = true;
            } else if (isEnabled) {
                String numbers = matches.get(i).replace("mul(", "").replace(")", "");
                result += Integer.parseInt(numbers.split(",")[0]) * Integer.parseInt(numbers.split(",")[1]);
            }
        }

        System.out.println(result);
    }

    static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Tino\\IdeaProjects\\AoC24\\src\\Dez3\\P1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                file += line;
            }
        }
    }


}
