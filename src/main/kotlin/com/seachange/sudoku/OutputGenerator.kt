package com.seachange.com.seachange.sudoku

import com.seachange.sudoku.GRID_SIZE
import com.seachange.sudoku.Grid
import com.seachange.sudoku.INNER_GRID_SIZE
import com.seachange.sudoku.cellValueToString

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

fun Int.isFullGridSeparator() = this % INNER_GRID_SIZE == 2 && this < GRID_SIZE - 1