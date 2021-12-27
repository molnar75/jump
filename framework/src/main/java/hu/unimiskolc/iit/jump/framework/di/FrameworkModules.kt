package hu.unimiskolc.iit.jump.framework.di

import hu.unimiskolc.iit.jump.core.data.datasource.ScoreDataSource
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

val interactorModule = module {
}