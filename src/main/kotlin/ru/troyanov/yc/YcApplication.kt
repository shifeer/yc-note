package ru.troyanov.yc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YcApplication

fun main(args: Array<String>) {
	runApplication<YcApplication>(*args)
}
