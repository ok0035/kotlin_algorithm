package Level2

class 기능개발 {

    /*
        * 선행되는 작업이 완료되기 전까지 후행되는 작업은 배포를 할 수가 없다.
        * 선행되는 작업이 끝날 때 완료된 다른 작업의 수를 배열에 저장한다.
        * */

    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        var time = (100 - progresses[0]) / speeds[0]
        if((100 - progresses[0]) % speeds[0] != 0) time+=1
        var release = 1

        for (i in 1 until progresses.size) {
            var remain = (100 - progresses[i]) / speeds[i]
            if((100 - progresses[i]) % speeds[i] != 0) remain+=1

            if(remain <= time) release++
            else {
                answer.add(release)
                release = 1
                time = remain
            }
            if(i == progresses.lastIndex)
                answer.add(release)
        }

        return answer.toIntArray()
    }

    fun runSolution() {
        println(solution(intArrayOf(20, 99, 93, 30, 55, 10), intArrayOf(5, 10, 1, 1, 30, 5)).toList())
    }

}