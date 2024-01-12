import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val relationships: Array<IntArray> = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(2, 6),
        intArrayOf(3, 4),
        intArrayOf(4, 5)
    )
    var target = 1
    var limit = 2

    var solution = Solution().solution(relationships, target, limit)

    println(solution)
}

data class Node(val id: Int, val level: Int)

class Solution {
    fun solution(relationships: Array<IntArray>, target: Int, limit: Int): Int {

        var adjacencyList = mutableMapOf<Int, MutableList<Int>>()

        for (relationship in relationships) {
            adjacencyList.getOrPut(relationship[0]) { mutableListOf() }.add(relationship[1])
            adjacencyList.getOrPut(relationship[1]) { mutableListOf() }.add(relationship[0])
        }

        val queue: Queue<Node> = LinkedList()

        var oldFriends = 0
        var newFriends = 0
        var notFriends = 0

        return oldFriends * 5 + newFriends * 10 + notFriends
    }
}
