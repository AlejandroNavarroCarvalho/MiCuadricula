package com.example.micuadricula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.micuadricula.data.DataSource
import com.example.micuadricula.model.Topic
import com.example.micuadricula.ui.theme.MiCuadriculaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiCuadriculaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicApp()
                }
            }
        }
    }
}

@Composable
fun TopicApp () {
    TopicList(
        topicList = DataSource().loadTopics(),
    )
}

@Composable
fun TopicList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_small)) ,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        columns = GridCells.Fixed(2),
        modifier = modifier) {
        items(topicList) { topic ->
            TopicCard(
                topic = topic
            )
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier
                    .height(dimensionResource(id =R.dimen.padding_superHuge))
                    .width(dimensionResource(id = R.dimen.padding_superHuge)),
                contentScale = ContentScale.Crop
            )
            Column (modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium), start = dimensionResource(
                id = R.dimen.padding_medium
            ), end = dimensionResource(id = R.dimen.padding_medium))){
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small)),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row (modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null)
                    Text(
                        text = topic.points.toString(),
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small)),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MiCuadriculaPreview() {
    MiCuadriculaTheme {
        TopicApp()
    }
}