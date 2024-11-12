package com.example.anima_quiz.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.anima_quiz.feature.data.model.Player
import com.example.anima_quiz.feature.data.viewModel.PlayerViewModel

@Composable
fun QuizCompleted(
    playerViewModel: PlayerViewModel,
    nav: NavHostController,
    score: Int,
    total: Int,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {

    fun handleButtonClick() {
        // Retrieve the username from the previous screen's saved state
        val userName = nav.previousBackStackEntry?.savedStateHandle?.get<String>("userName")

        if (userName != null) {
            // Create or update the Player object
            val player = Player(
                nickname = userName,
                score = score,
                higherScore = score // Set higherScore to current score initially
            )
            playerViewModel.insertOrUpdatePlayer(player)
        }

        // Continue with any other logic after the score is saved
        onContinue()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(32.dp)
        ) {
            Text(
                text = "Quiz Completed!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Your Score \n $score / ${total * 1000}",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp),
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { handleButtonClick() },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            ) {
                Text("Continuar", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
