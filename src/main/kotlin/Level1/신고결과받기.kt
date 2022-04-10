package Level1

class 신고결과받기 {

    fun solution2(id_list: Array<String>, report: Array<String>, k: Int): IntArray =
        report.map { it.split(" ") }
            .groupBy { it[1] }
            .asSequence() // 지연계산
            .map { it.value.distinct() }
            .filter { it.size >= k }
            .flatten()
            .map { it[0] }
            .groupingBy { it }
            .eachCount()
            .run { id_list.map { getOrDefault(it, 0) }.toIntArray() }

    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        var answer: IntArray = IntArray(id_list.size) { 0 }
        var repoter: MutableMap<String, MutableList<String>> = mutableMapOf()
        var reported: MutableMap<String, Int?> = mutableMapOf()

        report.forEach {
            val r = it.split(" ")
            if(repoter[r[0]] == null)
                repoter[r[0]] = mutableListOf()
            repoter[r[0]]!!.add(r[1])
            repoter[r[0]] = repoter[r[0]]!!.distinct().toMutableList()
        }

        repoter.forEach {
            it.value.forEach {i ->
                if(reported[i] == null)
                    reported[i] = 1
                else reported[i] = reported[i]?.plus(1)
            }
        }

        val result = reported.filter {
            println(it.value)
            it.value!! >= k
        }.map {
            it.key
        }.toList()

        id_list.forEachIndexed { index, s ->
            var cnt = 0
            if(repoter[s] != null) {
                repoter[s]!!.forEach {
                    result.forEach { reported ->
                        if (it == reported) {
                            cnt++
                        }
                    }
                }
                answer[index] = cnt
            }
        }


        return answer
    }

    fun runSolution() {

        val id_list = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi")
        val k = 2

        println(solution(id_list, report, k).toList())

    }

}