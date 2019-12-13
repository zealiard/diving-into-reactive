package net.purpler.demo.reactive.coffeeservice.repository

import net.purpler.demo.reactive.coffeeservice.model.Coffee
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface CoffeeRepository: ReactiveCrudRepository<Coffee, String> {
	fun findCoffeeByName(name: String): Mono<Coffee>
}