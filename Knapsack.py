# https://www.hackerrank.com/contests/srin-aadc03/challenges/classic-01-knapsack/problem

t = int(input())

def solve(art, s):
    table = [[0 for _ in range(s+1)] for _ in range((len(art)+1))]
    art.insert(0,(0,0))
    for item in range(1, len(table)):
        w = art[item][0]
        v = art[item][1]
        for cap in range(1, len(table[0])):
            if w > cap:
                table[item][cap] = table[item-1][cap]
            else:
                use_it = table[item-1][cap-w] + v
                lose_it = table[item-1][cap]
                table[item][cap] = max(use_it, lose_it)
    return table[-1][-1]
for i in range(t):
    s, n = input().split(' ')
    artifacts = []
    for j in range(int(n)):
        w, v = input().split(' ')
        artifacts.append((int(w), int(v)))
    print(solve(artifacts, int(s)))