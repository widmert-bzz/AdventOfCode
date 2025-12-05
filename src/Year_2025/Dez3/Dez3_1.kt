package Year_2025.Dez3

import java.io.File


private val lines: List<String> =
    File("C:\\Users\\widme\\IdeaProjects\\AdventOfCode\\src\\Year_2025\\Dez3\\input.txt").readLines()
private var result = 0


private fun main() {
    for (line: String in lines) {
        var maxNumber1 = line[0].digitToInt();
        var maxNumber1Index = 0;
        var maxNumber2 = 0;

        for (index in 1..(line.length - 2)) {
            if (line[index].digitToInt() > maxNumber1) {
                maxNumber1 = line.toCharArray()[index].digitToInt();
                maxNumber1Index = index;
            }
        }

        val substring = line.substring(maxNumber1Index + 1)
        for (index in (maxNumber1Index + 1)..(maxNumber1Index + substring.length)) {
            if (line.toCharArray()[index].digitToInt() > maxNumber2) {
                maxNumber2 = line.toCharArray()[index].digitToInt();
            }
        }
        result += (maxNumber1.toString() + maxNumber2.toString()).toInt();
    }
    println(result)
}