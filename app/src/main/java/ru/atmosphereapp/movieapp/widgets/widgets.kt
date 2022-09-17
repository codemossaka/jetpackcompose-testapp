package ru.atmosphereapp.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.atmosphereapp.movieapp.R
import ru.atmosphereapp.movieapp.model.Movie
import ru.atmosphereapp.movieapp.model.getMovies


@Preview
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = { },
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
//        .height(130.dp)
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = CircleShape,
                elevation = 4.dp) {
//                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Account")

                DisplayImage(imageUrl = movie.images[0],
                    contentDescription = "ff",
                    modifier = Modifier.clip(CircleShape))
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title,
                    style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.title}",
                    style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)) {
                                append("Plot:")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        },
                            modifier = Modifier.padding(6.dp))
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.caption)
                        Text(text = "Actor: ${movie.actors}",
                            style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.caption)
                    }
                }
                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "fggg",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray)
            }
        }
    }
}

@Composable
fun DisplayImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

