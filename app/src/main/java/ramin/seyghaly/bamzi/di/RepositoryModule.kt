package ramin.seyghaly.bamzi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ramin.seyghaly.bamzi.data.repository.GameRepositoryImpl
import ramin.seyghaly.bamzi.domain.repository.GameRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGameRepository(
        gameRepositoryImpl: GameRepositoryImpl
    ): GameRepository
}
