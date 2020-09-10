fun main(args: Array<String>) {

    cal(1, 2) { x, y ->
        x + y
        return
    }
    println("abc")

}

inline fun cal(x: Int, y: Int, fn: (Int, Int) -> Int) {

    println(fn(x, y))

}




