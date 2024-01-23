package com.example.task_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.task_2.ui.theme.Task_2Theme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AndroidEventScheduler(this)
        var eventItem: EventItem? = null
        setContent {
            Task_2Theme {
                var secondsText by remember {
                    mutableStateOf("")
                }
                var message by remember {
                    mutableStateOf("")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(16.dp),
                        value = secondsText,
                        onValueChange = {secondsText = it},
                        placeholder = {
                            Text(text = "Enter event time in seconds")
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(16.dp),
                        value = message,
                        onValueChange = {message = it},
                        placeholder = {
                            Text(text = "Enter event name")
                        }
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(onClick = {
                            eventItem = EventItem(
                                time = LocalDateTime.now()
                                    .plusSeconds(secondsText.toLong()),
                                message = message
                            )
                            eventItem?.let { scheduler::scedule }
                            secondsText = ""
                            message =""
                        }) {
                            Text(text = "Set")
                        }
                        Button(onClick = {
                            eventItem?.let { scheduler::cancel }
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }

            }
        }
    }
}