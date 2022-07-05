package example.micronaut

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

import javax.validation.Valid

@Client("/fruits")
interface FruitClient {

    @Post
    HttpStatus save(Fruit fruit)

    @Get
    Iterable<Fruit> searchAll()
}