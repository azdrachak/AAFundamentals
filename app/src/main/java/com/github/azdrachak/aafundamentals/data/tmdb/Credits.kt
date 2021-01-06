// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json    = Json(JsonConfiguration.Stable)
// val credits = json.parse(Credits.serializer(), jsonString)

package com.github.azdrachak.aafundamentals.data.tmdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    val id: Int,
    val cast: List<Cast>
)

@Serializable
data class Cast(
    val id: Int,

    val name: String,

    @SerialName("profile_path")
    val profilePath: String? = null,

    @SerialName("cast_id")
    val castID: Int? = null
)
