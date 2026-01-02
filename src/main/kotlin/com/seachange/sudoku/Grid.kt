package com.seachange.sudoku

const val GRID_SIZE = 9
val CELL_RANGE = 0..9

typealias Grid = Array<IntArray>

fun createGrid(): Grid = Array(GRID_SIZE) { IntArray(GRID_SIZE) { 0 } }

fun Grid.set(row: Int, column: Int, value: Int) { this[row][column] = value }
fun Grid.get(row: Int, column: Int) = this[row][column]

fun Grid.isSpace(row: Int, column: Int) = this[row][column] == 0

fun loadGrid(values: Grid): Grid {
    check(values.size == GRID_SIZE) { "Input array must have $GRID_SIZE rows" }
    values.forEachIndexed { row, rowArray ->
        check(rowArray.size == GRID_SIZE) { "Input array rows must have $GRID_SIZE columns, check out row $row" }
    }
    values.forEachIndexed { row, rowArray ->
        rowArray.forEachIndexed { column, cellValue ->
            check(cellValue >= 0 && cellValue <= GRID_SIZE) { "Input value at ($row,$column) is out of range, should be between 0 and $GRID_SIZE" }
        }
    }

    val grid = values.copyOf()
    values.forEachIndexed { index, ints ->
        grid[index] = ints.copyOf()
    }

    return grid
}
