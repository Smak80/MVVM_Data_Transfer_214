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
            result = ""
        }

    var arg2: String
        get() = _arg2.value
        set(value) {
            _arg2.value = value
            calc.arg2 = value
            result = ""
        }

    private val _result = mutableStateOf("")
    var result: String
        get() = _result.value
        private set(value) {_result.value = value}

    private val _operation = mutableStateOf(Operation.PLUS)
    var operation: Operation
        get() = _operation.value
        set(value){
            _operation.value = value
            calc.operation = value
            result = ""
        }

    private val _operationPermitted = mutableStateOf(false)
    var operationPermitted: Boolean
        get() = _operationPermitted.value
        set(value){
            _operationPermitted.value = value
        }

    init{
        /**
         * Асинхронное получение данных из модели через подписку на общий поток.
         * Можно использовать, когда заранее неизвестно, в какой момент в модели
         * появится необходимая для пользователя информация
         */
        viewModelScope.launch {
            calc.canCalculate.onEach {
                operationPermitted = it
            }.collect()
        }
    }

    fun updateResult(){
        /**
         * Синхронное получение данных из модели.
         * Более простой способ. Применяется, когда известно,
         * в какой момент понадобятся данные
         */
        result = calc.result
    }
}