package com.example.ruben.privaliachallenge.core

import com.example.ruben.privaliachallenge.core.presenter.Presenter
import com.example.ruben.privaliachallenge.core.view.IView
import org.junit.Test
import org.mockito.Mockito

class PresenterShould {

    @Test(expected = IllegalArgumentException::class)
    fun throw_exception_on_null_view() {
        val presenter = Mockito.mock(Presenter::class.java, Mockito.CALLS_REAL_METHODS)
        presenter.setView(null)

        presenter.start()
    }
}