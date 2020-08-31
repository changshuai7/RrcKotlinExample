fun main() {


    var a = arrayOf<String>("a", "b", "c")
    var b = arrayOfNulls<String>(4)
    val c = emptyArray<String>()

    var d = Array<String>(4) { "$it = hello" }
    for (item in d) {
        println(item)
    }

}
