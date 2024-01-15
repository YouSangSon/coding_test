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

	totalMaps := make(map[string]map[string]int)
	sumMap := make(map[string]int)

	for _, friend := range friends {
		totalMaps[friend] = make(map[string]int)
		sumMap[friend] = 0
	}

	for friend, totalMap := range totalMaps {
		for _, friend2 := range friends {
			if friend != friend2 {
				totalMap[friend2] = 0
			}
		}
	}

	for _, gift := range gifts {
		giver := strings.Split(gift, " ")[0]
		receiver := strings.Split(gift, " ")[1]

		sumMap[giver]++
		sumMap[receiver]--

		totalMaps[giver][receiver]++
	}

	resultMap := make(map[string]int)

	for giver, totalMap := range totalMaps {
		for receiver, _ := range totalMap {
			if giver != receiver {
				_, ok := totalMaps[giver][receiver]
				_, ok2 := totalMaps[receiver][giver]

				if ok && ok2 {
					if totalMaps[giver][receiver] == totalMaps[receiver][giver] {
						if sumMap[giver] > sumMap[receiver] {
							resultMap[giver] += 1
						}
					}

					if totalMaps[giver][receiver] > totalMaps[receiver][giver] {
						resultMap[giver] += 1
					}
				} else {
					if sumMap[giver] > sumMap[receiver] {
						resultMap[giver] += 1
					}
				}
			}
		}
	}

	maxGifts := 0
	for _, gifts := range resultMap {
		if gifts > maxGifts {
			maxGifts = gifts
		}
	}

	return maxGifts
}
