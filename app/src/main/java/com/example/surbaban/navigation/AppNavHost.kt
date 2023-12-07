package com.example.surbaban.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.surbaban.ui.theme.screens.home.HomeScreen
import com.example.surbaban.ui.theme.screens.login.LoginScreen
import com.example.surbaban.ui.theme.screens.products.Add_ProductsScreen
import com.example.surbaban.ui.theme.screens.products.ViewProductsScreen
import com.example.surbaban.ui.theme.screens.signup.SignupScreen



@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = VIEW_PRODUCTS_URL
){
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ){
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(ADD_PRODUCTS_URL){
            Add_ProductsScreen(navController = navController)
        }
        composable(VIEW_PRODUCTS_URL){
            ViewProductsScreen(navController = navController)
        }
    }

}