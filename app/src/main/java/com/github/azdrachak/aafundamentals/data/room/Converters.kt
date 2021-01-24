package com.github.azdrachak.aafundamentals.data.room

import androidx.room.TypeConverter
import com.github.azdrachak.aafundamentals.data.Actor
import com.github.azdrachak.aafundamentals.data.tmdb.Genre

class Converters {

    @TypeConverter
    fun fromGenres(genres: List<Genre>): String =
        genres.joinToString("|") { "${it.id}:${it.name}" }

    @TypeConverter
    fun toGenres(genres: String): List<Genre> {
        return genres.split("|").map {
            val rawGenre: List<String> = it.split(":")
            val id = rawGenre[0].toInt()
            val name = rawGenre[1]
            Genre(id = id, name = name)
        }
    }

    @TypeConverter
    fun fromActors(actors: List<Actor>): String {
        return actors.joinToString("|") {
            "${it.id};${it.name};${it.picture}"
        }
    }

    @TypeConverter
    fun toActors(actors: String): List<Actor> {
        return actors.split("|").map {
            val rawActor = it.split(";")
            val id = rawActor[0].toInt()
            val name = rawActor[1]
            val picture = rawActor[2]
            Actor(id = id, picture = picture, name = name)
        }
    }
}
