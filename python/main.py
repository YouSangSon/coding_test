def solution(friends, gifts):
    total_maps = {friend: {other: 0 for other in friends if other != friend} for friend in friends}
    sum_map = {friend: 0 for friend in friends}

    for gift in gifts:
        giver, receiver = gift.split()
        sum_map[giver] += 1
        sum_map[receiver] -= 1
        total_maps[giver][receiver] += 1

    result_map = {}
    for giver in total_maps:
        for receiver in total_maps[giver]:
            giver_to_receiver = total_maps[giver][receiver]
            receiver_to_giver = total_maps[receiver].get(giver, 0)

            if giver_to_receiver == receiver_to_giver:
                if sum_map[giver] > sum_map[receiver]:
                    result_map[giver] = result_map.get(giver, 0) + 1
            elif giver_to_receiver > receiver_to_giver:
                result_map[giver] = result_map.get(giver, 0) + 1

    return max(result_map.values(), default=0)


if __name__ == '__main__':
    friends1 = ["muzi", "ryan", "frodo", "neo"]
    gifts1 = ["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"]
    print(solution(friends1, gifts1))  # Output: 2
    
    friends2 = ["joy", "brad", "alessandro", "conan", "david"]
    gifts2 = ["alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"]
    print(solution(friends2, gifts2))  # Output: 4
    
    friends3 = ["a", "b", "c"]
    gifts3 = ["a b", "b a", "c a", "a c", "a c", "c a"]
    print(solution(friends3, gifts3))  # Output: 2