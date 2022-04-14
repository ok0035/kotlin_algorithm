package Level2

class 행렬테두리회전 {

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = mutableListOf<Int>()
        var square = mutableListOf<MutableList<Int>>()
        var resultList = mutableListOf<Int>()

        //create square
        for (i in 0 until rows) {
            var cols = mutableListOf<Int>()
            for (j in 1..columns) {
                cols.add(columns * i + j)
            }
            square.add(cols)
        }

        queries.forEach {
            //0행 1열 -> col_start
            //0행 3열 -> col_end
            //2행 1열 -> row_start
            //2행 3열 -> row_end
            val start = it[1] - 1
            val end = it[3] - 1
            val top = it[0] - 1
            val bot = it[2] - 1

            var topIdx = top
            var left = start
            var cur = square[topIdx][left]
            var isEnd = false
            resultList.clear()

            while (!isEnd) {
                when (topIdx) {
                    top -> {
                        //i+1 = i
                        //한칸씩 뒤로 밀기
                        //마지막 한개가 남으면 [top+1][end]에 넣기
                        while (left <= end) {
                            if (left != end) {
                                val temp = square[topIdx][left + 1]
                                resultList.add(cur)
                                square[topIdx][left + 1] = cur
                                cur = temp
                                left++
                            } else {
                                //끝에 온 경우
                                //맨 끝 값을 아래로 내려주고 원래 값을 temp에 넣음
                                val temp = square[topIdx + 1][end]
                                resultList.add(cur)
                                square[topIdx + 1][end] = cur
                                cur = temp
                                topIdx++
                                break
                            }
                        }
                    }

                    bot -> {
                        //바닥인 경우 [top][left-1] 바닥에서 왼쪽으로 이동
                        while (left >= start) {

                            if (left > start) {
                                //같지 않은 경우
                                val temp = square[topIdx][left - 1]
                                resultList.add(cur)
                                square[topIdx][left - 1] = cur
                                cur = temp
                                left--

                            } else {
                                //같은 경우 -> 바닥 라인 끝 -> 좌측라인으로 이동
                                val temp = square[topIdx - 1][left]
                                resultList.add(cur)
                                square[topIdx - 1][left] = cur
                                cur = temp
                                topIdx--
                                if(topIdx == top && left == start) {
                                    isEnd = true
                                    break
                                }
                                break
                            }

                        }

                    }

                    else -> {

                        if (left == end) {
                            //오른쪽 라인이 아직 돌아가지 않았음
                            //위에서 아래로
                            while (topIdx <= bot) {

                                if (topIdx != bot) {
                                    val temp = square[topIdx + 1][end]
                                    resultList.add(cur)
                                    square[topIdx + 1][end] = cur
                                    cur = temp
                                    topIdx++

                                } else {

                                    //topIdx == bottom
                                    //바닥인 경우 temp = square[topIdx][left-1]
                                    val temp = square[topIdx][left - 1]
                                    resultList.add(cur)
                                    square[topIdx][left - 1] = cur
                                    cur = temp
                                    left--
                                    break
                                }
                            }

                        } else if (left == start) {
                            //오른쪽 라인이 끝났음 -> 바닥까지 완료 후 왼쪽 아래부터 시작지점까지
                            //아래에서 위로
                            //top == topIdx && left == start 일 경우 종료
                            while (topIdx >= top) {
                                if (topIdx > top) {
                                    val temp = square[topIdx - 1][left]
                                    resultList.add(cur)
                                    square[topIdx - 1][left] = cur
                                    cur = temp
                                    topIdx--
                                } else {
                                    isEnd = true
                                    break
                                }
                            }
                        }
                    }
                }
            }
            answer.add(resultList.minOrNull()!!)
        }

        return answer.toIntArray()
    }

    fun runSolution() {
        println(
            solution(
                6,
                6,
                arrayOf(intArrayOf(2, 2, 5, 4), intArrayOf(3,3,6,6), intArrayOf(5,1,6,3))
            ).toList()
        )
    }

}