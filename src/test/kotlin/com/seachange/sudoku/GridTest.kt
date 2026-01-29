package com.seachange.sudoku

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Test

class GridTest {

    private val grid: Grid = buildGrid()

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

    @Test
    fun `should be able to pre-load a grid from an array`() {
        val valuesToLoad = unsolvedGrid
        val result: Grid = Grid.createAndLoadGrid(valuesToLoad)
        assertThat(result).isNotSameAs(valuesToLoad)
    }

    @Test
    fun `the loaded grid should be a deep copy of the values to load`() {
        val valuesToLoad = unsolvedGrid
        val result: Grid = Grid.createAndLoadGrid(valuesToLoad)

        valuesToLoad[0][0] = 9
        assertThat(result.get(0, 0)).isEqualTo(0)
    }

    @Test
    fun `loadGrid must throw an exception if the input array doesn't have 9 rows`() {
        val invalidGrid = arrayOf(
            intArrayOf(0, 0, 6,  0, 9, 0,  0, 0, 0),
            intArrayOf(0, 2, 0,  8, 4, 0,  9, 7, 0),
            intArrayOf(0, 0, 9,  0, 6, 0,  8, 1, 4),
        )

        assertFailure {
            Grid.createAndLoadGrid(invalidGrid)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Input array must have 9 rows")

    }

    @Test
    fun `loadGrid must throw an exception if any of the input array's rows don't have 9 columns`() {
        val invalidGrid = arrayOf(
            // top
            intArrayOf(0, 0, 6,  0, 9, 0,  0, 0, 0),
            intArrayOf(0, 2, 0,  8, 4, 0,  9, 7, 0, 1), // extra
            intArrayOf(0, 0, 9,  0, 6, 0,  8, 1, 4),

            // middle
            intArrayOf(0, 0, 0,  2, 0, 4,  0, 0, 0),
            intArrayOf(6, 0, 0,  3, 1, 0,  0, 2), // missing
            intArrayOf(0, 0, 8,  9, 0, 0,  5, 0, 3),

            // bottom
            intArrayOf(0, 7, 1,  0, 0, 8,  3, 5), // missing
            intArrayOf(0, 8, 0,  0, 3, 0,  0, 0, 7),
            intArrayOf(0, 0, 3,  0, 0, 7,  1, 0, 0),
        )

        assertFailure {
            Grid.createAndLoadGrid(invalidGrid)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Input array rows must have 9 columns, check out row 1")

    }

    @Test
    fun `loadGrid must throw an exception if any of the cell values are not between 0 and 9`() {
        val invalidGrid = arrayOf(
            // top
            intArrayOf(0, 0, 10,  0, 9, 0,  0, 0, 0), // 10 is out of range, at (0,2)
            intArrayOf(0, 2, 0,  8, 4, 0,  9, 7, 0),
            intArrayOf(0, 0, 9,  0, 6, 0,  8, 1, 4),

            // middle
            intArrayOf(0, 0, 0,  2, 0, 4,  0, 0, 0),
            intArrayOf(6, 0, 0,  3, 1, 0,  -1, 2, 8), // -1 is out of range, at (4,6)
            intArrayOf(0, 0, 8,  9, 0, 0,  5, 0, 3),

            // bottom
            intArrayOf(0, 7, 1,  0, 0, 8,  3, 5, 0),
            intArrayOf(0, 8, 0,  0, 3, 0,  0, 0, 7),
            intArrayOf(0, 0, 3,  0, 0, 7,  1, 0, 0),
        )

        assertFailure {
            Grid.createAndLoadGrid(invalidGrid)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Input value at (0,2) is out of range, should be between 0 and 9")

        assertFailure {
            invalidGrid[0][2] = 0
            Grid.createAndLoadGrid(invalidGrid)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Input value at (4,6) is out of range, should be between 0 and 9")

    }

    @Test
    fun `should generate 'simple' output for a grid`() {
        val unsolved = """
                . . 6 . 9 . . . .
                . 2 . 8 4 . 9 7 .
                . . 9 . 6 . 8 1 4
                . . . 2 . 4 . . .
                6 . . 3 1 . . 2 8
                . . 8 9 . . 5 . 3
                . 7 1 . . 8 3 5 .
                . 8 . . 3 . . . 7
                . . 3 . . 7 1 . .
               """.trimIndent()

        val grid = Grid.createAndLoadGrid(unsolvedGrid)
        val output = grid.toString()

        assertThat(output).isEqualTo(unsolved)
    }

    @Test
    fun `should generate 'full grid' output for a grid`() {
        val unsolved = """
                +-------+-------+-------+
                | . . 6 | . 9 . | . . . |
                | . 2 . | 8 4 . | 9 7 . |
                | . . 9 | . 6 . | 8 1 4 |
                +-------+-------+-------+
                | . . . | 2 . 4 | . . . |
                | 6 . . | 3 1 . | . 2 8 |
                | . . 8 | 9 . . | 5 . 3 |
                +-------+-------+-------+
                | . 7 1 | . . 8 | 3 5 . |
                | . 8 . | . 3 . | . . 7 |
                | . . 3 | . . 7 | 1 . . |
                +-------+-------+-------+
               """.trimIndent()

        val grid = Grid.createAndLoadGrid(unsolvedGrid)
        val output = grid.generateFullGridOutput()
        assertThat(output).isEqualTo(unsolved)

    }


    private fun buildGrid(): Grid = createGrid()

}