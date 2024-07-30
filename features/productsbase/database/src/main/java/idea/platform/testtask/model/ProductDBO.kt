package idea.platform.testtask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "products")
data class ProductDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 1,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("time") val time: Calendar,
    @ColumnInfo("tags") val tags: List<String>,
    @ColumnInfo("amount") val amount: Int,
)
