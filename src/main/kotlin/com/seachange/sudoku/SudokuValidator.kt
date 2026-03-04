package com.seachange.sudoku

class SudokuValidator(
    private val grid: Grid
) {

    internal fun isRowValid(row: Int): Boolean {
        val rowValues = (0..<GRID_SIZE).map { columnIndex ->
            grid[row, columnIndex]
        }.filter { it > 0 }

        return rowValues.haveNoDuplicates()
    }

    internal fun isColumnValid(column: Int): Boolean {
        val columnValues = (0..<GRID_SIZE).map { rowIndex ->
            grid[rowIndex, column]
        }.filter { it > 0 }

        return columnValues.haveNoDuplicates()
    }

    private fun List<Int>.haveNoDuplicates() = this.size == this.distinct().size

}