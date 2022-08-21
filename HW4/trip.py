# SPOJ #33 Solution
# https://www.spoj.com/problems/TRIP/
# By Ethan Ruoff, Jason Jin, and Andrea Lin
from sys import stdin, stdout
from typing import DefaultDict

def trip(alice, bob):
    # Memoize to remove duplicate branches
    memo = DefaultDict(dict)
    def trip_helper(a, b):
        # Base case if finished string
        if a < 0 or b < 0:
            # format of return in (<max_length>, <Set>) so only unique values
            return (0, {''})
        # If memoized already just return calculated answer
        elif a in memo and b in memo[a]:
            return memo[a][b]
        
        # If same character at both indexes move back one for both strings
        if alice[a] == bob[b]:
            use_it = trip_helper(a-1, b-1)
            # Add in common character to all answers
            result = (use_it[0] + 1, {x + alice[a] for x in use_it[1]})
        # If index values aren't the same, then iterate back one for each string
        else:
            res1 = trip_helper(a-1, b)
            res2 = trip_helper(a, b-1)
            # If both results are the same length combine them
            if res1[0]==res2[0]:
                result = (res1[0], res1[1] | res2[1])
            # Otherwise use the one with larger max-length
            else:
                result = max(res1, res2, key=lambda x:x[0])
        # Memoize result to avoid repetition
        memo[a][b] = result
        return result
    # Return sorted set of values
    return sorted(trip_helper(len(alice)-1, len(bob)-1)[1])

def main():
    t = int(stdin.readline())
    for i in range(t):
        alice, bob = stdin.readline().rstrip('\n'), stdin.readline().rstrip('\n')
        for r in trip(alice, bob):
            stdout.write(f'{r}\n')
        if i < t-1:
            stdout.write('\n')

if __name__ == '__main__':
    main()