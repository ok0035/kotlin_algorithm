package Level2

class 오픈채팅방 {

    var recordMap = mutableMapOf<String, String>()

    fun solution(record: Array<String>): Array<String> {
        var answer = record.toMutableList()

        record.forEach {
            val arr = it.split(" ")
            val command = arr[0]
            val id = arr[1]

            when (command) {
                "Enter" -> recordMap[id] = arr[2]
                "Change" -> recordMap[id] = arr[2]
            }

        }

        answer.mapIndexed { index, s ->
            val command = s.split(' ')[0]
            val id = s.split(" ")[1]

            val resultNick = recordMap[id]

            when (command) {
                "Enter" -> answer[index] = "${resultNick}님이 들어왔습니다."
                "Change" -> answer[index] = ""
                "Leave" -> answer[index] = "${resultNick}님이 나갔습니다."
            }
        }

        return answer.filter {
            it != ""
        }.toTypedArray()
    }

    fun runSolution() {
        println(
            solution(
                arrayOf(
                    "Enter uid1234 Muzi",
                    "Enter uid4567 Prodo",
                    "Leave uid1234",
                    "Enter uid1234 Prodo",
                    "Change uid4567 Ryan"
                )
            ).toList()
        )
    }
}