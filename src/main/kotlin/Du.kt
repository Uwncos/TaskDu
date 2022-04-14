import org.apache.commons.io.FileUtils.sizeOfDirectory
import java.io.*

class Du(private val humanForm: Boolean,
         private val totalSize: Boolean,
         private val base: Boolean,
         ) {

    fun getSizeKB(fileStr: String): Double {
        val isBaseSI = isSI(base)
        val file = File(fileStr)
        val sizeKB: Double
        if (file.isDirectory) {
            sizeKB = (sizeOfDirectory(file) / isBaseSI * 10).toInt() / 10.0
        } else {
            sizeKB = (file.length() / isBaseSI * 10).toInt() / 10.0
        }
        return sizeKB
    }

    fun size(sizeKB: Double): String {
        val isBaseSI = isSI(base)
        return if (humanForm) {
            var unit = arrayListOf(" KB", " MB", " GB")
            var sizeKB0 = (sizeKB * 10).toInt() / 10.0
            var k = 0
            if (base) unit = arrayListOf(" KiB", " MiB", " GiB")
            while (sizeKB0 > isSI(base) - 1) {
                sizeKB0 = (sizeKB0 / isBaseSI)
                k += 1
            }
            "" + sizeKB0 +  unit[k]
        } else {
            "" + (sizeKB / isBaseSI)
        }
    }

    fun isSI(base: Boolean): Double {
        var isBaseSI = 1024.0
        if (base) isBaseSI = 1000.0
        return isBaseSI
    }

    fun du(filesNames: List<String>) {

        for (i in filesNames.indices) {
            println(size(getSizeKB(filesNames[i])))
        }

        if (totalSize) {
            var total = 0.0
            for (i in filesNames.indices) {
                total += getSizeKB(filesNames[i])
            }
            print((size(total)))
        }
    }
}
