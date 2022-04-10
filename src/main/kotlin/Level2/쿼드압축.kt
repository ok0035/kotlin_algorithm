package Level2

import java.util.*
import kotlin.contracts.contract

class 쿼드압축 {

    //여러 함수의 도움이 필요할 때는 멤버변수로 꺼내쓰면 된다.
    //처음 받은 배열이 모두 같은 수일 경우


    private lateinit var quadArr: Array<IntArray>
    private var zero = 0
    private var one = 0

    fun solution(arr: Array<IntArray>): IntArray {
        val answer = IntArray(2)
        quadArr = arr

        countOneZero(arr.size, 0, 0)
        answer[0] = zero
        answer[1] = one
        return answer
    }

    fun countOneZero(size: Int, x: Int, y: Int) {

        if(size == 1) {
            when (quadArr[x][y]) {
                0 -> zero++
                1 -> one++
            }
            return
        }

        var isSame = true
        val first = quadArr[x][y]
        for (row in x until x + size) {
            for (col in y until y + size) {
                if (quadArr[row][col] != first) {
                    isSame = false
                    break
                }
            }
            if (!isSame) break;
        }

        if (isSame) {
            when (quadArr[x][y]) {
                0 -> {
                    zero++
                    return
                }
                1 -> {
                    one++
                    return
                }
            }
        }

        countOneZero(size / 2, x, y)
        countOneZero(size / 2, x, y + size / 2)
        countOneZero(size / 2, x + size / 2, y)
        countOneZero(size / 2, x + size / 2, y + size / 2)

    }


//    private fun dfs(n: Int, x: Int, y: Int) {
//        if (n == 1) {
//            if (map[x][y] == 1) {
//                one++
//            } else {
//                zero++
//            }
//            return
//        }
//        if (isSame(n, x, y)) {
//            return
//        }
//        dfs(n / 2, x, y)
//        dfs(n / 2, x + n / 2, y)
//        dfs(n / 2, x, y + n / 2)
//        dfs(n / 2, x + n / 2, y + n / 2)
//    }
//
//    private fun isSame(n: Int, x: Int, y: Int): Boolean {
//        val first = map[x][y]
//        for (i in x until x + n) {
//            for (j in y until y + n) {
//                if (first != map[i][j]) {
//                    return false
//                }
//            }
//        }
//        if (first == 0) {
//            zero += 1
//        } else {
//            one += 1
//        }
//        return true
//    }

    fun runSolution() {
        println(
            solution(
                arrayOf(
                    intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
                    intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
                    intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
                    intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
                    intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
                    intArrayOf(0, 0, 0, 0, 1, 1, 1, 1)
                )
            ).toList()
        )
    }

}