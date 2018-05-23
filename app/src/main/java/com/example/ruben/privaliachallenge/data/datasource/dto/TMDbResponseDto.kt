package com.example.ruben.privaliachallenge.data.datasource.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TMDbResponseDto(
        val page: Int,
        @SerializedName("total_results")
        val totalResults: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
        val results: MutableList<TMDbMovieDto>
) : Serializable