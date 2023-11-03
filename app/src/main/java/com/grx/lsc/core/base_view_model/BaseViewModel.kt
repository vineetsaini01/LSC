package com.grx.lsc.core.base_view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel



abstract class BaseViewModel<E, S>(initialState: S) : ViewModel() {

    abstract fun event(event: E)

    private val _state: MutableState<S> = mutableStateOf(initialState)
    val state: State<S> = _state

    protected fun setState(reducer: S.() -> S) {
        val newState = state.value.reducer()
        _state.value = newState
    }

}