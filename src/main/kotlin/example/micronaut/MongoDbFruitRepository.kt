package example.micronaut

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.micronaut.core.annotation.NonNull
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import javax.validation.Valid

@Singleton
open class MongoDbFruitRepository(
    private val mongoConf: MongoDbConfiguration,
    private val mongoClient: MongoClient
) : FruitRepository {

    override fun save(@Valid fruit: Fruit): Mono<Boolean> =
        Mono.from(collection.insertOne(fruit))
            .map { true }
            .onErrorReturn(false)

    @NonNull
    override fun list(): Publisher<Fruit> = collection.find()

    private val collection: MongoCollection<Fruit>
        get() = mongoClient.getDatabase(mongoConf.name)
            .getCollection(mongoConf.collection, Fruit::class.java)
}