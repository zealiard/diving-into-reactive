package net.purpler.demo.reactive.coffeeservice.controller

import net.purpler.demo.reactive.coffeeservice.model.Coffee
import net.purpler.demo.reactive.coffeeservice.model.CoffeeOrder
import net.purpler.demo.reactive.coffeeservice.service.CoffeeService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/coffees")
class CoffeeController(private val service: CoffeeService) {

	@GetMapping
	fun all(): Flux<Coffee> {
		return service.getAllCoffees()
	}

	@GetMapping("/{id}")
	fun byId(@PathVariable id: String): Mono<Coffee> {
		return service.getCoffeeById(id)
	}

	@GetMapping("/{id}/orders", produces = [MediaType.TEXT_EVENT_STREAM_VALUE]) // Stream으로 데이터를 보낸다.
	fun orders(@PathVariable id: String): Flux<CoffeeOrder> {
		return service.getOrdersForCoffee(id)
	}
}