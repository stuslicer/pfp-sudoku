package com.seachange.sudoku

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

val allCellLocations = Iterable {
    iterator {
        GRID_SIZE_RANGE.forEach { rowIndex ->
            GRID_SIZE_RANGE.forEach { columnIndex ->
                yield(Pair(rowIndex, columnIndex) )
            }
        }
    }
}

class SudokuValidator(
    private val grid: Grid
) {

    val allCellValues = Iterable {
        iterator {
            GRID_SIZE_RANGE.forEach { rowIndex ->
                GRID_SIZE_RANGE.forEach { columnIndex ->
                    yield(grid[rowIndex,columnIndex] )
                }
            }
        }
    }

    val cellsForValidGrid = Iterable {
        iterator {
            GRID_SIZE_RANGE.forEach { index ->
                yield(index to index )
            }
            // 2, 3
            yield(1 to 4)
            yield(1 to 7)

            // 4, 6
            yield(4 to 1)
            yield(4 to 7)

            // 7, 8
            yield(7 to 1)
            yield(7 to 4)
        }
    }

    fun isCellValid(row: Int, column: Int): Boolean = when {
        ! isRowValid(row) -> false
        ! isColumnValid(column) -> false
        ! isInnerGridValid(row, column) -> false
        else -> true
    }.also {
        logger.debug { "isCellValid($row, $column) = $it" }
    }

    fun isGridValid(): Boolean = cellsForValidGrid.all { (row,column) ->
        isCellValid(row, column)
    }

    fun isSolved(): Boolean = isGridValid() && isComplete()

    internal fun isRowValid(row: Int): Boolean =
        row.nonZeroValuesForRow()
            .haveNoDuplicates()

    private fun Int.nonZeroValuesForRow() = (0..<GRID_SIZE).map { columnIndex ->
        grid[this, columnIndex]
    }.filter { it > 0 }

    internal fun isColumnValid(column: Int): Boolean =
        column.nonZeroValuesForColumn()
            .haveNoDuplicates()

    private fun Int.nonZeroValuesForColumn() = (0..<GRID_SIZE).map { rowIndex ->
        grid[rowIndex, this]
    }.filter { it > 0 }

    internal fun isInnerGridValid(row: Int, column: Int) = nonZeroValuesForInnerGrid(row, column).haveNoDuplicates()

    private fun nonZeroValuesForInnerGrid(row: Int, column: Int) =
        (row.generateInnerGridRange()).flatMap { rowIndex ->
            (column.generateInnerGridRange()).map { columnIndex ->
                grid[rowIndex, columnIndex]
            }
        }.filter { it > 0 }

    private fun Int.generateInnerGridRange() = ((this/INNER_GRID_SIZE) * INNER_GRID_SIZE).let {
        it ..< (it + INNER_GRID_SIZE)
    }

    private fun List<Int>.haveNoDuplicates() = this.size == this.distinct().size

    fun isComplete(): Boolean = allCellValues.none { it == 0 }

}
