package com.grx.lsc.core.base_view_model

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    abstract fun onEvent(event: T)
}
