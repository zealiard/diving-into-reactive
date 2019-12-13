package net.purpler.demo.reactive.coffeeservice.model

import java.time.Instant

data class CoffeeOrder(
	val coffeeId: String,
	val whenOrdered: Instant
)