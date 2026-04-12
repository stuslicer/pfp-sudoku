package com.seachange.sudoku

import com.seachange.com.seachange.sudoku.FullGridOutputGenerator
import com.seachange.com.seachange.sudoku.OutputGenerator

const val GRID_SIZE = 9
const val INNER_GRID_SIZE = 3

val GRID_SIZE_RANGE = 0..<GRID_SIZE
val CELL_RANGE = 0..9


typealias GridArray = Array<IntArray>

fun GridArray.toDebugString(): String {
    return this.joinToString(separator = "\n") {
        it.joinToString(separator = " ") { it.toString() }
    }
}

class Grid(
    private val outputGenerator: OutputGenerator = FullGridOutputGenerator()
) {

    private val grid = Array(GRID_SIZE) { IntArray(GRID_SIZE) { 0 } }

    operator fun set(row: Int, column: Int, value: Int) { grid[row][column] = value }
    operator fun get(row: Int, column: Int) = grid[row][column]

    fun isSpace(row: Int, column: Int) = grid[row][column] == 0

    override fun equals(other: Any?): Boolean = other is Grid && this.toString() == other.toString()
    override fun hashCode(): Int = toString().hashCode()
    override fun toString(): String  {
        return grid.joinToString(separator = "\n") {
            it.joinToString(separator = " ") { it.cellValueToString() }
        }
    }

    private fun Int.cellValueToString() = if( this == 0 ) "." else "$this"

    fun generateOutput() = outputGenerator.generateOutput(this)

    companion object {

        fun createAndLoadGrid(
            values: GridArray,
            outputGenerator: OutputGenerator = FullGridOutputGenerator(),
            ): Grid {
            check(values.size == GRID_SIZE) { "Input array must have $GRID_SIZE rows" }
            values.forEachIndexed { row, rowArray ->
                check(rowArray.size == GRID_SIZE) { "Input array rows must have $GRID_SIZE columns, check out row $row" }
            }
            for( row in values.indices ) {
                for( column in values[row].indices ) {
                    check(values[row][column].isInRange()) { "Input value at ($row,$column) is out of range, should be between 0 and $GRID_SIZE" }
                }
            }

            val newGrid = Grid(outputGenerator)
            values.forEachIndexed { row, rowArray ->
                rowArray.forEachIndexed { column, value ->
                    newGrid.set(row, column, value)
                }
            }

            return newGrid
        }
    }

}

fun createGrid(outputGenerator: OutputGenerator = FullGridOutputGenerator()): Grid = Grid(outputGenerator)

fun Int.isInRange() = this in CELL_RANGE

fun Int.cellValueToString() = if( this == 0 ) "." else "$this"

