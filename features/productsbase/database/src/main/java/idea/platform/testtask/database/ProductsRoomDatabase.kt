package idea.platform.testtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import idea.platform.testtask.dao.ProductDao
import idea.platform.testtask.model.CalendarConverter
import idea.platform.testtask.model.ListConverter
import idea.platform.testtask.model.ProductDBO

class ProductsDatabase internal constructor(private val database: ProductsRoomDatabase) {

    companion object {
        const val NAME = "products"
        const val INITIAL_DATABASE = "data.db"
    }

    val productDao: ProductDao
        get() = database.productsDao()
}

@Database(entities = [ProductDBO::class], version = 1)
@TypeConverters(ListConverter::class, CalendarConverter::class)
internal abstract class ProductsRoomDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductDao
}

internal fun ProductsDatabase(applicationContext: Context): ProductsDatabase {
    val productsRoomDatabase =
        Room
            .databaseBuilder(
                context = checkNotNull(applicationContext.applicationContext),
                klass = ProductsRoomDatabase::class.java,
                name = ProductsDatabase.NAME
            )
            .createFromAsset(ProductsDatabase.INITIAL_DATABASE)
            .addMigrations()
            .build()
    return ProductsDatabase(productsRoomDatabase)
}
