package com.seachange.digressions

private class Grid()

private class SudokuSolver(
    private val grid: Grid) {

    fun solve(): Boolean {
        return true
    }

    fun solution(): Grid {
        return grid
    }

    fun difficulty(): Int {
        return 0
    }
}

private class SudokuSolverResult(
    val solved: Boolean,
    val solution: Grid,
    val difficulty: Int
)

private class SudokuSolverService {

    fun solve(grid: Grid): SudokuSolverResult {
        return SudokuSolverResult(true, grid, 0)
    }

}

fun main() {
    val grid = Grid()
    val solver = SudokuSolver(grid)
    solver.solve()
    val solution = solver.solution()
    val difficulty = solver.difficulty()

    val service = SudokuSolverService()
    val result = service.solve(grid)
    val solved = result.solved
    val solution2 = result.solution
    val difficulty2 = result.difficulty

}