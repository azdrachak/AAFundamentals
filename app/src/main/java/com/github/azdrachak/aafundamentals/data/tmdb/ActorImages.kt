package com.github.azdrachak.aafundamentals.data.tmdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("file_path")
    val filePath: String,

    @SerialName("height")
    val height: Int,

    @SerialName("width")
    val width: Int
)
