package example.micronaut

import io.micronaut.core.annotation.Creator
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable

@MappedEntity
@Serdeable
data class Fruit @Creator constructor(
    @field:NonNull @get:NonNull
    @param:NonNull val name: String, @field:Nullable @get:Nullable
    @param:Nullable var description: String?
) {
    constructor(s: String) : this(s, "")

    @Id
    @GeneratedValue
    var id: String? = null
}
