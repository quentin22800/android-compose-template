package org.mrlem.sample.compose.feature.filmslist.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.mrlem.sample.compose.design.theme.ComposeSampleTheme
import org.mrlem.sample.compose.design.theme.Typography
import org.mrlem.sample.compose.feature.filmslist.ui.FilmsListViewState
import org.mrlem.sample.compose.feature.filmslist.ui.R
import org.mrlem.sample.compose.feature.ghibli.domain.model.Film

@Composable
internal fun Layout(
    state: FilmsListViewState = FilmsListViewState(),
    onClick: (String) -> Unit = {},
    onRefresh: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.filmslist),
                        modifier = Modifier
                            .padding(top = 24.dp),
                    )
                },
                modifier = Modifier
                    .height(84.dp),
            )
        }
    ) { padding ->

        SwipeRefresh(
            state = rememberSwipeRefreshState(
                isRefreshing = state.isRefreshing,
            ),
            onRefresh = onRefresh,
            modifier = Modifier
                .padding(padding),
        ) {
            if (state.films.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.filmslist_empty),
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    style = Typography.body1,
                    modifier = Modifier
                        .padding(padding)
                        .padding(top = 64.dp)
                        .fillMaxSize(),
                )
            } else {
                LazyColumn {
                    items(state.films) { film ->
                        Item(
                            film = film,
                            onClick = onClick,
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyPreview() {
    ComposeSampleTheme {
        Layout(
            state = FilmsListViewState(emptyList())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ComposeSampleTheme {
        Layout(
            state = FilmsListViewState(listOf(
                Film(
                    id = "123",
                    title = "Le Voyage de Chihiro",
                    originalTitle = "千と千尋の神隠し",
                    originalTitleRomanised = "Sen to Chihiro no kamikakushi",
                    description = "Le film raconte l'histoire de Chihiro, une fillette de dix ans qui, alors qu'elle se rend en famille vers sa nouvelle maison, entre dans le monde des esprits. Après la transformation de ses parents en porcs par la sorcière Yubaba, Chihiro prend un emploi dans l'établissement de bains de la sorcière pour retrouver ses parents et regagner le monde des humains.",
                    director = "Hayao Miyazaki",
                    releaseDate = "2001",
                    coverImage = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rtGDOeG9LzoerkDGZF9dnVeLppL.jpg",
                    bannerImage = "https://image.tmdb.org/t/p/original/etqr6fOOCXQOgwrQXaKwenTSuzx.jpg",
                ),
                Film(
                    id = "123",
                    title = "Le Voyage de Chihiro",
                    originalTitle = "千と千尋の神隠し",
                    originalTitleRomanised = "Sen to Chihiro no kamikakushi",
                    description = "Le film raconte l'histoire de Chihiro, une fillette de dix ans qui, alors qu'elle se rend en famille vers sa nouvelle maison, entre dans le monde des esprits. Après la transformation de ses parents en porcs par la sorcière Yubaba, Chihiro prend un emploi dans l'établissement de bains de la sorcière pour retrouver ses parents et regagner le monde des humains.",
                    director = "Hayao Miyazaki",
                    releaseDate = "2001",
                    coverImage = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rtGDOeG9LzoerkDGZF9dnVeLppL.jpg",
                    bannerImage = "https://image.tmdb.org/t/p/original/etqr6fOOCXQOgwrQXaKwenTSuzx.jpg",
                ),
            ))
        )
    }
}