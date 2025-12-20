package com.seachange.sudoku

const val GRID_SIZE = 9

typealias Grid = Array<IntArray>

fun createGrid(): Grid = Array(GRID_SIZE) { IntArray(GRID_SIZE) { 0 } }

fun Grid.set(row: Int, column: Int, value: Int) { this[row][column] = value }
fun Grid.get(row: Int, column: Int) = this[row][column]

