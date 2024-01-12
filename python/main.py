def solution(friends, gifts):
    gift_count = {}

    for gift in gifts:
        giver, receiver = gift.split()
        gift_count[giver] = gift_count.get(giver, 0) - 1
        gift_count[receiver] = gift_count.get(receiver, 0) + 1

    max_gift_count = 0

    for friend in friends:
        if friend in gift_count and gift_count[friend] > max_gift_count:
            max_gift_count = gift_count[friend]

    return max_gift_count

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