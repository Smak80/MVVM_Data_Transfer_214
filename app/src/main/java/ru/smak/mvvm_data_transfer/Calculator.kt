package ru.smak.mvvm_data_transfer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class Calculator {

    val calcScope = CoroutineScope(Dispatchers.IO + Job())

    var arg1: String = ""
        set(value) {
            field = value
            calcScope.launch { canCalculate.emit(check()) }
        }

    var arg2: String = ""
        set(value) {
            field = value
            calcScope.launch { canCalculate.emit(check()) }
        }

    var operation: Operation = Operation.PLUS
        set(value) {
            field = value
            calcScope.launch { canCalculate.emit(check()) }
        }

    var result: String = ""

    var canCalculate = MutableSharedFlow<Boolean>(replay = 1)

    private fun check(): Boolean = when(operation){
        Operation.PLUS -> {
            val a1 = arg1.toIntOrNull()
            val a2 = arg2.toIntOrNull()
            a1!=null && a2!=null && a1 + a2 in 0..100
        }
        Operation.MINUS -> {
            val a1 = arg1.toIntOrNull()
            val a2 = arg2.toIntOrNull()
            a1!=null && a2!=null && a1 - a2 in 0..100
        }
        Operation.TIMES -> {
            val a1 = arg1.toIntOrNull()
            val a2 = arg2.toIntOrNull()
            a1!=null && a2!=null && a1 * a2 in 0..100
        }
        Operation.DIV -> {
            val a1 = arg1.toIntOrNull()
            val a2 = arg2.toIntOrNull()
            a1!=null && a2!=null && (a1.toFloat() / a2) == (a1 / a2).toFloat() && a1 / a2 in 0..100
        }
    }
}