package net.purpler.demo.reactive.coffeeservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Coffee(
	@Id
	val id: String?,
	val name: String
)