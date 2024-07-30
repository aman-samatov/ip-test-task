package idea.platform.testtask

import android.content.Context
import idea.platform.testtask.database.ProductsDatabase
import idea.platform.testtask.di.InjectionModule
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseModule : InjectionModule {

    override val instance: Module by lazy {
        module {
            single<ProductsDatabase> { ProductsDatabase(get<Context>()) }
        }
    }
}