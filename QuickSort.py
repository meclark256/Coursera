#! usr/bin/env python

"""
This is a program which implements the quicksort algorithm
in three different ways.  The first takes the pivot element 
to always be the first element of the array, while the second 
takes the pivot to always be the last element.  The third
way is to consider the first, last, and middle element of
the array and letting the pivot be the median of the three.
This saves time in arrays that are nearly sorted or nearly 
reverse sorted.  This program also calculates the number of
comparisons quicksort uses while sorting.
"""

firstcomparison = 0
lastcomparison = 0
mediancomparison = 0

#A method for partition around the first element of the array
def partition_first(array, leftend, rightend):
    pivot = array[leftend]
    i = leftend + 1
    for j in range(leftend + 1, rightend):
        if array[j] < pivot:
            temp = array[j]
            array[j] = array[i]
            array[i] = temp
            i += 1

    leftendval = array[leftend]
    array[leftend] = array[i-1]
    array[i-1] = leftendval
    return i - 1 

#A method for partitioning around the last element of the array
def partition_last(array, leftend, rightend):
    
    pivot = array[rightend-1]

    array[rightend-1] = array[leftend]
    array[leftend] = pivot
    
    i = leftend + 1
    for j in range(leftend + 1, rightend):
        if array[j] < pivot:
            temp = array[j]
            array[j] = array[i]
            array[i] = temp
            i += 1

    leftendval = array[leftend]
    array[leftend] = array[i-1]
    array[i-1] = leftendval
    return i - 1 

#A method to calculate the median of three numbers using two comparisons
def median(a, b, c):
    if ( a - b) * (c - a) >= 0:
        return a

    elif (b - a) * (c - b) >= 0:
        return b

    else:
        return c

#A method to partition around the median
def partition_median(array, leftend, rightend):
    left = array[leftend]
    right = array[rightend-1]
    length = rightend - leftend
    if length % 2 == 0:
        middle = array[leftend + length/2 - 1]
    else:
        middle = array[leftend + length/2]
  
    

    pivot = median(left, right, middle)

    pivotindex = array.index(pivot) #only works if all values in array unique

    array[pivotindex] = array[leftend]
    array[leftend] = pivot

    i = leftend + 1
    for j in range(leftend + 1, rightend):
        if array[j] < pivot:
            temp = array[j]
            array[j] = array[i]
            array[i] = temp
            i += 1

    leftendval = array[leftend]
    array[leftend] = array[i-1]
    array[i-1] = leftendval
    return i - 1 

    
#Implement quicksort while partitioning around the first index
def quick_sort1(array, leftindex, rightindex):
    global firstcomparison
    if leftindex < rightindex:
        
        newpivotindex = partition_first(array, leftindex, rightindex)
        
        firstcomparison += (rightindex - leftindex - 1)
        
        quick_sort1(array, leftindex, newpivotindex) 
        
        quick_sort1(array, newpivotindex + 1, rightindex)
        
def quicksort_last(array, leftindex, rightindex):
    global lastcomparison
    if leftindex < rightindex:

        newpivotindex = partition_last(array, leftindex, rightindex)

        lastcomparison += (rightindex - leftindex - 1)

        quicksort_last(array, leftindex, newpivotindex)
        quicksort_last(array, newpivotindex + 1, rightindex)

 
def quicksort_median(array, leftindex, rightindex):
     global mediancomparison
     if leftindex < rightindex:

         newpivotindex = partition_median(array, leftindex, rightindex)

         mediancomparison += (rightindex - leftindex - 1)
         quicksort_median(array, leftindex, newpivotindex)
         

         quicksort_median(array, newpivotindex + 1, rightindex)


test3 = [1, 11, 5, 15, 2, 12, 9, 99, 77, 0]
test4 = []
for i in range(1, 101):
    test4.append(i)

f = open("IntegerArray.txt", "r")
intarray = []
for line in f:
    intarray.append(int(line))
f.close()

#test on an array of length 10000
mediancomparison = 0
quicksort_median(intarray, 0, len(intarray))
print mediancomparison
