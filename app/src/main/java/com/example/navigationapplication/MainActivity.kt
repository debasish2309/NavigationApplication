package com.example.navigationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/**
 * NavHost : The area where the screen renders is called as navhost
 * NavGraph : the information from where we need to move to which is called as navgraph
 * NavController : the management for navgraph and navhost it also manages backstack
 *
 * NavArguments : Which argument to pass if any
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration") {
        composable(route = "registration") {
       //     RegistrationScreen(navController)
            RegistrationScreen{
                navController.navigate("main/${it}")
            }
        }
        composable(route = "login") {
            LoginScreen()
        }
        composable(route = "main/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )) {
            val email = it.arguments?.getString("email")
            if (email != null) {
                MainScreen(email)
            }
        }
    }
}

//@Composable
//fun RegistrationScreen(navController: NavController) {
//    Text(
//        text = "Registration",
//        style = MaterialTheme.typography.headlineMedium,
//        modifier = Modifier.clickable {
//            navController.navigate("login")
//        })
//}

@Composable
fun RegistrationScreen(onClick : (email: String) -> Unit) {
    Text(
        text = "Registration",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.clickable {
            onClick("deb@gmail.com")
        }
    )
}

@Composable
fun LoginScreen() {
    Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun MainScreen(email: String) {
    Text(text = "Main - $email", style = MaterialTheme.typography.headlineMedium)
}

