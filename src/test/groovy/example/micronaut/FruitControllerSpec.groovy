package example.micronaut

import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import jakarta.inject.Inject
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import spock.lang.Specification

@MicronautTest
class FruitControllerSpec extends Specification implements TestPropertyProvider {

    static MongoDBContainer mongo = new MongoDBContainer(DockerImageName.parse("mongo:5.0.12"))
            .withExposedPorts(27017)

    @Inject
    FruitClient client

    def "interact with fruits endpoint"() {
        when:
        var fruits = client.searchAll()

        then:
        fruits.empty

        when:
        var status = client.save(new Fruit("banana"))

        then:
        status == HttpStatus.CREATED

        when:
        fruits = client.searchAll()

        then:
        !fruits.empty

        fruits.get(0).name == "banana"
    }

    @Override
    Map<String, String> getProperties() {
        mongo.start()

        return ["mongodb.uri": mongo.replicaSetUrl]
    }
}
