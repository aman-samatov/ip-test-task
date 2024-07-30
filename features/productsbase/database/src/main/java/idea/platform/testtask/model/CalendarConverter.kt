package idea.platform.testtask.model

import androidx.room.TypeConverter
import idea.platform.testtask.utils.toCalendar
import java.util.Calendar

internal class CalendarConverter {

    @TypeConverter
    fun fromCalendarToLong(calendar: Calendar): Long {
        return calendar.timeInMillis
    }

    @TypeConverter
    fun fromLongToCalendar(value: Long): Calendar {
        return value.toCalendar()
    }

}
