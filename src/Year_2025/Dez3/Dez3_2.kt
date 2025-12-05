package Year_2025.Dez3

import java.io.File


private val lines: List<String> =
    File("C:\\Users\\widme\\IdeaProjects\\AdventOfCode\\src\\Year_2025\\Dez3\\input.txt").readLines()
private var result: Long = 0L


private fun main() {
    for (line: String in lines) {
        val numbers: List<Int> = findMaxDigit(line, 11)
        var bigNumber = ""
        for (number in numbers) {
            bigNumber += number.toString()
        }

        result += bigNumber.toLong();
    }
    println(result)
}

private fun findMaxDigit(line: String, digitIndex: Int): List<Int> {
    var maxNumberIndex = 0;
    var maxNumber = 0;

    val lineShort = line.dropLast(digitIndex)
    for (index in 0..<lineShort.length) {
        if (lineShort[index].digitToInt() > maxNumber) {
            maxNumber = lineShort.toCharArray()[index].digitToInt();
            maxNumberIndex = index;
        }
    }
    println(maxNumberIndex)
    return if (digitIndex == 0) {
        listOf(maxNumber)
    } else {
        listOf(maxNumber) + findMaxDigit(line.substring(maxNumberIndex + 1), digitIndex - 1)
    }

}