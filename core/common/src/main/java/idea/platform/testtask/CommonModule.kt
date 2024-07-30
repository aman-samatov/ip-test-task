package idea.platform.testtask

import android.content.Context
import android.content.SharedPreferences
import idea.platform.testtask.di.InjectionModule
import idea.platform.testtask.dispatchers.CoroutineDispatchers
import idea.platform.testtask.dispatchers.DefaultDispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object CommonModule : InjectionModule {

    override val instance: Module by lazy {
        module {
            singleOf<CoroutineDispatchers>(::DefaultDispatchers) bind CoroutineDispatchers::class
        }
    }
}