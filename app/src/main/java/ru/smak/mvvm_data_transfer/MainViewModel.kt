package ru.smak.mvvm_data_transfer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val calc: Calculator = Calculator()

    private val _arg1 = mutableStateOf("")
    private val _arg2 = mutableStateOf("")

    var arg1: String
        get() = _arg1.value
        set(value) {
            _arg1.value = value
            calc.arg1 = value
        }

    var arg2: String
        get() = _arg2.value
        set(value) {
            _arg2.value = value
            calc.arg2 = value
        }

    val result = mutableStateOf("")

    val operation = mutableStateOf(Operation.PLUS)
}