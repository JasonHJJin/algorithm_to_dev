# # import time
# from sys import stdin, stdout
# # stdout.write('TEst\n.\')
# # start_time = time.time()

# ######################################################################
# from sys import stdin, stdout

# f = open('input.txt', 'r')

# a=0
# for i in f:
#     a = a+int(i)

# b = int(str(a)[:10])

# # look up cis import std out 
# stdout.write('Full sum: ' + str(a) + '\n')
# stdout.write('First 10 digits: '+str(b) + '\n')


# stdout.flush()
# ######################################################################

# # print("--- %.9f seconds ---" % (time.time() - start_time))

##################################################################
# f = open('input.txt', 'r')
# newlist = []

# newlist = [x for x in f]

# print(newlist)

# from sys import stdin, stdout
# _, k = map(int, stdin.readline().split())
# count = sum(1 for x in map(int, stdin.buffer) if not x % k)
# stdout.write(str(count) + '\n')


# f = []
# sum_a = []


from sys import stdin, stdout,  argv
from functools import reduce

f = open(str(argv[-1]), 'r')


if not f.read(1):
    stdout.write('Full sum: ' + str(0) + '\n')
    stdout.write('First 10 digits: '+str(0) + '\n')
    exit()

f.seek(0)


sum_a = reduce(lambda x, y:int(x)+int(y), f)

stdout.write('Full sum: ' + str(sum_a) + '\n')
stdout.write('First 10 digits: '+str(sum_a)[:10] + '\n')

## .02 s is the time to beat 