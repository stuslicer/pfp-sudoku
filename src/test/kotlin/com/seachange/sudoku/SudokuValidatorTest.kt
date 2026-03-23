package com.seachange.sudoku

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.seachange.sudoku.testsupport.solvedGrid
import com.seachange.sudoku.testsupport.unsolvedGrid
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class SudokuValidatorTest {

    private class CellValidatorArguments(): ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(solvedGrid(), 4, true, "solved grid, cell should be valid"),
                Arguments.of(solvedGrid(), 6, false, "solved grid, cell should be invalid"),
                Arguments.of(unsolvedGrid(), 4, true, "unsolved grid, cell should be valid"),
                Arguments.of(unsolvedGrid(), 6, false, "unsolved grid, cell should be invalid"),
            )
        }
    }

    @ParameterizedTest(name = "{3}")
    @ArgumentsSource(CellValidatorArguments::class)
    fun `should be able to detect if a cell value is valid for its row`(gridToLoad: GridArray, cellValue: Int, expectedResult: Boolean, description: String) {
        val grid = Grid.createAndLoadGrid(gridToLoad)

        val validator = SudokuValidator(grid)

        grid[0, 1] = cellValue
        assertThat(validator.isRowValid(0)).isEqualTo(expectedResult)
    }

    @ParameterizedTest(name = "{3}")
    @ArgumentsSource(CellValidatorArguments::class)
    fun `should be able to detect if a cell value is valid for its column`(gridToLoad: GridArray, cellValue: Int, expectedResult: Boolean, description: String) {
        val grid = Grid.createAndLoadGrid(gridToLoad)

        val validator = SudokuValidator(grid)

        grid[0, 1] = cellValue
        assertThat(validator.isColumnValid(1)).isEqualTo(expectedResult)
    }

    @ParameterizedTest(name = "{3}")
    @ArgumentsSource(CellValidatorArguments::class)
    fun `should be able to detect if a cell value is valid for its inner grid`(gridToLoad: GridArray, cellValue: Int, expectedResult: Boolean, description: String) {
        val grid = Grid.createAndLoadGrid(gridToLoad)

        val validator = SudokuValidator(grid)

        grid[0, 1] = cellValue
        assertThat(validator.isInnerGridValid(0, 1)).isEqualTo(expectedResult)
    }

    @ParameterizedTest(name = "{3}")
    @ArgumentsSource(CellValidatorArguments::class)
    fun `should be able to detect if a cell value is valid`(gridToLoad: GridArray, cellValue: Int, expectedResult: Boolean, description: String) {
        val grid = Grid.createAndLoadGrid(gridToLoad)

        val validator = SudokuValidator(grid)

        grid[0, 1] = cellValue
        assertThat(validator.isCellValid(0, 1)).isEqualTo(expectedResult)
    }

    @Test
    fun `should be able to detect if a solved grid is complete`() {
        val validator = SudokuValidator(Grid.createAndLoadGrid(solvedGrid()))

        assertThat(validator.isComplete()).isTrue()
    }

    @Test
    fun `should be able to detect if a unsolved grid is incomplete`() {
        val validator = SudokuValidator(Grid.createAndLoadGrid(unsolvedGrid()))

        assertThat(validator.isComplete()).isFalse()
    }

    @Test
    fun `should be able to detect if a solved grid is valid`() {
        val validator = SudokuValidator(Grid.createAndLoadGrid(solvedGrid()))

        assertThat(validator.isGridValid()).isTrue()
    }

}