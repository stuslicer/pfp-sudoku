package com.seachange.sudoku

import com.seachange.com.seachange.digressions.OutputType

const val GRID_SIZE = 9
const val INNER_GRID_SIZE = 3
val CELL_RANGE = 0..9


typealias GridArray = Array<IntArray>

fun GridArray.toDebugString(): String {
    return this.joinToString(separator = "\n") {
        it.joinToString(separator = " ") { it.toString() }
    }
}

class Grid(
    private val outputGenerator: OutputGenerator = FullGridOutputGenerator()) {

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

    fun generateOutput() = outputGenerator.generateOutput(this)

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

interface OutputGenerator {
    fun generateOutput(grid: Grid): String
}

class FullGridOutputGenerator : OutputGenerator {

    override fun generateOutput(grid: Grid) = buildString {
        generateFullHorizontalSeparator()
        for (rowIndex in 0..<GRID_SIZE) {
            generateFullGridRow(grid, rowIndex)
            if (rowIndex.isFullGridSeparator()) {
                generateFullHorizontalSeparator()
            }
        }
        generateFullHorizontalSeparator()
    }.trim()

    private fun StringBuilder.generateFullGridRow(grid: Grid, row: Int) {
        append("| ")
        for (column in 0..<GRID_SIZE) {
            append(grid.get(row,column).cellValueToString() + " ")
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

}

class UnicodeFullGridOutputGenerator: OutputGenerator {

    private val TOP_LEFT_CORNER = "╔"
    private val TOP_RIGHT_CORNER = "╗"
    private val TOP_INTERSECTION = "╦"
    private val BOTTOM_LEFT_CORNER = "╚"
    private val BOTTOM_RIGHT_CORNER = "╝"
    private val BOTTOM_INTERSECTION = "╩"
    private val HORIZONTAL_SEPARATOR = "═"
    private val LEFT_INTERSECTION = "╠"
    private val RIGHT_INTERSECTION = "╣"
    private val INNER_INTERSECTION = "╬"
    private val VERTICAL_SEPARATOR = "║"

    override fun generateOutput(grid: Grid) = buildString {
        generateTopHorizontalSeparator()
        for (rowIndex in 0..<GRID_SIZE) {
            generateFullGridRow(grid, rowIndex)
            if (rowIndex.isFullGridSeparator()) {
                generateInnerHorizontalSeparator()
            }
        }
        generateBottomHorizontalSeparator()
    }.trim()

    private fun StringBuilder.generateFullGridRow(grid: Grid, row: Int) {
        append(VERTICAL_SEPARATOR + " ")
        for (column in 0..<GRID_SIZE) {
            append(grid.get(row,column).cellValueToString() + " ")
            if (column.isFullGridSeparator()) {
                append(VERTICAL_SEPARATOR + " ")
            }
        }
        append(VERTICAL_SEPARATOR + "\n")
    }

    private fun StringBuilder.generateInnerRow(intersection: String) {
        for(i in 0 until GRID_SIZE) {
            append(HORIZONTAL_SEPARATOR + HORIZONTAL_SEPARATOR)
            if(i.isFullGridSeparator()) append(intersection + HORIZONTAL_SEPARATOR)
        }
    }

    private fun StringBuilder.generateTopHorizontalSeparator() {
        append(TOP_LEFT_CORNER + HORIZONTAL_SEPARATOR)
        generateInnerRow(TOP_INTERSECTION)
        append(TOP_RIGHT_CORNER + "\n")
    }

    private fun StringBuilder.generateInnerHorizontalSeparator() {
        append(LEFT_INTERSECTION + HORIZONTAL_SEPARATOR)
        generateInnerRow(INNER_INTERSECTION)
        append(RIGHT_INTERSECTION + "\n")
    }

    private fun StringBuilder.generateBottomHorizontalSeparator() {
        append(BOTTOM_LEFT_CORNER + HORIZONTAL_SEPARATOR)
        generateInnerRow(BOTTOM_INTERSECTION)
        append(BOTTOM_RIGHT_CORNER + "\n")
    }

}

fun Int.cellValueToString() = if( this == 0 ) "." else "$this"

fun Int.isFullGridSeparator() = this % INNER_GRID_SIZE == 2 && this < GRID_SIZE - 1

