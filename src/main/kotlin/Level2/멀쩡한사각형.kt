package Level2

class 멀쩡한사각형 {

    var lst : MutableList<Long> = mutableListOf()

    fun solution(w: Int, h: Int): Long {
        var answer: Long = 0

        lst.add(w.toLong())
        lst.add(h.toLong())

        var gcd = gcd()
        var pw = w.toLong() / gcd
        var ph = h.toLong() / gcd
        var pn = pw + ph -1//패턴 사각형에서 선이 지나가는 사각형의 개수 2 X 3 일 경우 4가 나와야함
        answer = w.toLong() * h.toLong() - (pn * gcd)

        return answer
    }

    fun gcd(): Long {

        lst.sortDescending()
        if(lst[1] == 0L) return lst[0]

        var a = lst[0]
        var b = lst[1]

        var r = a % b
        lst[0] = b
        lst[1] = r

        return gcd()
    }

    fun runSolution() {
        println(solution(8, 12))
    }

}