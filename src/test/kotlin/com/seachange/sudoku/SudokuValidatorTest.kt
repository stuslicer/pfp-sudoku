package com.seachange.sudoku

import assertk.assertThat
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class SudokuValidatorTest {

    private val solvedGrid = arrayOf(
        // top
        intArrayOf(8, 4, 6,  7, 9, 1,  2, 3, 5),
        intArrayOf(1, 2, 5,  8, 4, 3,  9, 7, 6),
        intArrayOf(7, 3, 9,  5, 6, 2,  8, 1, 4),

        // middle
        intArrayOf(3, 5, 7,  2, 8, 4,  6, 9, 1),
        intArrayOf(6, 9, 4,  3, 1, 5,  7, 2, 8),
        intArrayOf(2, 1, 8,  9, 7, 6,  5, 4, 3),

        // bottom
        intArrayOf(4, 7, 1,  6, 2, 8,  3, 5, 9),
        intArrayOf(5, 8, 2,  1, 3, 9,  4, 6, 7),
        intArrayOf(9, 6, 3,  4, 5, 7,  1, 8, 2),
    )

    private val unsolvedGrid = arrayOf(
        // top
        intArrayOf(0, 0, 6,  0, 9, 0,  0, 0, 0),
        intArrayOf(0, 2, 0,  8, 4, 0,  9, 7, 0),
        intArrayOf(0, 0, 9,  0, 6, 0,  8, 1, 4),

        // middle
        intArrayOf(0, 0, 0,  2, 0, 4,  0, 0, 0),
        intArrayOf(6, 0, 0,  3, 1, 0,  0, 2, 8),
        intArrayOf(0, 0, 8,  9, 0, 0,  5, 0, 3),

        // bottom
        intArrayOf(0, 7, 1,  0, 0, 8,  3, 5, 0),
        intArrayOf(0, 8, 0,  0, 3, 0,  0, 0, 7),
        intArrayOf(0, 0, 3,  0, 0, 7,  1, 0, 0),
    )

    @Test
    fun `should be able to detect if a cell value is valid for its row`() {
        val grid = Grid.createAndLoadGrid(solvedGrid)

        val validator = SudokuValidator(grid)

        grid.set(0, 1, 4)
        assertThat(validator.isRowValid(0)).isTrue()
    }

}