package net.purpler.demo.reactive.coffeeservice.component

import net.purpler.demo.reactive.coffeeservice.model.Coffee
import net.purpler.demo.reactive.coffeeservice.repository.CoffeeRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import javax.annotation.PostConstruct

@Component
class DataLoader(private val repo: CoffeeRepository) {

	@PostConstruct
	fun loadData() {
		println("### load Datas......")
		repo.deleteAll() // 기존 데이터는 일단 지우고..
			.thenMany(Flux.just("Cafe Cereza", "Don Pablo", "Sumatra", "Kaldi", "Kona") // 이 이름들로 커피 정보를 생성 할 것이다.
				.map { Coffee(null, it) } // Flux를 통해 들어오는 커피이름으로 Coffee 정보를 생성한다.
				.flatMap { repo.save(it) }) // 그리고 MongoDB에 저장
			.thenMany(repo.findAll()) // 저장이 잘 됐는지 전체 데이터를 조회해서
			.subscribe { println("### $it") } // 출력한다.
	}
}