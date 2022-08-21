t = int(input())
    
def solve(nums):
    val = 1
    nums.sort()
    for i in range(len(nums)):
        if nums[i] <= val:
            val += nums[i]
        else:
            return val
    return val
        
for i in range(t):
    l = input()
    print(solve([int(x) for x in input().split(' ')]))
