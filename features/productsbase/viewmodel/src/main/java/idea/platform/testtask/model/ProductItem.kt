package idea.platform.testtask.model

import androidx.compose.runtime.Immutable
import idea.platform.testtask.utils.formatDate
import java.util.Calendar

@Immutable
data class ProductItem(
    val id: Long = 0,
    val name: String,
    val time: Calendar,
    val tags: List<String>,
    val amount: Amount,
) {
    fun getDate() = time.formatDate()


    fun hasTags(): Boolean {
        return tags.isNotEmpty()
    }


    @JvmInline
    value class Amount(
        val amount: Int
    ) {
        companion object {
            val EMPTY = Amount(0)
        }

        init {
            require(amount >= 0) {
                "Amount cannot be negative"
            }
        }

        fun increment(): Amount {
            return Amount(amount = amount + 1)
        }

        fun decrement(): Amount {
            if (amount == 0) return EMPTY

            return Amount(amount = amount - 1)
        }

        fun printValue() = amount.toString()
    }
}

