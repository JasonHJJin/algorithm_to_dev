# HW 5: Math Expressions
# By Ethan Ruoff, Jason Jin, and Andrea Lin
from sys import argv, stdout

inp = argv

# Check to make sure only one number
if len(inp) != 2:
    stdout.write('Usage: python ./mathexpr <non-negative integer>\n')
    exit()

# Make sure it is a number
inp = argv[-1]
if not inp.isdigit() or inp=='0':
    stdout.write(f'Error: Argument \'{inp}\' is not a non-negative integer.\n')
    exit()

# Class for node in binary tree
class Node():
    def __init__(self, parent, toString, left=None, right=None, val=None):
        # Have a parent to allow backtracking to construct the string
        self.parent = parent
        self.left = left
        self.right = right
        self.val = val
        # Used to reconstruct the correct expression for the solution
        self.toString = toString

def solve(target):
    # If target is 1 we've already reached it!
    if target==1:
        return '1'

    q = []
    # Visited values to avoid repetition
    visited = [0]

    # Create the head node and add it to visited
    head = Node(None, '', val=1)
    curr = head
    visited.append(curr.val)

    #Perform BFS left -> right until we find the target
    while True:
        # If we reach the target break out of the loop
        if curr.val == target:
            break
        # Add left node to the tree if not visited
        l = curr.val//3
        if l not in visited:
            curr.left = Node(curr, ' / 3', val=l)
            visited.append(l)
            q.append(curr.left)
        # Add right node to the tree if not visited
        r = curr.val * 2
        if r not in visited:
            curr.right = Node(curr, ' x 2', val=r)
            visited.append(r)
            q.append(curr.right)
        curr = q.pop(0)
    
    # Backtrack up the tree to construct the answer
    ans = ''
    while curr.parent:
        #print(curr.val)
        ans = curr.toString + ans
        curr = curr.parent
    return '1' + ans

stdout.write(solve(int(inp))+'\n')