package com.example.anima_quiz.ui.screens

import android.os.AsyncTask
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anima_quiz.feature.data.dao.PlayerDao
import com.example.anima_quiz.feature.data.model.Player
import com.example.anima_quiz.feature.data.repository.PlayerRepository
import com.example.anima_quiz.feature.data.viewModel.PlayerViewModel


@Composable
fun LeaderboardScreen(onRestart: () -> Unit = {}, sair: () -> Unit = {}, playerViewModel: PlayerViewModel) {
    val players by playerViewModel.players.collectAsState(initial = emptyList())

    val limparPlacar: () -> Unit = {
        playerViewModel.clearLeaderboard()
    }

    // While players is empty (still loading), show a CircularProgressIndicator
    if (players.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Leaderboard",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onRestart,
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Responder Quiz", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = sair,
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Sair", color = Color.White, fontWeight = FontWeight.Bold)
            }

            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp)) // Loading spinner
            Text(
                text = "Leaderboard is empty",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    } else {
        // Once players are loaded, show the Leaderboard
        Leaderboard(players = players, onRestart = onRestart, sair = sair, limparPlacar = limparPlacar)
    }


}

@Composable
fun Leaderboard(players: List<Player> = emptyList(), onRestart: () -> Unit = {}, sair: () -> Unit = {}, limparPlacar: () -> Unit = {}) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Header
        item {
            Text(
                text = "Leaderboard",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onRestart,
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Repetir Quiz", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = limparPlacar,
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text("Limpar Placar", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = sair,
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Sair", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        // Player list
        itemsIndexed(players) { index, player ->
            PlayerItem(position = index + 1, player = player)
        }
    }
}

@Composable
fun PlayerItem(position: Int, player: Player) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$position",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.width(40.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.nickname,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Pontuação: ${player.score}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Maior Pontuação: ${player.higherScore}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeaderboardScreen() {
    val samplePlayers = listOf(
        Player(nickname = "Player 1", score = 100, higherScore = 200),
        Player(nickname = "Player 2", score = 80, higherScore = 150),
        Player(nickname = "Player 3", score = 120, higherScore = 250),
        Player(nickname = "Player 4", score = 90, higherScore = 180),
        Player(nickname = "Player 5", score = 110, higherScore = 220),
        Player(nickname = "Player 6", score = 70, higherScore = 160),
        Player(nickname = "Player 7", score = 95, higherScore = 195),
        Player(nickname = "Player 8", score = 105, higherScore = 215),
        Player(nickname = "Player 9", score = 85, higherScore = 175),
        Player(nickname = "Player 10", score = 130, higherScore = 280),
        Player(nickname = "Player 11", score = 115, higherScore = 230),
        Player(nickname = "Player 12", score = 75, higherScore = 165),
        Player(nickname = "Player 13", score = 125, higherScore = 255),
        Player(nickname = "Player 14", score = 85, higherScore = 185),
        Player(nickname = "Player 15", score = 135, higherScore = 285)
    )
    Leaderboard(players = samplePlayers)
}