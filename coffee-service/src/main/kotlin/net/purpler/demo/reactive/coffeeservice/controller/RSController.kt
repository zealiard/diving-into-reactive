package net.purpler.demo.reactive.coffeeservice.controller

import net.purpler.demo.reactive.coffeeservice.model.Coffee
import net.purpler.demo.reactive.coffeeservice.model.CoffeeOrder
import net.purpler.demo.reactive.coffeeservice.service.CoffeeService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller
class RSController(private val service: CoffeeService) {

	@MessageMapping("coffees")
	fun supplyCoffees(): Flux<Coffee> {
		return service.getAllCoffees()
	}

	@MessageMapping("orders.{name}")
	fun orders(@DestinationVariable name: String): Flux<CoffeeOrder> {
		return service.getCoffeeByName(name)
			.flatMapMany { coffee -> service.getOrdersForCoffee(coffee.id!!) }
	}
}