package com.seachange.sudoku

const val GRID_SIZE = 9
const val INNER_GRID_SIZE = 3
val CELL_RANGE = 0..9


typealias GridArray = Array<IntArray>

fun GridArray.toDebugString(): String {
    return this.joinToString(separator = "\n") {
        it.joinToString(separator = " ") { it.toString() }
    }
}

class Grid {

    private val grid = Array(GRID_SIZE) { IntArray(GRID_SIZE) { 0 } }

    fun set(row: Int, column: Int, value: Int) { grid[row][column] = value }
    fun get(row: Int, column: Int) = grid[row][column]

    fun isSpace(row: Int, column: Int) = grid[row][column] == 0

    override fun toString(): String  {
        return grid.joinToString(separator = "\n") {
            it.joinToString(separator = " ") { it.cellValueToString() }
        }
    }

    private fun Int.cellValueToString() = if( this == 0 ) "." else "$this"

    fun generateFullGridOutput() = buildString {
            generateFullHorizontalSeparator()
            for (rowIndex in grid.indices) {
                generateFullGridRow(grid[rowIndex])
                if (rowIndex.isFullGridSeparator()) {
                    generateFullHorizontalSeparator()
                }
            }
            generateFullHorizontalSeparator()
        }.trim()

    private fun StringBuilder.generateFullGridRow(row: IntArray) {
        append("| ")
        for (column in row.indices) {
            append(row[column].cellValueToString() + " ")
            if (column.isFullGridSeparator()) {
                append("| ")
            }
        }
        append("|\n")
    }

    private fun StringBuilder.generateFullHorizontalSeparator() {
        append("+-")
        for(i in 0 until GRID_SIZE) {
            append("--")
            if(i.isFullGridSeparator()) append("+-")
        }
        append("+\n")
    }

    private fun Int.isFullGridSeparator() = this % INNER_GRID_SIZE == 2 && this < GRID_SIZE - 1

    companion object {

        fun createAndLoadGrid(values: GridArray): Grid {
            check(values.size == GRID_SIZE) { "Input array must have $GRID_SIZE rows" }
            values.forEachIndexed { row, rowArray ->
                check(rowArray.size == GRID_SIZE) { "Input array rows must have $GRID_SIZE columns, check out row $row" }
            }
            for( row in values.indices ) {
                for( column in values[row].indices ) {
                    check(values[row][column].isInRange()) { "Input value at ($row,$column) is out of range, should be between 0 and $GRID_SIZE" }
                }
            }

            val newGrid = Grid()
            values.forEachIndexed { row, rowArray ->
                rowArray.forEachIndexed { column, value ->
                    newGrid.set(row, column, value)
                }
            }

            return newGrid
        }
    }

}

fun createGrid(): Grid = Grid()

fun Int.isInRange() = this in CELL_RANGE

