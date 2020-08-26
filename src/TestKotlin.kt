fun main() {

    var arr = arrayOf<String>("a", "b", "ccccc", "dddd")

    var filter: F<String> = {
        it.length > 1
    }
    val ff = arr.filter(filter)
    println(ff)

}
typealias F<T> = (T) -> Boolean
