package net.purpler.demo.reactive.coffeeservice.service

import net.purpler.demo.reactive.coffeeservice.model.Coffee
import net.purpler.demo.reactive.coffeeservice.model.CoffeeOrder
import net.purpler.demo.reactive.coffeeservice.repository.CoffeeRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant

@Service
class CoffeeService(private val repo: CoffeeRepository) {

	fun getAllCoffees(): Flux<Coffee> {
		return repo.findAll()
	}

	fun getCoffeeById(id: String): Mono<Coffee> {
		return repo.findById(id)
	}

	fun getCoffeeByName(name: String): Mono<Coffee> {
		return repo.findCoffeeByName(name) // 이름을 검색한다.
			.defaultIfEmpty(Coffee("12345", "My favorite coffee")) // 일치하는 정보가 없으면 기본값을 넘겨준다.
	}

	fun getOrdersForCoffee(coffeeId: String): Flux<CoffeeOrder> {
		return Flux.interval(Duration.ofSeconds(1)) // 1초 간격으로 숫자를 발행한다.
			.onBackpressureDrop() // 처리량이 많아서 처리를 못하는 요소는 버린다.
			.map { CoffeeOrder(coffeeId, Instant.now()) } // 주문정보를 생성한다.
	}
}