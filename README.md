# maxdifference

You are given an array of integers and must compute the maximum difference between any item and any lower indexed smaller item for all the possible pairs, i.e., for a given array a find the maximum value of a[j] - a[i] for all i, j where 0 ≤ i < j < n and a[i] < a[j]. If there are no lower indexed smaller items for all the items, then return -1.
 
For example, given an array [ 1, 2, 6, 4], you would first compare 2 to the elements to its left.  1 is smaller, so calculate the difference 2 - 1 = 1.  6 is bigger than 2 and 1, so calculate the differences 4 and 5.  4 is only bigger than 2 and 1, and the differences are 2 and 3.  The largest difference was 6 - 1 = 5.
