// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json   = Json(JsonConfiguration.Stable)
// val images = json.parse(Images.serializer(), jsonString)

package com.github.azdrachak.aafundamentals.data.tmdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("file_path")
    val filePath: String,

    val height: Int,

    val width: Int
)
