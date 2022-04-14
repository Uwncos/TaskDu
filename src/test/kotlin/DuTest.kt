import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import org.apache.commons.io.FileUtils.sizeOfDirectory

class DuTest {
    @Test
    fun getSizeTest1() {
        val dir = File("src\\files\\directory1")
        val file1 = File("src\\files\\file1")
        val file2 = File("src\\files\\file2")
        val du = Du(false, false, false)

        val size1 = du.getSizeKB("src\\files\\directory1")
        assertEquals(size1 , (sizeOfDirectory(dir) / 1024.0 * 10).toInt() / 10.0)
        val size2 = du.getSizeKB("src\\files\\file1")
        assertEquals(size2 , (file1.length() / 1024.0 * 10).toInt() / 10.0)
        val size3 = du.getSizeKB("src\\files\\file2")
        assertEquals(size3 , (file2.length() / 1024.0 * 10).toInt() / 10.0)
    }


    @Test
    fun getSizeTest2() {
        val dir = File("src\\files\\directory1")
        val file1 = File("src\\files\\file1")
        val file2 = File("src\\files\\file2")
        val du = Du(false, false, true)

        val size1 = du.getSizeKB("src\\files\\directory1")
        assertEquals(size1 , (sizeOfDirectory(dir) / 1000.0 * 10).toInt() / 10.0)
        val size2 = du.getSizeKB("src\\files\\file1")
        assertEquals(size2 , (file1.length() / 1000.0 * 10).toInt() / 10.0)
        val size3 = du.getSizeKB("src\\files\\file2")
        assertEquals(size3 , (file2.length() / 1000.0 * 10).toInt() / 10.0)
    }
}