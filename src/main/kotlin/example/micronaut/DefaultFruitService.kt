package example.micronaut

import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
class DefaultFruitService(private val repository: FruitRepository) : FruitService {

    override fun list(): Publisher<Fruit> {
        return repository.findAll()
    }

    override fun save(fruit: Fruit): Mono<Boolean> {
        return Mono.from(repository.save(fruit))
            .map { true }
            .onErrorMap { e -> println(e); e }
    }

    override fun find(id: String): Publisher<Fruit> {
        return repository.findById(id)
    }

    override fun findByNameInList(name: List<String>): Publisher<Fruit> {
        return repository.findByNameInList(name)
    }
}