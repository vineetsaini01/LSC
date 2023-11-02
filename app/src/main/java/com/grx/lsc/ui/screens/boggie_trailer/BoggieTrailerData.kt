package com.grx.lsc.ui.screens.boggie_trailer

sealed class BoggieTrailerEvent {
    object OnPressedYesConfirmBtn : BoggieTrailerEvent()

    object OnPressedChange : BoggieTrailerEvent()
}