package Level2

class 이진변환 {
    fun solution(s: String): IntArray {

        var ss = s
        var zeroCount = 0
        var binaryCount = 0

        while(ss != "1") {

            val oldSize = ss.length
            ss = ss.replace("0", "")
            val newSize = ss.length
            var sss = ss.length
            zeroCount += oldSize - newSize
            var binary = ""
            while(sss != 0) {
                val remainder = sss % 2
                sss /= 2
                binary += remainder
            }
            ss = binary.reversed()
            binaryCount++
        }
        var answer: IntArray = intArrayOf(binaryCount, zeroCount)
        return answer
    }

    fun solution2(s: String): IntArray {
        var copiedS = s
        var removedZero = 0
        var count = 0

        while (copiedS != "1") {
            removedZero += copiedS.replace("1", "").count()
            copiedS = Integer.toBinaryString(copiedS.replace("0", "").count())
            count++
        }
        return intArrayOf(count, removedZero)
    }

    fun runSolution() {
        println(solution("110010101001").toList())
        println(solution("01110").toList())
    }

}