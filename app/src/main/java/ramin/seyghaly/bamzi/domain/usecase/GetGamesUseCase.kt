package ramin.seyghaly.bamzi.domain.usecase

import ramin.seyghaly.bamzi.domain.model.Game
import ramin.seyghaly.bamzi.domain.repository.GameRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(): List<Game> {
        return gameRepository.getOfflineGames()
    }
}
