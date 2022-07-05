package example.micronaut

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import javax.validation.Valid

@Controller("/fruits")
open class FruitController(private val service: FruitService) {

    @Get
    fun searchAll(): Publisher<Fruit> = service.list()

    @Post
    open fun save(fruit: Fruit): Mono<HttpStatus> {
        return service.save(fruit)
            .map { added: Boolean -> if (added) HttpStatus.CREATED else HttpStatus.BAD_REQUEST }
    }
}