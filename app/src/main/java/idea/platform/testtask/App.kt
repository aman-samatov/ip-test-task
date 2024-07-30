package idea.platform.testtask

import android.app.Application
import android.content.pm.ApplicationInfo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        setupKoin()

        setupTimber()
    }

    private fun setupKoin() {
        GlobalContext.stopKoin()
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)

            modules(
                CommonModule.instance,

                DataModule.instance,
                DatabaseModule.instance,
                ProductsModule.instance
            )
        }
    }

    private fun setupTimber() {
        if (isDebugMode()) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

fun Application.isDebugMode(): Boolean {
    return 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
}
