package com.example.ruben.privaliachallenge.app.usecase

import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCaseImpl
import com.example.ruben.privaliachallenge.core.executor.MainThread
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository
import com.example.ruben.privaliachallenge.entity.ResponseEntity
import com.example.ruben.privaliachallenge.rule.ImmediateSchedulersTestRule
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.junit.Assert.assertTrue
import org.mockito.Mock
import org.mockito.Mockito.*
import rx.Observable
import rx.observers.TestSubscriber

class PopularMoviesUseCaseShould {

    @Rule var testRule = ImmediateSchedulersTestRule()

    private var useCase: PopularMoviesUseCaseImpl? = null

    @Mock
    private val mainThread: MainThread? = null

    @Test(expected = IllegalArgumentException::class)
    fun throw_exception_with_invalid_parameters() {
        PopularMoviesUseCaseImpl(null, null)
    }

    @Test
    fun call_repository_to_get_popular_movies() {
        val mockRepository = mock(TMDbRepository::class.java)
        val mainThread = MainThread()

        `when`(mockRepository.getPopularMovies(ArgumentMatchers.anyInt())).thenReturn(Observable.empty())

        val subscriber = TestSubscriber.create<ResponseEntity>()

        useCase = PopularMoviesUseCaseImpl(mainThread, mockRepository)
        useCase!!.execute(1, subscriber)
        subscriber.awaitTerminalEvent()

        verify(mockRepository).getPopularMovies(1)
    }

    @Test
    fun create_subscription_correctly() {
        val testSubscriber = TestSubscriber.create<ResponseEntity>()
        useCase!!.execute(1, testSubscriber)

        verify(mainThread)!!.getScheduler()
    }

    @Test
    fun testInteractorShouldUnsubscribeCorrectly() {
        val testSubscriber = TestSubscriber.create<ResponseEntity>()
        useCase!!.execute(1, testSubscriber)
        useCase!!.unsubscribe()

        assertTrue(testSubscriber.isUnsubscribed)
    }

    @Test
    fun build_interactor_observable_on_execution() {
        val spyUseCase = spy<PopularMoviesUseCaseImpl>(useCase)

        `when`(spyUseCase.buildObservable(1)).thenReturn(Observable.empty())
        val mockSubscriber = TestSubscriber.create<ResponseEntity>()

        spyUseCase.execute(1, mockSubscriber)

        verify(spyUseCase).buildObservable(1)
    }
}