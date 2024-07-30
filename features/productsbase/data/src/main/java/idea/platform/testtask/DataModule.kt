package idea.platform.testtask

import idea.platform.testtask.di.InjectionModule
import idea.platform.testtask.model.ProductConverter
import idea.platform.testtask.repository.ProductsRepository
import idea.platform.testtask.repository.ProductsRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DataModule : InjectionModule {
    override val instance: Module by lazy {
        module {
            singleOf(::ProductsRepositoryImpl) bind ProductsRepository::class

            factoryOf(::ProductConverter)
        }
    }
}