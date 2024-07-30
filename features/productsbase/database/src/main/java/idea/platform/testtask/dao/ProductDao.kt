package idea.platform.testtask.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import idea.platform.testtask.model.ProductDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE name LIKE :name")
    fun observeAll(name: String): Flow<List<ProductDBO>>

    @Update
    suspend fun update(product: ProductDBO)

    @Delete
    suspend fun remove(product: ProductDBO): Int
}
