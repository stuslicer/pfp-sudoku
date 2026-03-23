package com.seachange.digressions

import io.github.oshai.kotlinlogging.KotlinLogging

val logger = KotlinLogging.logger {}

fun main() {
    logger.info( "Hello, world!")
    logger.info { "Hello, world!" }
}