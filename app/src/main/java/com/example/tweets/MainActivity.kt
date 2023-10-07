package com.example.tweets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweets.screens.CategoryScreen
import com.example.tweets.screens.DetailScreen
import com.example.tweets.ui.theme.TweetsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetsTheme {
                Scaffold(
                    topBar = {
                       TopAppBar(
                           colors = TopAppBarDefaults.smallTopAppBarColors(
                               containerColor = Color.White,
                               titleContentColor = Color.White
                           ),
                           title = {
                                 Text(text = "Tweets",
                                     modifier = Modifier
                                         .fillMaxWidth(1f)
                                         .padding(20.dp,0.dp,20.dp,0.dp),
                                     color = Color.Black,
                                     style = TextStyle(
                                         textAlign = TextAlign.Center,
                                         fontSize = 20.sp,
                                         fontWeight = FontWeight.Bold
                                     )

                                 )
                       },
                           navigationIcon = {
                               Box(modifier = Modifier
                                   .padding(15.dp)
                               )
                              Image(
                                  imageVector = Icons.Default.Home,
                                  contentDescription =  "Home"
                              )
                           })
                    }
                ) {
                    Box(modifier = Modifier.padding(it)){
                        App()
                    }
                }

            }
        }
    }
}


@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category"){

        composable("category"){
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }

        composable("detail/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ){
            DetailScreen()
        }
    }
}