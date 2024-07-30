package idea.platform.testtask.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val DATE_FORMAT = "dd.MM.yyyy"

private val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)

fun Calendar.formatDate(): String {
    return dateFormatter.format(this.timeInMillis)
}

fun Long.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this

    return calendar
}