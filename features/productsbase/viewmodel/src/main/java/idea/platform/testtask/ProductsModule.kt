package idea.platform.testtask

import idea.platform.testtask.di.InjectionModule
import idea.platform.testtask.interactor.ProductsInteractor
import idea.platform.testtask.model.ProductItemConverter
import idea.platform.testtask.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object ProductsModule : InjectionModule {
    override val instance: Module by lazy {
        module {
            viewModelOf(::ProductsViewModel)

            factoryOf(::ProductsInteractor)

            factoryOf(::ProductItemConverter)
        }
    }
}