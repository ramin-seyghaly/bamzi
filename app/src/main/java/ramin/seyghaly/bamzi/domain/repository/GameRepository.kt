package ramin.seyghaly.bamzi.domain.repository

import ramin.seyghaly.bamzi.domain.model.Game

interface GameRepository {
    suspend fun getOfflineGames(): List<Game>
}
