package com.nirali.sample.recipe.screeen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nirali.interview.R
import com.nirali.sample.recipe.components.CustomText
import com.nirali.sample.recipe.domain.model.Data
import com.nirali.sample.recipe.viewmodel.DogViewmodel

@Preview
@Composable
fun RecipeScreen() {
    RecipeState()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeState(dogViewmodel: DogViewmodel = hiltViewModel()) {
    var searchQuery by remember { mutableStateOf("") }

    val dogState by dogViewmodel.dogState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        dogViewmodel.getBreeds()
    }

    Scaffold(
        topBar =
        {
            CenterAlignedTopAppBar(title = {
                Text(text = "Recipe")
            })
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                placeholder = { Text("Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = ("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Search"
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    containerColor = Color.Transparent, // Transparent background to show border
                    focusedIndicatorColor = Color.Transparent, // Hide the indicator
                    unfocusedIndicatorColor = Color.Transparent // Hide the indicator
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(26.dp))
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = "Category", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color(0xFF4A4A4A),
                )
            )
            val items = List(20) { "Item $it" }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),


                ) {
                itemsIndexed(items)
                { index, item ->
                    CategoryItem(index)
                }


            }
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Recommendation for Vegan", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold, color = Color(0xFF4A4A4A),
                )
            )

            dogState.let {
                when {
                    it.isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    it.isError -> {
                        Text(
                            text = "Error", modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp)
                        )
                    }

                    it.breedList?.data?.isNotEmpty() == true -> {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            items(it.breedList.data)
                            { item ->
                                DataItem(item)
                            }


                        }

                    }

                }
            }


        }


    }


}

@Composable
fun DataItem(item: Data) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(100.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = "https://fastly.picsum.photos/id/4/5000/3333.jpg?hmac=ghf06FdmgiD0-G4c9DdNM8RnBIN7BO0-ZGEw47khHP4",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()

            )


        }
        CustomText(
            text = "${item.attributes.name}", modifier = Modifier
                .fillMaxWidth(),
            textStyle =
            TextStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = "https://fastly.picsum.photos/id/22/4434/3729.jpg?hmac=fjZdkSMZJNFgsoDh8Qo5zdA_nSGUAWvKLyyqmEt2xs0",
                contentDescription = "test1",
                modifier = Modifier
                    .size(30.dp)
                    .border(1.dp, Color.Gray, CircleShape)
                    .clip(CircleShape),
            )
            CustomText(
                text = " ${item.attributes.life.min} to ${item.attributes.life.max}",
                modifier = Modifier
                    .fillMaxWidth().align(Alignment.CenterVertically),
                textStyle =
                TextStyle(fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            )
            Icon(
                imageVector = Icons.Default.People,
                contentDescription = "Search Icon",
                modifier = Modifier.size(20.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun CategoryItem(index: Int) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .border(1.dp, color = Color.Transparent, CircleShape)
                    .clip(CircleShape)
                    .alpha(0.8f),
                model = "https://fastly.picsum.photos/id/27/3264/1836.jpg?hmac=p3BVIgKKQpHhfGRRCbsi2MCAzw8mWBCayBsKxxtWO8g",
                contentDescription = "",
                contentScale = ContentScale.Crop


            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Breakfast $index",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

        }

    }
}
