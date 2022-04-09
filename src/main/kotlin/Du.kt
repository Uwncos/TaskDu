
import java.io.*
import kotlin.collections.ArrayList
import kotlin.text.StringBuilder
import org.apache.commons.io.FileUtils.sizeOfDirectory

fun getSizeKB(fileStr: String, base: Boolean): Double {
    val isBaseSI = isSI(base)
    val file = File(fileStr)
    val sizeKB: Double
    if (file.isDirectory) {
        sizeKB = (sizeOfDirectory(file) / isBaseSI * 10).toInt() / 10.0
    }
    else {
        sizeKB = (file.length() / isBaseSI * 10).toInt() / 10.0
    }
    return sizeKB
}

fun isSI(base: Boolean): Double {
    var isBaseSI = 1024.0
    if (base) isBaseSI = 1000.0
    return isBaseSI
}


fun units(base: Boolean): List<String> {
    var isUnitsSI = arrayListOf<String>(" KB", " MB", " GB")
    if (base) isUnitsSI = arrayListOf(" KiB", " MiB", " GiB")
    return isUnitsSI
}


fun getSizesInHumanForm(filesNames: List<String>, base: Boolean): List<String> {
    val isUnitsSI = units(base)
    val isBaseSI = isSI(base)
    val sizesInHumanFormat = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        var sizeInHumanFormat = getSizeKB(filesNames[i], base)
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

fun getSizesKB(filesNames: List<String>, base: Boolean): List<String> {
    val sizes = ArrayList<String>()
    for (i in 0..filesNames.size - 1) {
        val size = getSizeKB(filesNames[i], base)
        sizes.add("$size")
    }
    return sizes
}

fun getTotalSizeKB(filesNames: List<String>, base: Boolean): Double {
    val sizes = getSizesKB(filesNames, base)
    var totalSizeInKB = 0.0
    for (i in 0..sizes.size - 1) {
        totalSizeInKB += sizes[i].toDouble()
    }
    return (totalSizeInKB * 10).toInt() / 10.0
}

fun getTotalInHumanForm(filesNames: List<String>, base: Boolean): String {
    val isUnitsSI = units(base)
    val isBaseSI = isSI(base)
    var totalSizeInHumanForm = getTotalSizeKB(filesNames, base)
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

fun du(humanForm: Boolean, totalSize: Boolean, base: Boolean, filesNames: List<String>): StringBuilder {
    val result = StringBuilder()
    if ((humanForm && base) || (humanForm)) {
        result.append(getSizesInHumanForm(filesNames, base))
        val sizes = getSizesInHumanForm(filesNames, base)
        for (i in 0..sizes.size - 1) println("File number $i: " + sizes[i])
    } else {
        result.append(getSizesKB(filesNames, base))
        val sizes = getSizesKB(filesNames, base)
        for (i in 0..sizes.size - 1) println("File number $i: " + sizes[i])
    }

    if ((totalSize && humanForm && base) || (totalSize && humanForm)) {
        result.append(getTotalInHumanForm(filesNames, base))
        println("Total: " + getTotalInHumanForm(filesNames, base))
    } else if (totalSize) {
        result.append(getTotalSizeKB(filesNames, base))
        println("Total: " + getTotalSizeKB(filesNames, base))
    }
    return result
}
fun main(args: Array<String>) {
    DuParser.main(args)
}


