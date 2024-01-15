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
        
        Map<String, Map<String, Integer>> totalMaps = new HashMap<>();
        Map<String, Integer> sumMap = new HashMap<>();

        for (String friend : friends) {
            Map<String, Integer> friendMap = new HashMap<>();
            for (String otherFriend : friends) {
                if (!friend.equals(otherFriend)) {
                    friendMap.put(otherFriend, 0);
                }
            }
            totalMaps.put(friend, friendMap);
            sumMap.put(friend, 0);
        }

        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String giver = parts[0];
            String receiver = parts[1];

            sumMap.put(giver, sumMap.getOrDefault(giver, 0) + 1);
            sumMap.put(receiver, sumMap.getOrDefault(receiver, 0) - 1);

            Map<String, Integer> giverMap = totalMaps.get(giver);
            giverMap.put(receiver, giverMap.getOrDefault(receiver, 0) + 1);
        }

        Map<String, Integer> resultMap = new HashMap<>();
        for (String giver : totalMaps.keySet()) {
            for (String receiver : totalMaps.get(giver).keySet()) {
                if (!giver.equals(receiver)) {
                    int giverToReceiver = totalMaps.get(giver).getOrDefault(receiver, 0);
                    int receiverToGiver = totalMaps.get(receiver).getOrDefault(giver, 0);

                    if (giverToReceiver == receiverToGiver) {
                        if (sumMap.get(giver) > sumMap.get(receiver)) {
                            resultMap.put(giver, resultMap.getOrDefault(giver, 0) + 1);
                        }
                    } else if (giverToReceiver > receiverToGiver) {
                        resultMap.put(giver, resultMap.getOrDefault(giver, 0) + 1);
                    }
                }
            }
        }

        int maxGifts = 0;
        for (int giftsReceived : resultMap.values()) {
            if (giftsReceived > maxGifts) {
                maxGifts = giftsReceived;
            }
        }

        return maxGifts;
    }
}