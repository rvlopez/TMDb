package com.example.ruben.privaliachallenge.data.datasource.mapper

import com.example.ruben.privaliachallenge.data.datasource.dto.TMDbMovieDto
import com.example.ruben.privaliachallenge.data.datasource.dto.TMDbResponseDto
import com.example.ruben.privaliachallenge.entity.MovieEntity
import com.example.ruben.privaliachallenge.entity.ResponseEntity
import javax.inject.Inject

class TMDbDtoMapper @Inject constructor(){

    fun toEntity(tmDbResponseDto: TMDbResponseDto) : ResponseEntity {
        return ResponseEntity(
                tmDbResponseDto.page,
                tmDbResponseDto.totalResults,
                tmDbResponseDto.totalPages,
                toMovieEntity(tmDbResponseDto.results)
        )
    }

    private fun toMovieEntity(tmDbMovieDtoList: MutableList<TMDbMovieDto>) : MutableList<MovieEntity> {
        val movieEntityList: MutableList<MovieEntity> = mutableListOf()

        for (tmDbMovieDto in tmDbMovieDtoList) {

            val posterPath: String = if (tmDbMovieDto.posterPath != null) tmDbMovieDto.posterPath else ""
            val backdropPath: String = if (tmDbMovieDto.backdropPath != null) tmDbMovieDto.backdropPath else ""

            movieEntityList.add(MovieEntity(
                    tmDbMovieDto.voteCount,
                    tmDbMovieDto.id,
                    tmDbMovieDto.video,
                    tmDbMovieDto.voteAverage,
                    tmDbMovieDto.title,
                    tmDbMovieDto.popularity,
                    posterPath,
                    tmDbMovieDto.originalLang,
                    tmDbMovieDto.originalTitle,
                    tmDbMovieDto.genreIds,
                    backdropPath,
                    tmDbMovieDto.adult,
                    tmDbMovieDto.overview,
                    tmDbMovieDto.releaseDate
            ))
        }

        return movieEntityList
    }
}