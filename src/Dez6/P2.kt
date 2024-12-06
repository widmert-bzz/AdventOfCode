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
    var posX = 0;
    var posY = 0;
    for (x in list.indices) {
        for (y in list.indices) {
            if (list[x][y] == '^') {

                posX = x
                posY = y
            }
        }
    }
    calcPath(posX, posY)
}

private fun calcPath(x: Int, y: Int) {
    var posX = x;
    var posY = y;

    var checkPosX = x;
    var checkPosY = y;

    var loops = 0;

    checkPosX--

    while (isValidPos(checkPosX, checkPosY)) {
        if (list[checkPosX][checkPosY] == '.') {
            posX = checkPosX
            posY = checkPosY
            list[posX][posY] = getSymbolForDir()
        } else if (list[checkPosX][checkPosY] == '#') {
            extendPathBack(posX, posY)
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

    println()
    println()
    println()

    checkPosX = x;
    checkPosY = y;

    dir = 0

    while (isValidPos(checkPosX, checkPosY)) {
        if (list[checkPosX][checkPosY] == '.' || list[checkPosX][checkPosY] == getSymbolForDir()) {
            posX = checkPosX
            posY = checkPosY
            list[posX][posY] = getSymbolForDir()
        } else if (list[checkPosX][checkPosY] == getNextSymbolForDir()) {
            posX = checkPosX
            posY = checkPosY
            list[checkPosX][checkPosY] = 'O'
            loops++
        } else if (list[checkPosX][checkPosY] == '#') {
            extendPathBack(posX, posY)
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

    print(loops)
}

private fun isValidPos(x: Int, y: Int): Boolean {
    return x >= 0 && x < list.size && y >= 0 && y < list[0].size
}

private fun getSymbolForDir(): Char {
    when (dir) {
        0 -> {
            return '^'
        }

        1 -> {
            return '>'
        }

        2 -> {
            return 'v'
        }

        3 -> {
            return '<'
        }
    }
    print("error")
    return 'X'
}

private fun getNextSymbolForDir(): Char {
    when (dir) {
        0 -> {
            return '>'
        }

        1 -> {
            return 'v'
        }

        2 -> {
            return '<'
        }

        3 -> {
            return '^'
        }
    }
    print("error")
    return 'X'
}

private fun extendPathBack(x: Int, y: Int) {
    var posX = x;
    var posY = y;

    while (true) {
        when (dir) {
            0 -> {
                posX++
            }

            1 -> {
                posY--
            }

            2 -> {
                posX--
            }

            3 -> {
                posY++
            }
        }
        if (!isValidPos(posX, posY)) {
            break
        } else if (list[posX][posY] == '#') {
            break
        } else {
            list[posX][posY] = getSymbolForDir()

        }
    }

}