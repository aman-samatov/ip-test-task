package idea.platform.testtask.model

import androidx.compose.runtime.Immutable
import idea.platform.testtask.utils.emptyString

@Immutable
data class SearchData(
    val name: String
) {
    companion object {
        val EMPTY_PARAMETERS = SearchData(emptyString())
    }

    fun hasNotBlankParameters(): Boolean {
        return !(name.isNotEmpty() and name.isBlank())
    }

    fun isEmpty(): Boolean {
        return this == EMPTY_PARAMETERS
    }

    fun isErasable(): Boolean {
        return name.isNotEmpty() and name.isNotBlank()
    }
}
