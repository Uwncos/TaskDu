import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class DuTest {
    @Test
    fun getSizeKBTest() {
        assertEquals(getSizeKB("src\\files\\file1", true), 14.8)
        assertEquals(getSizeKB("src\\files\\file1", false), 14.4)
        assertEquals(getSizeKB("src\\files\\directory1", false), 778.9)
        assertEquals(getSizeKB("src\\files\\directory1", true), 797.6)
    }

    @Test
    fun isSITest() {
        assertEquals(isSI(true), 1000.0)
        assertEquals(isSI(false), 1024.0)
    }

    @Test
    fun unitsTest() {
        assertEquals(units(true), listOf(" KiB", " MiB", " GiB"))
        assertEquals(units(false), listOf(" KB", " MB", " GB"))
    }

    @Test
    fun getSizeInHFTest() {
        assertEquals(getSizesInHumanForm(listOf("src\\files\\file1",
            "src\\files\\file2"), false), listOf("14.4 KB", "81.4 KB"))
        assertEquals(getSizesInHumanForm(listOf("src\\files\\file1",
            "src\\files\\file2"), true), listOf("14.8 KiB", "83.4 KiB"))
        assertEquals(getSizesInHumanForm(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), false), listOf("14.4 KB", "81.4 KB", "778.9 KB"))
        assertEquals(getSizesInHumanForm(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), true), listOf("14.8 KiB", "83.4 KiB", "797.6 KiB"))
    }

    @Test
    fun getSizesKBTest() {
        assertEquals(getSizesKB(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), false), listOf("14.4", "81.4", "778.9"))
        assertEquals(getSizesKB(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), true), listOf("14.8", "83.4", "797.6"))
    }

    @Test
    fun getTotalSizeKBTest() {
        assertEquals(getTotalSizeKB(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), true), 895.8)
        assertEquals(getTotalSizeKB(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), false), 874.7)
    }

    @Test
    fun getTotalInHFTest() {
        assertEquals(getTotalInHumanForm(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), false), "874.7 KB")
        assertEquals(getTotalInHumanForm(listOf("src\\files\\file1", "src\\files\\file2",
            "src\\files\\directory1"), true), "895.8 KiB")
    }


}