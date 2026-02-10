package com.seachange.digressions


class Sheep {}

class Flock(
    private val flockOfSheep: MutableList<Sheep> = mutableListOf(),
) {
    operator fun plusAssign(sheep: Sheep) {
        flockOfSheep += sheep
    }
    operator fun plusAssign(sheep: List<Sheep>) {
        flockOfSheep += sheep
    }
    operator fun compareTo(other: Flock): Int = flockOfSheep.size.compareTo(other.flockOfSheep.size)
}

fun main() {
    val john = Sheep()
    val paul = Sheep()
    val george = Sheep()

    val smallFlock = Flock()
    smallFlock += john
    val largeFlock = Flock()
    largeFlock += listOf(paul, george)

    if( largeFlock > smallFlock ) {
        println("Large Flock is largest flock")
    }
}