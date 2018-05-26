package com.example.ruben.privaliachallenge.data.datasource.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TMDbMovieDto(
        @SerializedName("vote_count")
        val voteCount: Int,
        val id: Int,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Float,
        val title: String,
        val popularity: Float,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("original_language")
        val originalLang: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("genre_ids")
        val genreIds: MutableList<Int>,
        val adult: Boolean,
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String
) : Serializable