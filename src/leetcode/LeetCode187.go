package main

//brute force
func findRepeatedDnaSequences(s string) []string {
	set := map[string]byte{}
	for i := 0; i <= len(s)-10; i++ {
		for j := i + 1; j <= len(s)-10; j++ {
			tmp := s[i : i+10]
			if tmp == s[j:j+10] {
				set[tmp] = 0
			}
		}
	}
	var res []string
	for key := range set {
		res = append(res, key)
	}
	return res
}

//set
func findRepeatedDnaSequences1(s string) []string {
	length := len(s)
	set := map[string]byte{}
	resSet := map[string]byte{}

	for i := 0; i <= length-10; i++ {
		str := s[i : i+10]
		if _, ok := set[str]; ok {
			resSet[str] = 0
		} else {
			set[str] = 0
		}
	}
	var res []string
	for key := range resSet {
		res = append(res, key)
	}
	return res
}

//优化空间
func findRepeatedDnaSequences2(s string) []string {
	length := len(s)
	if length < 10 {
		return []string{}
	}
	set := map[int]byte{}
	resSet := map[string]byte{}
	var key = 0
	for i := 0; i < 10; i++ {
		key <<= 3
		key |= int(s[i] & 7)
	}
	set[key] = 0
	for i := 10; i < length; i++ {
		key <<= 3
		key |= int(s[i] & 7)
		key &= 0x3fffffff
		if _, ok := set[key]; ok {
			resSet[s[i-9:i+1]] = 0
		} else {
			set[key] = 0
		}
	}
	var res []string
	for key := range resSet {
		res = append(res, key)
	}
	return res
}

func main() {

}
