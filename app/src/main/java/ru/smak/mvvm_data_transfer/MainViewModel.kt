package ru.smak.mvvm_data_transfer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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

    val _operation = mutableStateOf(Operation.PLUS)

    var operation: Operation
        get() = _operation.value
        set(value){
            _operation.value = value
            calc.operation = value
        }

    val _operationPermitted = mutableStateOf(false)
    var operationPermitted: Boolean
        get() = _operationPermitted.value
        set(value){
            _operationPermitted.value = value
        }

    init{
        viewModelScope.launch {
            calc.canCalculate.onEach {
                operationPermitted = it
            }.collect()
        }
    }
}