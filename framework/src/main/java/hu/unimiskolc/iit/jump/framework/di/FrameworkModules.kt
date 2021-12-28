package hu.unimiskolc.iit.jump.framework.di

import hu.unimiskolc.iit.jump.core.data.datasource.ScoreDataSource
import hu.unimiskolc.iit.jump.core.data.repository.ScoreRepository
import hu.unimiskolc.iit.jump.core.interactor.EndGame
import hu.unimiskolc.iit.jump.core.interactor.JumpInteractors
import hu.unimiskolc.iit.jump.core.interactor.GetResult
import hu.unimiskolc.iit.jump.framework.db.JumpDatabase
import hu.unimiskolc.iit.jump.framework.db.datasource.RoomJumpScoreDataSource
import hu.unimiskolc.iit.jump.framework.db.mapper.JumpScoreMapper
import org.koin.dsl.module

val daoModule = module {
    single {get<JumpDatabase>().jumpScoreDao()}
}

val dataSourceModule = module {
    single<ScoreDataSource> {RoomJumpScoreDataSource(get(), JumpScoreMapper())}
}

val repositoryModule = module {
    single { ScoreRepository(get()) }
}

val interactorModule = module {
    single { GetResult(get()) }
    single { EndGame(get())}
    single { JumpInteractors(get(), get())}
}