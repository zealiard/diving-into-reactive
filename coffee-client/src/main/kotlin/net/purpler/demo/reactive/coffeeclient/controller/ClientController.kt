package net.purpler.demo.reactive.coffeeclient.controller

import net.purpler.demo.reactive.coffeeclient.model.Coffee
import net.purpler.demo.reactive.coffeeclient.model.CoffeeOrder
import org.springframework.http.MediaType
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ClientController(private val requester: RSocketRequester) {

	@GetMapping("/coffees")
	fun coffees(): Flux<Coffee> {
		return requester.route("coffees").retrieveFlux(Coffee::class.java)
	}

	@GetMapping("/orders/{name}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
	fun orders(@PathVariable name: String): Flux<CoffeeOrder> {
		return requester.route("orders.$name").retrieveFlux(CoffeeOrder::class.java)
	}
}