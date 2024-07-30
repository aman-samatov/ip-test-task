package idea.platform.testtask.model

import java.util.Calendar

data class Product(
    val id: Long = 0,
    val name: String,
    val time: Calendar,
    val tags: List<String>,
    val amount: Int,
)
