package ru.smak.mvvm_data_transfer

import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.smak.mvvm_data_transfer.ui.theme.MVVM_Data_TransferTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_Data_TransferTheme {
                // A surface container using the 'background' color from the theme
                val mvm = viewModel(MainViewModel::class.java)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(Modifier.fillMaxWidth()){
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.3f)
                                    .weight(3f),
                                verticalArrangement = Arrangement.SpaceAround,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                OutlinedTextField(value = mvm.arg1, onValueChange = {
                                    if (it.toIntOrNull() != null) mvm.arg1 = it
                                })
                                OutlinedTextField(value = mvm.arg2, onValueChange = {
                                    if (it.toIntOrNull() != null) mvm.arg2 = it
                                })
                            }
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.3f)
                                    .weight(1f),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                FilledIconToggleButton(checked = mvm.operation.value == Operation.PLUS, onCheckedChange = {
                                    mvm.operation.value = Operation.PLUS
                                }) {
                                    Text("+")
                                }
                                FilledIconToggleButton(checked = mvm.operation.value == Operation.MINUS, onCheckedChange = {
                                    mvm.operation.value = Operation.MINUS
                                }) {
                                    Text("-")
                                }
                                FilledIconToggleButton(checked = mvm.operation.value == Operation.TIMES, onCheckedChange = {
                                    mvm.operation.value = Operation.TIMES
                                }) {
                                    Text("*")
                                }
                                FilledIconToggleButton(checked = mvm.operation.value == Operation.DIV, onCheckedChange = {
                                    mvm.operation.value = Operation.DIV
                                }) {
                                    Text("/")
                                }
                            }
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            enabled = false,
                        ) {
                            Text("=")
                        }
                        Text(mvm.result.value)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVM_Data_TransferTheme {

    }
}