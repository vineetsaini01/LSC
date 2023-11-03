package com.grx.lsc.core.base_view_model

import androidx.lifecycle.ViewModel

abstract class BaseViewModelOld<E> : ViewModel() {

    abstract fun onEvent(event: E)

}
