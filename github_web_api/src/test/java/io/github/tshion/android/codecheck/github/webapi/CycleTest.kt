package io.github.tshion.android.codecheck.github.webapi

import jdepend.framework.JDepend
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * 循環参照が無いかの検証
 *
 * * 参考文献: [ソフトウェアアーキテクチャ・ハードパーツ](https://www.oreilly.co.jp//books/9784814400065/) のp.10
 */
class CycleTest {

    private lateinit var jDepend: JDepend


    @Before
    fun setupBeforeTest() {
        jDepend = JDepend().apply {
            addDirectory("build/libs")
        }
    }


    @Test
    fun testAllPackages() {
        val packages = jDepend.analyze()
        assertEquals("Cycles exist", false, jDepend.containsCycles())
    }
}
