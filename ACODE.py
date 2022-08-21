def acode(code):
    if code == '':
        return 1
    i = 2
    code = '9' + code
    sol = [0] * len(code)
    sol[0] = 1
    sol[1] = 1
    while i < len(code):
        if code[i] > '6' or code[i-1] > '2':
            sol[i] = sol[i-1]
        else:
            if code[i-2] <= '2':
                sol[i] = sol[i-1] + sol[i-2]
            else:
                sol[i] = sol[i-1] * 2
        i += 1
    print(sol[-1])

def main():
    t = str(input())
    while t != '0':
        acode(t)
        t = str(input())
main()