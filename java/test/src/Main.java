import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] friends1 = { "muzi", "ryan", "frodo", "neo" };
        String[] gifts1 = { "muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi",
                "frodo ryan", "neo muzi" };
        System.out.println(solution.solution(friends1, gifts1)); // Output: 2

        String[] friends2 = { "joy", "brad", "alessandro", "conan", "david" };
        String[] gifts2 = { "alessandro brad", "alessandro joy", "alessandro conan", "david alessandro",
                "alessandro david" };
        System.out.println(solution.solution(friends2, gifts2)); // Output: 4

        String[] friends3 = { "a", "b", "c" };
        String[] gifts3 = { "a b", "b a", "c a", "a c", "a c", "c a" };
        System.out.println(solution.solution(friends3, gifts3)); // Output: 2
    }
}

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int maxCount = 0;

        Map<String, Integer> totalMap = new HashMap<>();
        for (String friend : friends) {
            totalMap.put(friend, 0);
        }

        Map<String, Map<String, Integer>> giverMaps = new HashMap<>();
        Map<String, Map<String, Integer>> receiverMaps = new HashMap<>();

        for (String gift : gifts) {
            String[] splitGift = gift.split(" ");
            String giver = splitGift[0];
            String receiver = splitGift[1];

            giverMaps.putIfAbsent(giver, new HashMap<>());
            giverMaps.get(giver).put(receiver, giverMaps.get(giver).getOrDefault(receiver, 0) + 1);

            receiverMaps.putIfAbsent(receiver, new HashMap<>());
            receiverMaps.get(receiver).put(giver, receiverMaps.get(receiver).getOrDefault(giver, 0) + 1);
        }

        for (Map.Entry<String, Map<String, Integer>> entry : giverMaps.entrySet()) {
            String giver = entry.getKey();
            Map<String, Integer> giverMap = entry.getValue();
            for (int count : giverMap.values()) {
                totalMap.put(giver, totalMap.get(giver) + count);
            }
        }

        for (Map.Entry<String, Map<String, Integer>> entry : receiverMaps.entrySet()) {
            String receiver = entry.getKey();
            Map<String, Integer> receiverMap = entry.getValue();
            for (int count : receiverMap.values()) {
                totalMap.put(receiver, totalMap.get(receiver) - count);
            }
        }

        Map<String, Integer> resultMap = new HashMap<>();
        for (String friend : friends) {
            resultMap.put(friend, 0);
        }

        for (Map.Entry<String, Map<String, Integer>> giverEntry : giverMaps.entrySet()) {
            String giver = giverEntry.getKey();
            for (Map.Entry<String, Map<String, Integer>> receiverEntry : receiverMaps.entrySet()) {
                String receiver = receiverEntry.getKey();
                if (!giver.equals(receiver) && totalMap.get(giver) > totalMap.get(receiver)) {
                    resultMap.put(giver, resultMap.get(giver) + 1);
                }
            }
        }

        for (int count : resultMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}