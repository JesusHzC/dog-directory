package com.jesushz.dogdirectory.dog.data.mappers

import com.jesushz.dogdirectory.core.database.entity.DogEntity
import com.jesushz.dogdirectory.core.data.models.Dog

fun Dog.toDogEntity(): DogEntity {
    return DogEntity(
        id = id,
        name = name,
        description = description,
        age = age,
        image = image
    )
}

fun DogEntity.toDog(): Dog {
    return Dog(
        id = id,
        name = name,
        description = description,
        age = age,
        image = image
    )
}
