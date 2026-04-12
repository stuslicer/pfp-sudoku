package com.seachange.sudoku


data class SolverResult(
    val solved: Boolean,
    val solution: Grid?,
)


class SudokuSolver {

    fun solve(grid: Grid): SolverResult {

        val validator = SudokuValidator(grid)

        if(!validator.isGridValid()) return SolverResult(false, null)

        return SolverResult(true, grid)
    }

}