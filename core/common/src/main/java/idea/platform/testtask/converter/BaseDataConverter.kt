package idea.platform.testtask.converter

interface BaseDataConverter<R, D> {
    fun convert(response: R): D
    fun revert(data: D): R
}

fun <R, D> List<R>.convertList(converter: BaseDataConverter<R, D>): List<D> {
    return map { converter.convert(it) }
}

fun <D, R> List<D>.revertList(converter: BaseDataConverter<R, D>): List<R> {
    return map { converter.revert(it) }
}
