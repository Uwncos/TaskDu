

import java.io.*
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder

//class Du(
//    private val humanForm: Boolean,
//    private val totalSize: Boolean,
//    private val base: Boolean,
////    private val filesNames: List<String>
//) {

fun getSizeKB(fileStr: String): Long {
    val file = File(fileStr)
    val sizeKB = (file.length() / 1024)
    return sizeKB
}


fun getSizesInHumanForm(filesNames: List<String>): List<String> {
    val sizesInHumanFormat = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        var sizeInHumanFormat = getSizeKB(filesNames[i]).toDouble()
        if (sizeInHumanFormat < 1024) {
            sizeInHumanFormat.toDouble()
            val size = "$sizeInHumanFormat" + " KB"
            sizesInHumanFormat.add(size)
        }
        if (sizeInHumanFormat >= 1024 && sizeInHumanFormat < 1_048_576) {
            sizeInHumanFormat = ((sizeInHumanFormat / 1024.0) * 100).toInt() / 100.0
            val size = "$sizeInHumanFormat" + " MB"
            sizesInHumanFormat.add(size)
        }
        if (sizeInHumanFormat >= 1_048_576) {
            sizeInHumanFormat = ((sizeInHumanFormat / 1048576.0) * 100).toInt() / 100.0
            val size = "$sizeInHumanFormat" + " GB"
            sizesInHumanFormat.add(size)
        }
    }
    return sizesInHumanFormat
}

fun getSizesInHumanFormSI(filesNames: List<String>): List<String> {
    val sizesInHumanFormatSI = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        var sizeInHumanFormat = getSizeKB(filesNames[i]).toDouble()
        if (sizeInHumanFormat < 1000.0) {
            val size = "$sizeInHumanFormat" + " KiB"
            sizesInHumanFormatSI.add(size)
        }
        if (sizeInHumanFormat >= 1000 && sizeInHumanFormat < 1_000_000) {
            sizeInHumanFormat = ((sizeInHumanFormat / 1000.0) * 100).toInt() / 100.0
            val size = "$sizeInHumanFormat" + " MiB"
            sizesInHumanFormatSI.add(size)
        }
        if (sizeInHumanFormat >= 1_000_000) {
            sizeInHumanFormat = ((sizeInHumanFormat / 1000000.0) * 100).toInt() / 100.0
            val size = "$sizeInHumanFormat" + " GiB"
            sizesInHumanFormatSI.add(size)
        }
    }
    return sizesInHumanFormatSI
}

fun getSizes(filesNames: List<String>): List<String> {
    val sizes = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        val size = getSizeKB(filesNames[i])
        sizes.add("$size")
    }
    return sizes
}

fun getTotalSizeKB(filesNames: List<String>): Int {
    val sizes = getSizes(filesNames)
    var totalSizeInKB = 0
    for (i in 0..sizes.size - 1) {
        totalSizeInKB += sizes[i].toInt()
    }
    return totalSizeInKB
}

fun getTotalInHumanForm(filesNames: List<String>): String {
    var totalSizeInHumanForm = getTotalSizeKB(filesNames).toDouble()
    var base = " KB"
    if (totalSizeInHumanForm >= 1024 && totalSizeInHumanForm < 1_048_576) {
        totalSizeInHumanForm = ((totalSizeInHumanForm / 1024.0) * 100).toInt() / 100.0
        base = " MB"
    }
    if (totalSizeInHumanForm >= 1_048_576) {
        totalSizeInHumanForm = ((totalSizeInHumanForm / 1048576.0) * 100).toInt() / 100.0
        base = " GB"
    }
    return "$totalSizeInHumanForm" + base
}

fun getTotalInHumanFormSI(filesNames: List<String>): String {
    var totalSizeInHumanFormSI = getTotalSizeKB(filesNames).toDouble()
    var base = " KiB"
    if (totalSizeInHumanFormSI >= 1000 && totalSizeInHumanFormSI < 1_000_000) {
        totalSizeInHumanFormSI = ((totalSizeInHumanFormSI / 1000.0) * 100).toInt() / 100.0
        base = " MiB"
    }
    if (totalSizeInHumanFormSI >= 1_000_000) {
        totalSizeInHumanFormSI = ((totalSizeInHumanFormSI / 1000000.0) * 100).toInt() / 100.0
        base = " GiB"
    }
    return "$totalSizeInHumanFormSI" + base
}


fun dudu(humanForm: Boolean,totalSize: Boolean, base: Boolean, filesNames: List<String>) {
    val result = StringBuilder()
    if (humanForm && base) {
        result.append(getSizesInHumanFormSI(filesNames))
    } else if (humanForm) {
        result.append(getSizesInHumanForm(filesNames))
    } else {
        result.append(getSizes(filesNames))
    }
    if (totalSize && humanForm && base) {
        result.append(getTotalInHumanFormSI(filesNames))
    } else if (totalSize && humanForm) {
        result.append(getTotalInHumanForm(filesNames))
    } else if (totalSize) {
        result.append(getTotalSizeKB(filesNames))
    }
    println(result)
//    return result
}
fun main(args: Array<String>) {
    DuParser.main(args)
}

//}
