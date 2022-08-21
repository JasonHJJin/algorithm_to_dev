from curses.ascii import isdigit
from sys import argv, stdin, stdout


def mathexpr(n):

    output = "1 "
    flag = True
    count = 1.0

    while flag:

        count *= 2

        if count <= n:
            if count == n:
                output += "x 2"
                flag = False
            else:
                output += "x 2 "
                continue

        elif count > n:
            output += "x 2 / 3 "
            count = round((count) / 3)

    return output


if len(argv) > 2 or argv[0] == argv[-1]:
    stdout.write("Usage: ./mathexpr <non-negative integer>")

if argv[1][0] == '-' or not isdigit(argv[1][0]):
    stdout.write(f"Error: Argument '{argv[1]}' is not a non-negative integer.")

print(mathexpr(int(argv[1])))
