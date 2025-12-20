package com.seachange.sudoku

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class GridTest {

    @Test
    fun `should be able to create a 9 by 9 grid`() {
        val grid = createGrid()
        assertThat(grid.size).isEqualTo(9)
        assertThat(grid[0].size).isEqualTo(9)
    }

@Test
fun `should be able to set and get a value from a grid`() {
    val grid = createGrid()
    grid.set(2, 4, 42)
    assertThat(grid.get(2, 4)).isEqualTo(42)
}

}