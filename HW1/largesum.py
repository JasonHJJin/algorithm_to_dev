# Coded by Ethan Ruoff, Andrea Lin, and Jason Jin

# Comment lines 7 and 8 instead of 3 and 4 if using our custom shell script
#from sys import stdout, argv
#with open(str(argv[-1]), 'r') as file:

# Import Library
from sys import stdout

# Open file for reading
with open(str('input.txt'), 'r') as file:

    # Read the file as a list, remove newlines, and pad everything with 0's
    lines = [x.replace('\n', '').zfill(50) for x in file.readlines()]

    # If the file is empty just print 0
    if not lines:
        stdout.write(f"Full sum: 0\nFirst 10 digits: 0")
        exit()

    total = ''
    carry = ''
    for i in range(len(lines[0])):
        digit_total = sum([int(x[-(i+1)]) for x in lines]) # Add single digit working backwards
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
# If it is blank, print sum as 0
if not total: 
    stdout.write(f"Full sum: 0\nFirst 10 digits: 0")
else:
    stdout.write(f"Full sum: {total}\nFirst 10 digits: {str(total)[0:10]}")