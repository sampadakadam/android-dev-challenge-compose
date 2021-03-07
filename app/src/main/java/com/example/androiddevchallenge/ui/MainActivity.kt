package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Item
import com.example.androiddevchallenge.R
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
    val items: List<Item> by mainActivityViewModel.uiState.observeAsState(initial = listOf())
    MainList(items = items)
}

@Composable
fun MainList(items: List<Item>) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(items = items) {
            ListItem(item = it)
        }

    }
}

@Composable
fun ListItem(item: Item) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Score(score = item.score)
        Spacer(modifier = Modifier.width(8.dp))
        Story(story = item)

    }
}

@Composable
fun Story(story: Item) {
    Column {
        Text(
            text = story.title,
            style = MaterialTheme.typography.subtitle1,
            color = Color.DarkGray
        )
        Text(
            text = story.url,
            style = MaterialTheme.typography.subtitle2,
            color = Color.LightGray
        )
        Row {
            Text(text = "${story.comments.size} comments")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = ",")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = story.by)
        }

    }

}

@Composable
fun Score(score: Long) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(
                    corner = CornerSize(2.dp)
                )
            )
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_upvote_black_24dp),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
        Text(
            color = Color.DarkGray,
            text = "$score",
            textAlign = TextAlign.Center,
        )
    }
}

@Preview("Light Theme", showBackground = true)
@Composable
fun LightPreview() {
    MyTheme {
        MainList(mockListOf())
    }
}

fun mockListOf(): List<Item> {
    var list = mutableListOf<Item>()
    repeat(10) {
        list.add(
            Item(
                id = 1234,
                title = "My YC App",
                time = 1175714200,
                score = 111,
                descendants = 0,
                by = "Sama",
                type = "story",
                url = "http://www.getdropbox.com/u/2/screencast.html",
                kids = listOf(),
            ),
        )
    }
    return list
}


//@Preview("Dark Theme", showBackground = true)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MainList(listOf(
//            Item(
//                id = 1234,
//                title = "My YC App",
//                time = 1175714200,
//                score = 111,
//                descendants = 0,
//                type = "story",
//                url = "http://www.getdropbox.com/u/2/screencast.html",
//                kids = listOf(),
//            )
//        ))
//    }
//}
