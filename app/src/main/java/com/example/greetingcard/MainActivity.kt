package com.example.greetingcard

import android.graphics.Color.rgb
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greetingcard.databinding.ActivityAnimalBinding
import com.example.greetingcard.databinding.ActivityAnotherBinding
import com.example.greetingcard.databinding.ActivityCalculatorBinding
import com.example.greetingcard.databinding.ActivityMusicBinding
import com.example.greetingcard.databinding.ActivityRegisterBinding
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "greeting"){
                    composable("greeting") { GreetingScreen(navController) }
                    composable("another") { AnotherScreen(navController) }
                    composable("music") { MusicScreen(navController) }
                    composable("calculator") { CalculatorScreen(navController)}
                    composable("animal") { AnimalScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(rgb(66, 180, 204))) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column (modifier = Modifier.padding(25.dp) ){
                Text(
                    text = "Hello, Try!Try!Try! Mory!",
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 10.dp)
                )
                Button(onClick = { navController.navigate("another") }) {
                    Text(text = "Go to Show Screen")
                }
            }
        }
    }
}

@Composable
fun AnotherScreen(navController: NavHostController) {
    AndroidViewBinding(ActivityAnotherBinding::inflate) {
        buttonBack.setOnClickListener {
            navController.navigate("greeting")
        }
        navButton1.setOnClickListener {
            navController.navigate("music")
        }
        navButton2.setOnClickListener {
            navController.navigate("calculator")
        }
        navButton3.setOnClickListener {
            navController.navigate("animal")
        }
        registerButton.setOnClickListener {
            navController.navigate("register")
        }
    }
}

@Composable
fun MusicScreen(navController: NavHostController) {
    val context = LocalContext.current
    AndroidViewBinding(ActivityMusicBinding::inflate) {
        btnMiddle.setOnClickListener {
            val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate)
            btnIcon.startAnimation(rotateAnimation)
        }
    }
}

@Composable
fun CalculatorScreen(navController: NavHostController) {
    AndroidViewBinding(ActivityCalculatorBinding::inflate) {
    }
}

@Composable
fun AnimalScreen(navController: NavHostController) {
    AndroidViewBinding(ActivityAnimalBinding::inflate) {
    }
}

@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    AndroidViewBinding(ActivityRegisterBinding::inflate) {
        buttonRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val checkPassword = etCheckPassword.text.toString()
            val isMsChecked = checkboxMs.isChecked
            val isMrsChecked = checkboxMrs.isChecked
            val isAgreeChecked = checkboxAgree.isChecked

            if (isAgreeChecked) {
                when {
                    username.isEmpty() -> {
                        Toast.makeText(context, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    email.isEmpty() -> {
                        Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    password.isEmpty() -> {
                        Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    password != checkPassword -> {
                        etCheckPassword.error = "Passwords do not match"
                    }
                    isMsChecked == isMrsChecked -> {
                        Toast.makeText(context, "You must choice the sex", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                    }
                }
            }else {
                Toast.makeText(context, "You must agree to the terms", Toast.LENGTH_SHORT).show()
            }
        }

        checkboxMs.setOnClickListener {
            if (checkboxMrs.isChecked) {
                checkboxMrs.isChecked = false
            }
        }

        checkboxMrs.setOnClickListener {
            if (checkboxMs.isChecked) {
                checkboxMs.isChecked = false
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        GreetingScreen(rememberNavController())
    }
}