package Year_2024.Dez6

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

private var list = ArrayList<CharArray>()


private var initialGuardPosX = 0
private var initialGuardPosY = 0

private fun main() {
    readFile()
    findGuard()
    print(walkPath(false, 0, initialGuardPosX, initialGuardPosY))
}


@Throws(IOException::class)
private fun readFile() {
    val file = File("/Users/gd860/IdeaProjects/AoC24/src/Year_2024.Dez6/P1.txt")
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
                initialGuardPosX = x
                initialGuardPosY = y
            }
        }
    }
}

private fun walkPath(isCheckingIfLoop: Boolean, InitDir: Int, x: Int, y: Int): Int {
    var dir = InitDir

    var posX = x;
    var posY = y;

    var checkPosX = x;
    var checkPosY = y;

    var steps = 0;
    var count = 0;

    while (isValidPos(checkPosX, checkPosY) && steps < 40) {
        for (i in list.indices) {
            for (j in list[i].indices) {
                if (list[i][j] == 'X') {
                    list[i][j] = '.'
                }
            }
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

        if (!isValidPos(checkPosX, checkPosY)) {
            break
        }

        if (list[checkPosX][checkPosY] == '.' || list[checkPosX][checkPosY] == 'X' || list[checkPosX][checkPosY] == 'O'|| list[checkPosX][checkPosY] == '^') {
            posX = checkPosX
            posY = checkPosY
            if (isCheckingIfLoop && list[posX][posY] != 'O') {
                list[posX][posY] = 'X'
            } else {
                list[posX][posY] = 'O'
            }

            if (!isCheckingIfLoop) {
                if (dir != 3) {
                    count += walkPath(true, (dir + 1), posX, posY)
                } else {
                    count += walkPath(true, 0, posX, posY)
                }
            }
        } else if (list[checkPosX][checkPosY] == '#') {
            if (dir != 3) {
                dir++;
            } else {
                dir = 0
            }
            checkPosX = posX
            checkPosY = posY
        }

        if (isCheckingIfLoop) {
            steps++
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

    }
    if (!isCheckingIfLoop) {
        return count
    } else if (steps >= 500) {
        println("" + x + " " + y)
        return 1;
    }
    return 0;
}

private fun isValidPos(x: Int, y: Int): Boolean {
    return x >= 0 && x < list.size && y >= 0 && y < list[0].size
}



