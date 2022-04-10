package Level1

class 로또 {

    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        var answer: IntArray = IntArray(2)

        val matchCnt = lottos.sortedDescending()
            .filter {
                win_nums.sortedDescending().contains(it)
            }.toList().size

        val zeroCnt = lottos.filter {
            it == 0
        }.size

        val low = matchCnt
        val high = matchCnt + zeroCnt

        answer[0] = cal(high)
        answer[1] = cal(low)
        return answer
    }

    fun cal(num: Int): Int {
        return when(num) {
            2-> 5
            3-> 4
            4-> 3
            5-> 2
            6-> 1
            else -> 6
        }
    }

    fun runSolution() {
        println(solution(intArrayOf(44, 1, 0, 0, 31, 25), intArrayOf(31, 10, 45, 1, 6, 19)).toList())
    }

}