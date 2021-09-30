package edu.upb.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.upb.composeexample.ui.theme.ComposeExampleTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import edu.upb.composeexample.ui.screens.ListScreen
import edu.upb.composeexample.ui.screens.Product

enum class Screens {
    List,
    Profile,
    Settings
}

data class BottomNavigationIcon(val screen: Screens, @DrawableRes val icon: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                var screen by remember { mutableStateOf(Screens.List) }
                val screensList = listOf(
                    BottomNavigationIcon(Screens.List, R.drawable.icons8_email_100__1_),
                    BottomNavigationIcon(Screens.Settings, R.drawable.icons8_chat_bubble_90),
                    BottomNavigationIcon(Screens.Profile, R.drawable.icons8_test_account_90)
                )
                var product by remember { mutableStateOf(listOf(
                    Product("Pruebita 1", "Decripcion 1", 123.98),
                    Product("Pruebita 2", "Decripcion 2", 123.98),
                    Product("Pruebita 3", "Decripcion 3", 123.98),
                    Product("Pruebita 4", "Decripcion 4", 123.98)
                )) }
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Hello!!")
                        })
                    },
                    content = {
                        when (screen) {
                            Screens.List -> ListScreen(product)
                            Screens.Profile -> MainPage()
                            Screens.Settings -> MainPage()
                        }
                    },
                    bottomBar = {
                        BottomNavigation() {
                            screensList.map {
                                BottomNavigationItem(
                                    selected = screen == it.screen,
                                    onClick = {
                                        screen = it.screen
                                    },
                                    icon = {
                                        Image(
                                            painterResource(it.icon),
                                            "content description"
                                        )
                                    })
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MainPage() {
    var progress by remember { mutableStateOf(0f) }
    Column {
        Greeting("Android $progress")
        Slider(
            value = progress,
            onValueChange = {
                progress = it
            })
    }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(12.dp)
    ) {
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExampleTheme {
        Greeting("Android")
    }
}