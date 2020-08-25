fun main() {
    val arrayOf = arrayOf<String>("a", "b", "ccc")

    val f: F<String> = {
        it.length > 1
    }
    val filter = arrayOf.filter(f)
    println(filter)


}
typealias F<T> = (T) -> Boolean


