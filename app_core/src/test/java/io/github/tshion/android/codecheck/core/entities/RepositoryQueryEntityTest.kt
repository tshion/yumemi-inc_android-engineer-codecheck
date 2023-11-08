package io.github.tshion.android.codecheck.core.entities

import org.junit.Assert
import org.junit.Test

/**
 * [RepositoryQueryEntity] のユニットテスト
 */
internal class RepositoryQueryEntityTest {

    @Test(expected = IllegalArgumentException::class)
    fun nullを指定してインスタンス生成() {
        RepositoryQueryEntity.newInstance(null, 1)
        Assert.fail()
    }

    @Test(expected = IllegalArgumentException::class)
    fun 空文字を指定してインスタンス生成() {
        RepositoryQueryEntity.newInstance("", 1)
        Assert.fail()
    }

    @Test(expected = IllegalArgumentException::class)
    fun 長すぎるキーワードを指定してインスタンス生成() {
        val word = (0..300).joinToString(separator = "") { "a" }
        RepositoryQueryEntity.newInstance(word, 1)
        Assert.fail()
    }


    @Test(expected = IndexOutOfBoundsException::class)
    fun ゼロを指定してインスタンス生成() {
        RepositoryQueryEntity.newInstance("abc", 0)
        Assert.fail()
    }
}
