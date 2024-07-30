package idea.platform.testtask.utils

fun emptyString() = ""

fun String.dropFirstAndLast(count: Int): String {
    return drop(count).dropLast(count)
}
