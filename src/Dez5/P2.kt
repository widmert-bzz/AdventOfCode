package Dez5

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.math.floor

private var inputs = ArrayList<String>()
private var rules = ArrayList<IntArray>()
private var updates = ArrayList<IntArray>()
private var incorrectUpdates = ArrayList<IntArray>()


private fun main() {
    readFile()
    proccessFile()
    process()
    sortWrongUpdates()
}

private fun readFile(): Boolean {
    val file = File("/Users/gd860/IdeaProjects/AoC24/src/Dez5/P1.txt")
    if (!file.exists() || !file.canRead()) {
        println("File does not exist or cannot be read.")
        return false
    }

    BufferedReader(FileReader(file)).use { reader ->
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.let { inputs.add(it) }
        }
    }
    return true
}

private fun proccessFile() {
    var rulesFinished = false;
    for (input in inputs) {
        if (input == "") {
            rulesFinished = true
        } else if (!rulesFinished) {
            val arr = IntArray(2)
            arr[0] = input.split("|")[0].toInt()
            arr[1] = input.split("|")[1].toInt()
            rules.add(arr)
        } else {
            val strArr = input.split(",")
            val intArr: IntArray = strArr.map { it.toInt() }.toIntArray()
            updates.add(intArr)
        }
    }
}

private fun process() {
    for (update in updates) {
        var isValid = true;
        for (i in update.indices) {
            for (rule in rules) {
                if (update[i] == rule[0]) {
                    var j = 0;
                    while (i >= j) {
                        if (update[j] == rule[1]) {
                            isValid = false
                        }
                        j++
                    }
                }
            }
        }
        if (!isValid) {
            incorrectUpdates.add(update)
        }
    }
}

private fun sortWrongUpdates() {
    var output = 0
    for (incorrectUpdate in incorrectUpdates) {
        var x = 0
        while (x < incorrectUpdate.size) {
            for (i in incorrectUpdate.indices) {
                for (rule in rules) {
                    if (incorrectUpdate[i] == rule[0]) {
                        var j = 0;
                        while (i >= j) {
                            if (incorrectUpdate[j] == rule[1]) {
                                val value = incorrectUpdate[i]
                                incorrectUpdate[i] = incorrectUpdate[j]
                                incorrectUpdate[j] = value
                                break
                            }
                            j++
                        }
                    }
                }
            }
            x++
        }

        for (i in incorrectUpdate) {
            print(i)
            print(",")
        }

        println()
        output += incorrectUpdate[floor(incorrectUpdate.size / 2f).toInt()]
    }
    print(output)
}


