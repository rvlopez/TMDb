package com.example.ruben.privaliachallenge.entity

import java.io.Serializable

class ResponseEntity (
        val page: Int,
        val totalResults: Int,
        val totalPages: Int,
        val results: MutableList<MovieEntity>
) : Serializable