package Level1

import java.util.*

class 신규아이디 {
    /*
    *
    *
    새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때, 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
    다음은 카카오 아이디의 규칙입니다.

    아이디의 길이는 3자 이상 15자 이하여야 합니다.
    아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
    단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.
    *
    1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
    2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
    3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
    4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
    5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
    6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
    7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
    *
    *
    * */

    fun solution(new_id: String): String {
        var answer: String = ""

        var ref = new_id.lowercase()
            .filter { it.isLetterOrDigit() || it == '-' || it == '_' || it == '.' }
            .replace("[.]{2,}".toRegex(), ".")

        println(ref)
        ref = removeDot(ref)
        if(ref.isEmpty()) ref = "a"
        if(ref.length >= 16)
            ref = ref.substring(0, 15)

        if(ref.length <= 2) {
            while(ref.length < 3) {
                ref += ref[ref.lastIndex]
            }
        }
        ref = removeDot(ref)

        answer = ref

        return answer
    }

    fun removeDot(ref: String): String {

        var copy = ref

        while(copy.isNotEmpty() && (copy[0] == '.' || copy[copy.lastIndex] == '.')) {
            if (copy.isEmpty()) return copy

            if (copy.isNotEmpty() && copy[0] == '.') {
                copy = copy.replaceFirst(".", "")
            }

            if (copy.isNotEmpty() && copy[copy.lastIndex] == '.') {
                copy = copy.substring(IntRange(0, copy.lastIndex - 1))
            }
        }

        return copy
    }

    fun solution2(newId: String) = newId.lowercase(Locale.getDefault())
        .filter { it.isLowerCase() || it.isDigit() || it == '-' || it == '_' || it == '.' }
        .replace("[.]*[.]".toRegex(), ".")
        .removePrefix(".").removeSuffix(".")
        .let { if (it.isEmpty()) "a" else it }
        .let { if (it.length > 15) it.substring(0 until 15) else it }.removeSuffix(".")
        .let {
            if (it.length <= 2)
                StringBuilder(it).run {
                    while (length < 3) append(it.last())
                    toString()
                }
            else it }

    fun runSolution() {
        println(solution("-.~!@#\$%&*()=+[{]}:?,<>/.-"))
    }

}