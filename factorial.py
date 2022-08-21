'''
For example, they defined the function Z. For any positive integer N, Z(N) is the number of zeros at the end of the decimal form of number N!. 
They noticed that this function never decreases. If we have two numbers N1<N2, then Z(N1) <= Z(N2). It is because we can never "lose" any trailing zero by multiplying by any positive number. We can only get new and new zeros. The function Z is very interesting, so we need a computer program that can determine its value efficiently.
'''

from math import factorial
from sys import stdin, stdout

def get_trailing_0s(n):
    zeros = 0
    i = 5
    while i < n:
        zeros += n//i
        i *= 5
    stdout.write(str(zeros) + '\n')

def sherlock_watson(n, m):
    facts = [1] * (n+m)
    for i in range(1, n+m):
        facts[i] = facts[i-1] * i
    
    combinations = factorial(l)//m#/(factorial(n)*factorial(m))
    stdout.write(str(combinations % (10e9+7)) + '\n')


# 2 3
t = int(stdin.readline())
for i in range(t):
    n = int(stdin.readline())
    get_trailing_0s(n)
    #n, m = stdin.readline().split(' ')
    #sherlock_watson(int(n), int(m))


