package net.purpler.demo.reactive.coffeeclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoffeeClientApplication

fun main(args: Array<String>) {
	runApplication<CoffeeClientApplication>(*args)
}
