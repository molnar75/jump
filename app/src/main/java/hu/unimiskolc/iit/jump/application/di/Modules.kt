package hu.unimiskolc.iit.jump.application.di

import hu.unimiskolc.iit.jump.application.fragment.EndGameViewModel
import hu.unimiskolc.iit.jump.application.fragment.GameViewModel
import hu.unimiskolc.iit.jump.application.fragment.StartGameViewModel
import hu.unimiskolc.iit.jump.framework.db.JumpDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { JumpDatabase.getInstance(androidContext()) }

    viewModel { GameViewModel(get()) }
    viewModel { StartGameViewModel() }
    viewModel { EndGameViewModel(get()) }
}