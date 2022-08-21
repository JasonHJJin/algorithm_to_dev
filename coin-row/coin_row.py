

# useit-loseit method
def coin_row(coins):
    def coin_row_helper(index):
        if index < 0:
            return 0
        return max(coins[index] + coin_row_helper(index-2), coin_row_helper(index-1))
    return coin_row_helper(len(coins)-1)

def coin_row_with_values(coins):
    memo = {}
    def coin_row_helper(index):
        if index in memo:
            return memo[index]
        if index < 0:
            return (0, [])
        use_it = coin_row_helper(index-2)
        lose_it = coin_row_helper(index-1)
        memo[index-2] = use_it
        memo[index-1] = lose_it 
        new_sum = use_it[0] + coins[index]
        return (new_sum, [coins[index]] + use_it[1]) if new_sum > lose_it[0] else lose_it
    return coin_row_helper(len(coins)-1)

with open('large_input.txt', 'r') as file:
    lines = [int(line) for line in file]
    #print(coin_row(lines))
    print(coin_row_with_values(lines))

# right or down
# 