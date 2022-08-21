# Given a list, return a list of all subsets
def powerset(lst):
    if not lst:
        return [[]]
    lose_it = powerset(lst[1:])
    use_it = list(map(lambda subset: [lst[0]] + subset, lose_it))
    return lose_it + use_it


print(powerset(['a', 'b', 'c']))


# Determines whether or not its possible to create the target value as a sum using the values in the list
def subset(target, lst):
    if target == 0:
        return True
    elif not lst:
        return False
    return subset(target - lst[0], lst[1:]) or subset(target, lst[1:])


def subset_with_values(target, lst):
    if target == 0:
        return (True, [])
    elif not lst:
        return (False, [])
    first_bool, first_case = subset_with_values(target - lst[0], lst[1:])
    if first_bool:
        return (True, first_case + [lst[0]])
    return subset_with_values(target, lst[1:])

print(subset_with_values(5, [3, 1, 2]))


def longest_consec_substring(s1, s2):
    if '' in (s1, s2):
        return 0
    if s1[0] == s2[0]:
        return 1 + longest_consec_substring(s1[1:], s2[1:])
    return max(longest_consec_substring(s1, s2[1:]), longest_consec_substring(s1[1:], s2))

print(longest_consec_substring('sam', 'apartment'))


def longest_consec_substring_vals(s1, s2):
    if s1 == '' or s2 == '':
        return (0, '')
    if s1[0] == s2[0]:
        res = longest_consec_substring_vals(s1[1:], s2[1:])
        return (res[0]+1, s1[0] + res[1])
    return max(longest_consec_substring_vals(s1, s2[1:]), longest_consec_substring_vals(s1[1:], s2), key=lambda x: x[0])

print(longest_consec_substring_vals('sam', 'apartment'))
