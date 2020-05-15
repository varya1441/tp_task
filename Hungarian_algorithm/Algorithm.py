"""
Hungarian algorithm realization
Optimized realization: O(n^3)
_______________
Functions:


"""

import numpy as np


def max_to_min_transform(matrix):
    # TODO: Maybe i should use global maximum?
    row_max = matrix.max(axis=1)
    matrix = row_max.reshape([len(row_max), 1])-matrix
    return matrix


def hungarian_algorithm(matrix, max_problem=False):
    """
    O(n^3) realization
    Usually solves min problem. If 

    Parameters:
    ---------------
    matrix - np.array with prices of workers doing specific job
    max_problem - boolean variable, which set what task to solve. Whether 
    find min or max.

    Returns:
    ---------------
    result_queue - a list of row indexes(persons queue), that has the min(max) price.
    price - the value of total cost function
    """
    if max_problem:
        matrix = max_to_min_transform(matrix)

    n = matrix.shape[0]
    m = matrix.shape[1]
    u = np.zeros((n+1,))
    v = np.zeros((m+1,))
    p = np.zeros((m+1,), dtype=np.int32)
    way = np.zeros((m+1,), dtype=np.int32)

    for new_row in range(n):
        print("AAA", new_row)

        # That is an additional element for start
        p[0] = new_row+1  # number of current row (starting from 1) being added
        j0 = 0

        min_v = np.full((m+1,), float("inf"))
        used = np.full((m+1,), False)
        j1 = 0

        # Do while
        while True:
            print(p)
            # DO part
            used[j0] = True

            i0 = p[j0]
            delta = float("inf")

            for j in range(1, m+1):
                if not(used[j]):
                    current = matrix[i0-1, j-1]-u[i0]-v[j]
                    if current < min_v[j]:
                        min_v[j] = current
                        way[j] = j0
                    if min_v[j] < delta:
                        delta = min_v[j]
                        j1 = j
                        print(j1)

            for j in range(0, m+1):
                if used[j]:
                    u[p[j]] += delta
                    v[j] -= delta
                else:
                    min_v[j] -= delta

            j0 = j1
            # while part
            if p[j0] == 0:
                print("BBBBB----STOOOOOOOOP!!!!!!!1")
                break

        while True:
            print("CCC")
            # Do part
            j1 = way[j0]
            p[j0] = p[j1]
            j0 = j1
            # while part
            if j0 == 0:
                break
    # Resulting queue
    result_queue = p[1:]
    price = -v[0]

    return result_queue, price




# Only for debugging
if __name__ == "__main__":
    my_matrix = [[7, 3, 6, 9, 5],
                 [7, 5, 7, 5, 6],
                 [7, 6, 8, 8, 9],
                 [3, 1, 6, 5, 7],
                 [2, 4, 9, 9, 5]]

    # Creating an np.array
    my_matrix = np.asarray(my_matrix, dtype=np.float32)

    result, price = hungarian_algorithm(my_matrix, max_problem=True)
    print(result,price)
    #Expected output
    #[2 3 5 1 4] 3.0
