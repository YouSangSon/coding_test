import java.util.*

fun main() {
    
}

data class Node(val id: Int, val level: Int)

class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        val totalMaps =
                friends
                        .associateWith { friend ->
                            friends.filter { it != friend }.associateWith { 0 }.toMutableMap()
                        }
                        .toMutableMap()
        val sumMap = friends.associateWith { 0 }.toMutableMap()

        gifts.forEach { gift ->
            val (giver, receiver) = gift.split(" ")
            sumMap[giver] = sumMap.getValue(giver) + 1
            sumMap[receiver] = sumMap.getValue(receiver) - 1

            val giverMap = totalMaps.getValue(giver)
            giverMap[receiver] = giverMap.getValue(receiver) + 1
        }

        val resultMap = mutableMapOf<String, Int>()
        totalMaps.forEach { (giver, receiverMap) ->
            receiverMap.forEach { (receiver, giverToReceiver) ->
                val receiverToGiver = totalMaps.getValue(receiver).getValue(giver)
                when {
                    giverToReceiver == receiverToGiver &&
                            sumMap.getValue(giver) > sumMap.getValue(receiver) ->
                            resultMap[giver] = resultMap.getOrDefault(giver, 0) + 1
                    giverToReceiver > receiverToGiver ->
                            resultMap[giver] = resultMap.getOrDefault(giver, 0) + 1
                }
            }
        }

        return resultMap.values.maxOrNull() ?: 0
    }
}
