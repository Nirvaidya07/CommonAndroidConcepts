package com.nirali.sample.recipe.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val dogId: Int = 0,
    val attributes: Attributes,
    val id: String,
    val relationships: Relationships,
    val type: String
)

data class Relationships(
    val group: Group
)

data class MaleWeight(
    val max: Int,
    val min: Int
)

data class Life(
    val max: Int,
    val min: Int
)

data class Group(
    val `data`: DataX
)

data class DataX(
    val id: String,
    val type: String
)
data class DocModel(
    val `data`: List<Data>,
    val links: Links
)

data class FemaleWeight(
    val max: Int,
    val min: Int
)


data class Attributes(
    val description: String,
    val female_weight: FemaleWeight,
    val hypoallergenic: Boolean,
    val life: Life,
    val male_weight: MaleWeight,
    val name: String
)
data class Links(
    val current: String,
    val last: String,
    val next: String,
    val self: String
)