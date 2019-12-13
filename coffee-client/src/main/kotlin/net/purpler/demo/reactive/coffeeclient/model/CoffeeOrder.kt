package net.purpler.demo.reactive.coffeeclient.model

import java.time.Instant

data class CoffeeOrder(
	val coffeeId: String,
	val whenOrdered: Instant
)