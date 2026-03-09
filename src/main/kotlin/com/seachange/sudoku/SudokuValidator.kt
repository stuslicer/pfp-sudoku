package com.seachange.sudoku

class SudokuValidator(
    private val grid: Grid
) {

    fun isCellValid(row: Int, column: Int): Boolean = when {
        ! isRowValid(row) -> false
        ! isColumnValid(column) -> false
        ! isInnerGridValid(row, column) -> false
        else -> true
    }

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

}