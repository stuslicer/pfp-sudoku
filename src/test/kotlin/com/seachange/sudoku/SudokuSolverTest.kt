package com.seachange.sudoku

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.seachange.sudoku.testsupport.solvedGrid
import org.junit.jupiter.api.Test

class SudokuSolverTest {

    private val solver = SudokuSolver()
    private val expected = Grid.createAndLoadGrid(solvedGrid())

    @Test
    fun `should be able to solve an already solved grid`() {

        val grid = Grid.createAndLoadGrid(solvedGrid())

        val result = solver.solve(grid)

        assertThat( result.solved ).isTrue()
        assertThat( result.solution ).isEqualTo(expected)

    }

    @Test
    fun `solver should return an error if the grid is not valid`() {

        val invalidGrid = Grid().apply {
            for (i in GRID_SIZE_RANGE) {
                for (j in GRID_SIZE_RANGE) {
                    this[i, j] = 1
                }
            }
        }
        val result = solver.solve(invalidGrid)

        assertThat(result.solved).isFalse()
        assertThat(result.solution).isNull()

    }

    @Test
    fun `solver should return an error if the grid is empty`() {

        val emptyGrid = Grid()
        val result = solver.solve(emptyGrid)

        assertThat(result.solved).isFalse()
        assertThat(result.solution).isNull()

    }


}