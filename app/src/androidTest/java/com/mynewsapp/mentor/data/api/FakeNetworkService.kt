package com.mynewsapp.mentor.data.api

import com.mynewsapp.mentor.data.model.sources.SourcesResponse
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.data.model.topHeadines.Source
import com.mynewsapp.mentor.data.model.topHeadines.TopHeadlinesResponse
import com.mynewsapp.mentor.di.api.NetworkService

class FakeNetworkService : NetworkService{
    override suspend fun getTopHeadlines(country: String): TopHeadlinesResponse {
        val listOfArticle = listOf(
            Article(
                title = "title1",
                description = "description1",
                url = "https://www.cnn.com/2022/12/23/weather/christmas-arctic-winter-storm-poweroutages-friday/index.html",
                imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/221223020829-18-winter-weather-1222-illinois.jpg?c=16x9&q=w_800,c_fill",
                source = Source("id1", "name1")
            ),
            Article(
                title = "title2",
                description = "description2",
                url = "https://www.cnn.com/2022/12/23/weather/christmas-arctic-winter-storm-poweroutages-friday/index.html",
                imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/221223020829-18-winter-weather-1222-illinois.jpg?c=16x9&q=w_800,c_fill",
                source = Source("id2", "name2")
            ),
            Article(
                title = "title3",
                description = "description3",
                url = "https://www.cnn.com/2022/12/23/weather/christmas-arctic-winter-storm-poweroutages-friday/index.html",
                imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/221223020829-18-winter-weather-1222-illinois.jpg?c=16x9&q=w_800,c_fill",
                source = Source("id3", "name3")
            ),
            Article(
                title = "title4",
                description = "description4",
                url = "https://www.cnn.com/2022/12/23/weather/christmas-arctic-winter-storm-poweroutages-friday/index.html",
                imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/221223020829-18-winter-weather-1222-illinois.jpg?c=16x9&q=w_800,c_fill",
                source = Source("id4", "name4")
            ),
            Article(
                title = "title5",
                description = "description5",
                url = "https://www.cnn.com/2022/12/23/weather/christmas-arctic-winter-storm-poweroutages-friday/index.html",
                imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/221223020829-18-winter-weather-1222-illinois.jpg?c=16x9&q=w_800,c_fill",
                source = Source("id5", "name5")
            )
        )
        return TopHeadlinesResponse("ok", 5, listOfArticle)
    }

    override suspend fun getSources(): SourcesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchResult(
        search: String,
        pageSize: Int,
        page: Int
    ): TopHeadlinesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsWithLanguageResult(language: String): TopHeadlinesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsWithCountryResult(country: String): TopHeadlinesResponse {
        TODO("Not yet implemented")
    }


}