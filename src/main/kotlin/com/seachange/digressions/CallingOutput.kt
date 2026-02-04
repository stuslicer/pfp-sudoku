package com.seachange.com.seachange.digressions

import com.seachange.sudoku.Grid
import com.seachange.sudoku.createGrid

enum class OutputType {
    SIMPLE_TEXT,
    ASCII,
    UNICODE,
    THREE_D
}

//fun printoutGrid(grid: Grid, option: OutputType = OutputType.ASCII) {
//    val output = when(option) {
//        OutputType.SIMPLE_TEXT -> grid.toString()
//        OutputType.ASCII -> grid.generateFullGridOutput()
//        OutputType.UNICODE -> grid.generateFullUnicodeGridOutput()
//        OutputType.THREE_D -> grid.generate3DGridOutput()
//    }
//    println(output)
//}

//fun main() {
//    printoutGrid(createGrid())
//}