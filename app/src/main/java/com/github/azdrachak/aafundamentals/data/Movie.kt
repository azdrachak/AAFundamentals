package com.github.azdrachak.aafundamentals.data

import androidx.room.*
import com.github.azdrachak.aafundamentals.data.tmdb.Genre

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster")
    val poster: String,

    @ColumnInfo(name = "backdrop")
    val backdrop: String,

    @ColumnInfo(name = "ratings")
    val ratings: Float,

    @ColumnInfo(name = "number_of_ratings")
    val numberOfRatings: Int,

    @ColumnInfo(name = "minimum_age")
    val minimumAge: Int,

    @ColumnInfo(name = "runtime")
    val runtime: Int,

    @ColumnInfo(name = "genres")
    val genres: List<Genre>,

    @ColumnInfo(name = "actors")
    val actors: List<Actor>
)
