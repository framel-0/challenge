package life.league.challenge

import android.app.Application
import life.league.challenge.data.datasource.*
import life.league.challenge.data.repository.*
import life.league.challenge.ui.MainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class ChallengeApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

        import(androidXModule(this@ChallengeApplication))

        bind<NetworkDataSource>() with singleton {
            NetworkDataSourceImpl(
            )
        }

        bind<Repository>() with singleton {
            RepositoryImpl(instance()
            )
        }

        bind() from provider { MainViewModelFactory(instance()) }
    }
}