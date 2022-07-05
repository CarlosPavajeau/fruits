package example.micronaut

import io.micronaut.core.annotation.NonNull
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository
import org.reactivestreams.Publisher

@MongoRepository
interface FruitRepository : ReactiveStreamsCrudRepository<Fruit, String> {
    @NonNull
    fun findByNameInList(@NonNull names: List<String>): Publisher<Fruit>
}