package net.purpler.demo.reactive.coffeeclient.component

import net.purpler.demo.reactive.coffeeclient.model.Coffee
import net.purpler.demo.reactive.coffeeclient.model.CoffeeOrder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import javax.annotation.PostConstruct

@Component
class TestClient(private val client: WebClient) {

//	@PostConstruct
	fun letsDoThis() {
		client.get() // get 요청을 보낸다.
			.uri("/coffees") // http://localhost:8080/coffees로 요청을 보낸다.
			.retrieve()
			.bodyToFlux(Coffee::class.java) // response를 이용해 Coffee 정보를 생성한다.
			.filter { coffee -> coffee.name.equals("kona", true) } // Coffee 정보 중에 Kona 커피를 찾는다.
			.flatMap { coffee -> client.get()
				.uri("/coffees/{id}/orders", coffee.id) // http://localhost:8080/coffees/{id}/orders로 요청을 보낸다.
				.retrieve()
				.bodyToFlux(CoffeeOrder::class.java)} // CoffeeOrder 정보를 생성한다.
			.subscribe { println("### $it") } // stream 구독을 시작하고, 전달되는 데이터를 출력한다.
	}
}