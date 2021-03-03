package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainActivityViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                BestStoriesScreen(mainActivityViewModel)
            }
        }
        mainActivityViewModel.start()
    }
}

@Composable
fun BestStoriesScreen(mainActivityViewModel: MainActivityViewModel) {
    val items: List<Long> by mainActivityViewModel.uiState.observeAsState(initial = listOf())
    MainList(items = items)
}

@Composable
fun MainList(items: List<Long>) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(items = items) {
            ListItem(index = it)
        }

    }
}

@Composable
fun ListItem(index: Long) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(8.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview("Light Theme", showBackground = true)
@Composable
fun LightPreview() {
    MyTheme {
        MainList(listOf())
    }
}

@Preview("Dark Theme", showBackground = true)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MainList(listOf())
    }
}
