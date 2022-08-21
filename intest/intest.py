"""
Name          : intest.py
Author        : Brian S. Borowski
Version       : 1.0
Date          : July 5, 2022
Last modified : July 5, 2022
Description   : Solution to SPOJ -> Enormous Input Test (INTEST)
              : Various solutions are shown in commented blocks.
              : The fastest solution is uncommented.
"""

##n, k = input().split()
##n = int(n)
##k = int(k)
##count = 0
##for i in range(n):
##    val_str = input()
##    val = 0
##    for j in range(len(val_str)):
##        val = val * 10 + ord(val_str[j]) - 48
##    if val % k == 0:
##        count += 1
##print(count)

##from sys import stdin, stdout
##n, k = stdin.readline().split()
##n = int(n)
##k = int(k)
##count = 0
##for i in range(n):
##    val_str = stdin.readline()
##    val = 0
##    for j in range(len(val_str) - 1):
##        val = val * 10 + ord(val_str[j]) - 48
##    if val % k == 0:
##        count += 1
##stdout.write(str(count) + '\n')

##from sys import stdin, stdout
##n, k = stdin.readline().split()
##n = int(n)
##k = int(k)
##count = 0
##for line in stdin:
##    val = 0
##    for j in range(len(line) - 1):
##        val = val * 10 + ord(line[j]) - 48
##    if val % k == 0:
##        count += 1
##stdout.write(str(count) + '\n')

##from sys import stdin, stdout
##n, k = map(int, stdin.readline().split())
##count = 0
##while n > 0:
##    val = int(stdin.readline())
##    if val % k == 0:
##        count += 1
##    n -= 1
##stdout.write(str(count) + '\n')

##from sys import stdin, stdout
##vals = []
##n, k = map(int, stdin.readline().split())
##vals = [int(stdin.readline()) for _ in range(n)]
##count = len([1 for x in vals if x % k == 0])
##stdout.write(str(count) + '\n')

from sys import stdin, stdout
_, k = map(int, stdin.readline().split())
count = sum(1 for x in map(int, stdin.buffer) if not x % k)
stdout.write(str(count) + '\n')
