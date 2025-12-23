package com.seachange.sudoku

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class GridTest {

    private val grid: Grid = buildGrid()

    @Test
    fun `should be able to create a 9 by 9 grid`() {
        assertThat(grid.size).isEqualTo(9)
        assertThat(grid[0].size).isEqualTo(9)
    }

    @Test
    fun `should be able to set and get a value from a grid`() {
        grid.set(2, 4, 42)
        assertThat(grid.get(2, 4)).isEqualTo(42)
    }

    @Test
    fun `a space should be represented by a 0 value`() {
        grid.set(0, 2, 0)
        grid.set(0, 3, 42)
        assertThat(grid.isSpace(0, 2)).isTrue()
        assertThat(grid.isSpace(0, 3)).isFalse()
    }

    private fun buildGrid(): Grid = createGrid()

}