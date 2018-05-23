package com.example.ruben.privaliachallenge.entity

import java.io.Serializable

class MovieEntity (
        val voteCount: Int,
        val id: Int,
        val video: Boolean,
        val voteAverage: Int,
        val title: String,
        val popularity: Float,
        val posterPath: String,
        val originalLang: String,
        val originalTitle: String,
        val genreIds: MutableList<Int>,
        val backdropPath: String,
        val adult: Boolean,
        val overview: String,
        val releaseDate: String
) : Serializable