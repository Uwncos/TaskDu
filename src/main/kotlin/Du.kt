
import java.io.*
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder

fun getSizeKB(fileStr: String): Long {
    val file = File(fileStr)
    val sizeKB = (file.length() / 1024)
    return sizeKB
}

fun isSI(base: Boolean): Int {
    var isBaseSI = 1024
    if (base) isBaseSI = 1000
    return isBaseSI
}


fun units(base: Boolean): List<String> {
    var isUnitsSI = arrayListOf<String>(" KB", " MB", "GB")
    if (base) isUnitsSI = arrayListOf(" KiB", " MiB", "GiB")
    return isUnitsSI
}


fun getSizesInHumanForm(filesNames: List<String>, base: Boolean): List<String> {
    val isUnitsSI = units(base)
    val isBaseSI = isSI(base).toDouble()
    val sizesInHumanFormat = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        var sizeInHumanFormat = getSizeKB(filesNames[i]).toDouble()
        if (sizeInHumanFormat < isBaseSI) {
            val size = "$sizeInHumanFormat" + isUnitsSI[0]
            sizesInHumanFormat.add(size)
        }
        if (sizeInHumanFormat >= isBaseSI && sizeInHumanFormat < isBaseSI * isBaseSI) {
            sizeInHumanFormat = ((sizeInHumanFormat / isBaseSI) * 100).toInt() / 100.0
            val size = "$sizeInHumanFormat" + isUnitsSI[1]
            sizesInHumanFormat.add(size)
        }
        if (sizeInHumanFormat >= isBaseSI * isBaseSI) {
            sizeInHumanFormat = ((sizeInHumanFormat / (isBaseSI * isBaseSI)) * 100).toInt() / 100.0
            val size = "$sizeInHumanFormat" + isUnitsSI[2]
            sizesInHumanFormat.add(size)
        }
    }
    return sizesInHumanFormat
}

fun getSizesKB(filesNames: List<String>): List<String> {
    val sizes = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        val size = getSizeKB(filesNames[i])
        sizes.add("$size")
    }
    return sizes
}

fun getTotalSizeKB(filesNames: List<String>): Int {
    val sizes = getSizesKB(filesNames)
    var totalSizeInKB = 0
    for (i in 0..sizes.size - 1) {
        totalSizeInKB += sizes[i].toInt()
    }
    return totalSizeInKB
}

fun getTotalInHumanForm(filesNames: List<String>, base: Boolean): String {
    val isUnitsSI = units(base)
    val isBaseSI = isSI(base).toDouble()
    var totalSizeInHumanForm = getTotalSizeKB(filesNames).toDouble()
    var unit = isUnitsSI[0]
    if (totalSizeInHumanForm >= isBaseSI && totalSizeInHumanForm < isBaseSI * isBaseSI) {
        totalSizeInHumanForm = ((totalSizeInHumanForm / isBaseSI) * 100).toInt() / 100.0
        unit = isUnitsSI[1]
    }
    if (totalSizeInHumanForm >= isBaseSI * isBaseSI) {
        totalSizeInHumanForm = ((totalSizeInHumanForm / (isBaseSI * isBaseSI)) * 100).toInt() / 100.0
        unit = isUnitsSI[2]
    }
    return "$totalSizeInHumanForm" + unit
}

fun du(humanForm: Boolean, totalSize: Boolean, base: Boolean, filesNames: List<String>) {
    val result = StringBuilder()
    if (humanForm && base) {
        result.append(getSizesInHumanForm(filesNames, base))
    } else {
        result.append(getSizesKB(filesNames))
    }
    if (totalSize && humanForm && base) {
        result.append(getTotalInHumanForm(filesNames, base))
    } else if (totalSize) {
        result.append(getTotalSizeKB(filesNames))
    }
    println(result)
}
fun main(args: Array<String>) {
    DuParser.main(args)
}


