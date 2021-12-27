package hu.unimiskolc.iit.jump.application

import android.app.Application
import hu.unimiskolc.iit.jump.application.di.appModule
import hu.unimiskolc.iit.jump.framework.di.daoModule
import hu.unimiskolc.iit.jump.framework.di.dataSourceModule
import hu.unimiskolc.iit.jump.framework.di.interactorModule
import hu.unimiskolc.iit.jump.framework.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JumpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JumpApplication)
            modules(listOf(appModule, daoModule, dataSourceModule, repositoryModule, interactorModule))
        }
    }
}