#! usr/bin/env python

"""
This is a program which implements the mergesort algorithm
and uses it to count the number of inversions in an array of
length N which contains unique integers.
By inversions, I mean pairs (i, j) in the array such that i > j
"""


# Method to merge two arrays, and count split inversions
def mergeandcount(list1, list2, inv):
    mergedlist = []
    inversions = inv
    i = 0
    j = 0
    length1 = len(list1)
    length2 = len(list2)
    newlength = length1 + length2

    for k in range(0, newlength):
        if i == length1:
            for l in range(j, length2):
                mergedlist.append(list2[l])
            break
        if j == length2:
           for l in range(i, length1):
               mergedlist.append(list1[l])
           break
        if list1[i] < list2[j]:
            mergedlist.append(list1[i])
            i += 1
        else:
            mergedlist.append(list2[j])
            inversions = inversions + (length1 - i)
            j += 1
    return mergedlist, inversions

#Method to sort an array
def sort(alist, count):
    newcount = count
    listlength = len(alist)
    if listlength == 0 or listlength == 1:
        return alist, newcount
    firsthalf = []
    secondhalf = []
    for elt in range(listlength/2):
        firsthalf.append(alist[elt])

    for elt in range(listlength/2, listlength):
        secondhalf.append(alist[elt])
    sortedfirst, newcount = sort(firsthalf, newcount)
    sortedsecond, newcount = sort(secondhalf, newcount)
    newlist, newcount = mergeandcount(sortedfirst, sortedsecond, newcount)
    return newlist, newcount

f = open("IntegerArray.txt", "r")
intarray = []
for line in f:
    intarray.append(int(line))
f.close()

a = [1, 3, 5, 7, 2, 4, 6]

print sort(a, 0)

print "testcase is " 
print sort(intarray, 0)[1]
