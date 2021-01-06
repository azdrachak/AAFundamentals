// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json    = Json(JsonConfiguration.Stable)
// val details = json.parse(Details.serializer(), jsonString)

package com.github.azdrachak.aafundamentals.data.tmdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Details(
    @SerialName("backdrop_path")
    val backdropPath: String,

    val genres: List<Genre>,
    val id: Int,

    val overview: String,

    @SerialName("poster_path")
    val posterPath: String,

    val runtime: Int,

    val title: String,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Int
)
