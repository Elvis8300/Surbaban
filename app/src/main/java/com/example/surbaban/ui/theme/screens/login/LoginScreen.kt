package com.example.surbaban.ui.theme.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.surbaban.data.AuthViewModel
import com.example.surbaban.navigation.LOGIN_URL
import com.example.surbaban.navigation.SIGNUP_URL
import com.example.surbaban.ui.theme.SurbabanTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController,){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text ="Login",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(20.dp))
        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Enter Email")},
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )

        )
        Spacer(modifier = Modifier.height(20.dp))
        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Enter Password")},
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )

        )
        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context )

        Button(onClick ={
            authViewModel.login(email, password)
        }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick ={
            navController.navigate(SIGNUP_URL)
        }) {
            Text(text = "Register instead")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreen(){
    SurbabanTheme {
        LoginScreen(navController = rememberNavController())
    }
}