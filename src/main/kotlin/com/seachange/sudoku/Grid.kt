package com.seachange.sudoku

const val GRID_SIZE = 9
const val INNER_GRID_SIZE = 3
val CELL_RANGE = 0..9

typealias Grid = Array<IntArray>

fun createGrid(): Grid = Array(GRID_SIZE) { IntArray(GRID_SIZE) { 0 } }

fun Grid.set(row: Int, column: Int, value: Int) { this[row][column] = value }
fun Grid.setAndGet(row: Int, column: Int, value: Int): Int {
    this[row][column] = value
    return value
}
fun Grid.get(row: Int, column: Int) = this[row][column]

fun Grid.isSpace(row: Int, column: Int) = this[row][column] == 0

fun Int.isInRange() = this in CELL_RANGE

fun loadGrid(values: Grid): Grid {
    check(values.size == GRID_SIZE) { "Input array must have $GRID_SIZE rows" }
    values.forEachIndexed { row, rowArray ->
        check(rowArray.size == GRID_SIZE) { "Input array rows must have $GRID_SIZE columns, check out row $row" }
    }
    for( row in values.indices ) {
        for( column in values[row].indices ) {
            check(values[row][column].isInRange()) { "Input value at ($row,$column) is out of range, should be between 0 and $GRID_SIZE" }
        }
    }

    val grid = values.copyOf()
    values.forEachIndexed { index, ints ->
        grid[index] = ints.copyOf()
    }

    return grid
}

fun Int.cellValueToString() = if( this == 0 ) "." else "$this"

fun Grid.generateSimpleGridOutput(): String {
    return this.joinToString(separator = "\n") {
        it.joinToString(separator = " ") { it.cellValueToString() }
    }
}

fun Grid.generateFullGridOutput(): String {
    val grid = this
    return buildString {
        generateFullHorizontalSeparator()
        for (rowIndex in grid.indices) {
            generateFullGridRow(grid[rowIndex])
            if (rowIndex.isFullGridSeparator()) {
                generateFullHorizontalSeparator()
            }
        }
        generateFullHorizontalSeparator()
    }.trim()
}

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
