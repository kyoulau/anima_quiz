package com.example.anima_quiz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

data class Player(
    val name: String,
    val correctAnswers: Int,
    val averageResponseTime: Double
)

@Composable
fun LeaderboardScreen(players: List<Player>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = "Leaderboard",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(players) { index, player ->
                PlayerItem(position = index + 1, player = player)
            }
        }
    }
}

@Composable
fun PlayerItem(position: Int, player: Player) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Posição
            Text(
                text = "$position",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.width(40.dp)
            )

            // Nome do jogador
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Respostas Corretas: ${player.correctAnswers}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Tempo Médio: ${player.averageResponseTime.roundToInt()} seg",
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
        Player(name = "Alice", correctAnswers = 15, averageResponseTime = 3.2),
        Player(name = "Bob", correctAnswers = 12, averageResponseTime = 4.1),
        Player(name = "Charlie", correctAnswers = 11, averageResponseTime = 5.0),
        Player(name = "David", correctAnswers = 9, averageResponseTime = 6.2),
        Player(name = "Eve", correctAnswers = 8, averageResponseTime = 7.5)
    )
    LeaderboardScreen(players = samplePlayers)
}
