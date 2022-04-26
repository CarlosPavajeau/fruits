package example.micronaut

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import javax.validation.Valid

@Controller("/fruits")
open class FruitController(private val fruitRepository: FruitRepository) {

    @Get
    fun searchAll(): Publisher<Fruit> = fruitRepository.list()

    @Post
    open fun save(@Valid fruit: Fruit): Mono<HttpStatus> {
        return fruitRepository.save(fruit)
            .map { added: Boolean -> if (added) HttpStatus.CREATED else HttpStatus.CONFLICT }
    }
}