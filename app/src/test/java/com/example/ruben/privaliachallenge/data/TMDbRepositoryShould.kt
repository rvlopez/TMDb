package com.example.ruben.privaliachallenge.data

import com.example.ruben.privaliachallenge.data.datasource.TMDbDataSource
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository
import com.example.ruben.privaliachallenge.data.repository.TMDbRepositoryImpl
import com.example.ruben.privaliachallenge.entity.ResponseEntity
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import rx.observers.TestSubscriber

class TMDbRepositoryShould {

    private var tmdbDataSource: TMDbDataSource? = null
    private var tmdbRepository: TMDbRepository? = null

    private var testSubscriber: TestSubscriber<ResponseEntity>? = null

    @Before
    fun setUp() {
        tmdbDataSource = mock(TMDbDataSource::class.java)
        tmdbRepository = TMDbRepositoryImpl(tmdbDataSource)
        testSubscriber = TestSubscriber.create<ResponseEntity>()
    }

    @Test(expected = IllegalArgumentException::class)
    fun throw_exception_with_invalid_arguments() {
        TMDbRepositoryImpl(null)
    }
}