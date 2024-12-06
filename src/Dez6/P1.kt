package Dez6

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

private fun main() {
    readFile()
    findGuard()
}

private var list = ArrayList<CharArray>()

private var dir = 0;

@Throws(IOException::class)
private fun readFile() {
    val file = File("/Users/gd860/IdeaProjects/AoC24/src/Dez6/P1.txt")
    BufferedReader(FileReader(file)).use { reader ->
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.let { list.add(it.toCharArray()) }
        }
    }
}

private fun findGuard() {
    for (x in list.indices) {
        for (y in list.indices) {
            if (list[x][y] == '^') {
                calcPath(x, y)
            }
        }
    }
}

private fun calcPath(x: Int, y: Int) {
    var posX = x;
    var posY = y;

    var checkPosX = x;
    var checkPosY = y;

    var newSteps = 0;

    checkPosX--

    while (isValidPos(checkPosX, checkPosY)) {
        if (list[checkPosX][checkPosY] == '.') {
            newSteps++
            posX = checkPosX
            posY = checkPosY
            list[posX][posY] = 'X'
        } else if (list[checkPosX][checkPosY] == 'X' || list[checkPosX][checkPosY] == '^') {
            posX = checkPosX
            posY = checkPosY
        } else if (list[checkPosX][checkPosY] == '#') {
            if (dir != 3) {
                dir++;
            } else {
                dir = 0
            }
            checkPosX = posX
            checkPosY = posY
        }

        when (dir) {
            0 -> {
                checkPosX--
            }

            1 -> {
                checkPosY++
            }

            2 -> {
                checkPosX++
            }

            3 -> {
                checkPosY--
            }
        }
    }
    for (row in list) {
        for (col in row) {
            print(col)
        }
        println()
    }

    print(newSteps + 1)
}

private fun isValidPos(x: Int, y: Int): Boolean {
    return x >= 0 && x < list.size && y >= 0 && y < list[0].size
}