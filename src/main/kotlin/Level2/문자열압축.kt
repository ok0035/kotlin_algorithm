package Level2

class 문자열압축 {

    fun solution(s: String): Int {

        var answer = s.length
        var sCopied = ""
        var zipNum = 1
        var cnt = 1

        while (zipNum < s.length) {

            cnt = 1
            val ss = s.chunked(zipNum)
            for(i in 1 until ss.size) {
                if(ss[i] == ss[i-1]) {
                    cnt ++
                    if(i == ss.lastIndex)
                        sCopied += cnt.toString() + ss[i]
                } else {
                    sCopied += when (cnt) {
                        1 -> ss[i-1]
                        else -> cnt.toString() + ss[i-1]
                    }
                    if(i == ss.lastIndex)
                        sCopied += ss[i]
                    cnt = 1
                }
            }

            if(sCopied.length < answer) answer = sCopied.length
            sCopied = ""
            zipNum++
        }

        return answer
    }

    fun runSolution() {
//        println(solution("abcabcabcabcdededededede"))
        println(solution("ababcdcdababcdcd"))
    }
}