package main

import (
	"fmt"
	"strings"
)

func main() {
	// Test cases
	friends1 := []string{"muzi", "ryan", "frodo", "neo"}
	gifts1 := []string{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"}
	result1 := solution(friends1, gifts1)
	fmt.Println(result1) // Output: 2

	friends2 := []string{"joy", "brad", "alessandro", "conan", "david"}
	gifts2 := []string{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"}
	result2 := solution(friends2, gifts2)
	fmt.Println(result2) // Output: 4

	friends3 := []string{"a", "b", "c"}
	gifts3 := []string{"a b", "b a", "c a", "a c", "a c", "c a"}
	result3 := solution(friends3, gifts3)
	fmt.Println(result3) // Output: 0
}

func solution(friends []string, gifts []string) int {
	var maxCount int

	totalMap := map[string]int{}
	for _, friend := range friends {
		totalMap[friend] = 0
	}

	giverMaps := make(map[string]map[string]int)
	receiverMaps := make(map[string]map[string]int)

	for _, gift := range gifts {
		splitGift := strings.Split(gift, " ")
		giver := splitGift[0]
		receiver := splitGift[1]

		if _, ok := giverMaps[giver]; !ok {
			giverMaps[giver] = make(map[string]int)
		}

		if _, ok := giverMaps[giver][receiver]; !ok {
			giverMaps[giver][receiver] = 1
		} else {
			giverMaps[giver][receiver]++
		}

		if _, ok := receiverMaps[receiver]; !ok {
			receiverMaps[receiver] = make(map[string]int)
		}

		if _, ok := receiverMaps[receiver][giver]; !ok {
			receiverMaps[receiver][giver] = 1
		} else {
			receiverMaps[receiver][giver]++
		}
	}

	for giver, giverMap := range giverMaps {
		for _, count := range giverMap {
			totalMap[giver] += count
		}
	}

	for receiver, receiverMap := range receiverMaps {
		for _, count := range receiverMap {
			totalMap[receiver] -= count
		}
	}

	resultMap := map[string]int{}
	for _, friend := range friends {
		resultMap[friend] = 0
	}

	for giver, giverMap := range giverMaps {
		for receiver, receiverMap := range receiverMaps {
			if giver != receiver {
				for given, giverCount := range giverMap {
					for received, receiverCount := range receiverMap {
						if giverCount > receiverCount {
							resultMap[giver]++
						}
					}
				}
				if totalMap[giver] > totalMap[receiver] {
					resultMap[giver]++
				}
			}
		}
	}

	fmt.Printf("giverMaps: %v\n", giverMaps)
	fmt.Printf("receiverMaps: %v\n", receiverMaps)

	for _, count := range resultMap {
		if count > maxCount {
			maxCount = count
		}
	}

	return maxCount
}
