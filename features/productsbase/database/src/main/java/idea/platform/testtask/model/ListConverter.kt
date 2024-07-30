package idea.platform.testtask.model

import androidx.room.TypeConverter
import idea.platform.testtask.utils.dropFirstAndLast

internal class ListConverter {

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        if (value == "[]") return emptyList()

        return value.dropFirstAndLast(1)
            .split(",")
            .map { item ->
                item
                    .trim()
                    .removeSurrounding("\"")
            }

    }

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.toString()
    }

}
