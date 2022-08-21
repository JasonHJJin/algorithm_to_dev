from sys import stdout, argv


def method1():
    with open(str(argv[-1]), 'r') as file:
        total = sum(map(int, file.readlines()))
    stdout.write(f"Full sum: {total}\nFirst 10 digits: {str(total)[0:10]}")

def method2(): # Correct
    with open(str(argv[-1]), 'r') as file:
        # Read the file as a list, remove newlines, and pad everything with 0's
        lines = [x.replace('\n', '').zfill(50) for x in file.readlines()]

        # If the file is empty just print 0
        if not lines:
            stdout.write(f"Full sum: 0\nFirst 10 digits: 0")
            return

        total = ''
        carry = ''
        for i in range(len(lines[0])):
            digit_total = sum([int(x[-(i+1)]) for x in lines])
            if carry:
                total_add = str(digit_total + int(carry)) # Add the carry to the total
                carry = total_add[:-1] # Make the new carry everything but the last digit
                total_add = total_add[-1] # Make the last digit added
            else:
                total_add = str(digit_total)[-1] # Make the last digit added
                carry = str(digit_total)[:-1] # Make the carry everything but the last digit
            total = total_add + total # Add the newest digit to the front of the total
        total = carry + total # Add the remaining carry numbers to the front

    total = total.lstrip('0') # Remove any leading 0's
    # If it is blank, make it a 0
    if not total: 
        stdout.write(f"Full sum: 0\nFirst 10 digits: 0")
    else:
        stdout.write(f"Full sum: {total}\nFirst 10 digits: {str(total)[0:10]}")

def method3(): # Correct
    with open(str(argv[-1]), 'r') as file:
        # Read the file as a list, remove newlines, and pad everything with 0's
        lines = [x.replace('\n', '') for x in file.readlines()]
        
        # If the file is empty just print 0
        if not lines:
            stdout.write(f"Full sum: 0\nFirst 10 digits: 0")
            return
        lengths = [len(x) for x in lines]

        total = ''
        carry = ''

        longest = max(lengths)
        i = 0
        while longest:
            i-=1
            digit_total = 0
            for j in range(len(lengths)):
                if lengths[j] > 0:
                    digit_total += int(lines[j][i])
                    lengths[j] -=1
            longest -= 1
            if carry:
                total_add = str(digit_total + int(carry)) # Add the carry to the total
                carry = total_add[:-1] # Make the new carry everything but the last digit
                total_add = total_add[-1] # Make the last digit added
            else:
                total_add = str(digit_total)[-1] # Make the last digit added
                carry = str(digit_total)[:-1] # Make the carry everything but the last digit
            total = total_add + total # Add the newest digit to the front of the total
        total = carry + total # Add the remaining carry numbers to the front

    total = total.lstrip('0') # Remove any leading 0's
    # If it is blank, make it a 0
    if not total: 
        stdout.write(f"Full sum: 0\nFirst 10 digits: 0")
    else:
        stdout.write(f"Full sum: {total}\nFirst 10 digits: {str(total)[0:10]}")

def generate_random_numbers():
    import random
    with open('input10.txt', 'w') as file:
        for i in range(200):
            file.write(str(random.randrange(0, 9999999)) + '\n')
        file.close()

def test_performance():
    import timeit
    time1 = timeit.timeit("method1()", setup="from __main__ import method1", number=1000)
    time2 = timeit.timeit("method2()", setup="from __main__ import method2", number=1000)
    time3 = timeit.timeit("method3()", setup="from __main__ import method3", number=1000)
    print('\n' + str(time1))
    print(time2)
    print(time3)
test_performance()
#method2()
#generate_random_numbers()