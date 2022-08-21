from sys import stdin, stdout
# hw3 Longest Common Subsequence

table = [[0 for i in range(81)] for j in range(81)]

def LCS(s1, s2):
    def LCS1(s1, s2, memo):
        if s1 == '' or s2 == '':
            return 0
        
        if s1 + s2 in memo:
            return memo[s1+s2]
        
        if s1[0] == s2[0]:
            result = 1 + LCS1(s1[1:], s2[1:], memo)
        
        else:
            result = max(LCS1(s1[1:], s2, memo), LCS1(s1, s2[1:], memo))
        memo[s1+s2] = result

        return result
    return LCS1(s1, s2, {})

#DP
def lcs_outer(str1, str2, table, output):

    X = len(str1)
    Y = len(str2)
    memo = set()
    
    for i in range(X+1):
        table[i][0] = 0
    for j in range(Y+1):
        table[0][j] = 0
    for i in range(1, X+1):
        for j in range(Y+1):
            if str1[i-1] == str2[j-1]:
                table[i][j] = table[i-1][j-1] + 1
            else:
                table[i][j] = max(table[i-1][j], table[i][j-1])

    def lcs_inner(s1, s2, s1_len, s2_len, table, output, p):

        if s1_len == 0 or s2_len == 0:
            #count = int(count) + 1
            output.add(p)
            return output

        elif s1[s1_len-1] == s2[s2_len-1]:
            p = s1[s1_len-1] + p
            lcs_inner(s1, s2, s1_len-1, s2_len-1, table, output, p)
            
        elif table[s1_len - 1][s2_len] < table[s1_len][s2_len-1]:
        #useS1 = lcs_inner(s1, s2, s1_len, s2_len - 1, table, p, memo)
            lcs_inner(s1, s2, s1_len, s2_len - 1, table, output, p)
        elif table[s1_len - 1][s2_len] > table[s1_len][s2_len-1]:
            lcs_inner(s1, s2, s1_len-1, s2_len, table, output, p)
        #useS2 = lcs_inner(s1, s2, s1_len - 1, s2_len, table, p, memo)
        # if useS1[0] > useS2[0]:
        #     return useS1
        # return useS2
        else:
            lcs_inner(s1, s2, s1_len, s2_len - 1, table, output, p)
            lcs_inner(s1, s2, s1_len - 1, s2_len, table, output, p)

    return lcs_inner(str1, str2, X, Y, table, output, "")


t = int(stdin.readline())
for i in range(t):
    s1, s2 = stdin.readline(), stdin.readline()
    output = set()
    lcs_outer(s1, s2, table, output)
    for i in output:
        if LCS(s1, s2) == len(i):
            stdout.write(f'{i}\n')