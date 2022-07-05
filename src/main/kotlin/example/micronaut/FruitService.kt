package example.micronaut

import io.micronaut.core.annotation.NonNull
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

interface FruitService {
    fun list(): Publisher<Fruit>

    fun save(fruit: Fruit): Mono<Boolean>

    fun find(@NonNull id: String): Publisher<Fruit>

    fun findByNameInList(name: List<String>): Publisher<Fruit>
}