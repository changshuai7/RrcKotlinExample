fun main() {


    // 100 200 300
    // 200 300 400

    var arr = Array(2) { i ->
        IntArray(3) { j -> (j + 1) * 100 + i * 100 }
    }
    for (i in arr.indices) {
        println(arr[i].contentToString())
    }
}
