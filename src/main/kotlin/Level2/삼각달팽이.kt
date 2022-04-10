package Level2

class 삼각달팽이 {

    fun solution2(n: Int): IntArray {
        var answer: IntArray
        val arr = Array(n) { IntArray(n) }
        val max = n * (n + 1) / 2 // 총 개수
        var top = 0 //상단
        var left = 0 //좌측
        var bottom = n - 1 //하단
        var right = n - 1 //우측
        var value = 1
        while (max >= value) {
            for (i in top..bottom) { // 하단이동
                if (max < value) break
                arr[i][left] = value++
            }
            if (max < value) break
            top++
            left++
            for (j in left..right) { // 우측이동
                if (max < value) break
                arr[bottom][j] = value++
            }
            if (max < value) break
            bottom--
            right--
            var index = right
            for (i in bottom downTo top) { //상단 이동
                if (max < value) {
                    break
                }
                arr[i][index--] = value++
            }
            top++
            right--
        }
        answer = IntArray(max)
        var index = 0
        for (i in 0 until n) {
            for (j in 0..i) {
                answer[index++] = arr[i][j]
            }
        }
        return answer
    }

    fun solution(n: Int): IntArray {

        val max = ((n + 1f) * n / 2f).toInt()
        var answer = IntArray(max)
        var triangleArray = Array(n) { IntArray(n) { 0 } }
        var left = 0
        var right = n - 1
        var top = 0
        var bottom = n - 1
        var value = 1

        while (value <= max) {
            for (i in top..bottom) {
                println(value)
                triangleArray[i][left] = value++
                if (value > max) break
            }
            top++
            left++

            for (i in left..right) {
                triangleArray[bottom][i] = value++
                if (value > max) break
            }
            bottom--
            right--

            var rightCopy = right
            for (i in bottom downTo top) {
                triangleArray[i][rightCopy--] = value++
                if (value > max) break
            }
            top++
            right--
        }

        var index = 0
        for (i in 0 until n) {
            for (j in 0..i) {
                print("${triangleArray[i][j]} ")
                answer[index++] = triangleArray[i][j]
            }
        }

        return answer
    }

    fun solution3(n: Int) = Array(n) { num -> IntArray(num + 1) { 0 } }
        .apply {
            var row = 0
            var column = 0
            var movingMode = 0 // 0: 아래쪽, 1: 오른쪽, 2: 왼쪽 위 대각선
            for (i in 1..(n * (n + 1) / 2)) {
                this[row][column] = i
                when(movingMode) {
                    0 -> if(row + 1 >= n || this[row + 1][column] != 0) movingMode = 1
                    1 -> if (column + 1 > row || this[row][column + 1] != 0) movingMode = 2
                    2 -> if (row - 1 < 0 || column - 1 < 0 || this[row-1][column - 1] != 0) movingMode = 0
                }
                when (movingMode) {
                    0 -> row++ // 아리쪽으로 이동
                    1 -> column++ // 오른쪽으로 이동
                    2 -> row-- and column-- // 왼쪽 위 대각선으로 이동
                }
            }
        }
        .fold(arrayListOf<Int>()) { acc, ints -> acc.apply { addAll(ints.toList()) } }
        .toIntArray()

    fun runSolution() {
        println(solution(5))
    }

}