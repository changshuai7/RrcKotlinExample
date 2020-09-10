fun main(args: Array<String>) {

    // 计算从5！

    //f(n) = n*f(n-1)
    val times = times(5)
    println(times)
}

//fun times(n: Int): Int {
//    if (n == 1) {
//        return 1
//    } else {
//        return times(n - 1) * n
//    }
//}


tailrec fun times(n: Int, total: Int = 1): Int {
    if (n == 1) {
        return total
    } else {
        return times(n - 1, total * n)
        //不需要回溯
    }
}






