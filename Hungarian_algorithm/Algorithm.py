"""
Hungarian algorithm realization
Optimized realization: O(n^3)
_______________
Functions:


"""

import numpy as np

def max_to_min_transform(matrix):
    #TODO: Maybe i should use global maximum?
    row_max = matrix.max(axis=1)
    matrix=row_max.reshape([len(row_max),1])-matrix
    return matrix
 
def hungarian_algorithm(matrix,max_problem=False):
    """
    O(n^3) realization
    Usually solves min problem. If 

    Parameters:
    ---------------
    matrix - np array with prices of workers doing specific job
    max_problem - boolean variable, which set what task to solve. Whether 
    find min or max.
    
    Returns:
    ---------------
    result_queue - a list of row indexes, that has the min(max) price.
    """

    #Resulting queue creation
    result_queue=[]


    return result_queue



#only for debugging 
if __name__ == "__main__":
    my_matrix=[[7,3,6,9,5],
            [7,5,7,5,6],
            [7,6,8,8,9],
            [3,1,6,5,7],
            [2,4,9,9,5]]

    my_matrix=np.asarray(my_matrix,dtype=np.float32)
    matrix_min=max_to_min_transform(my_matrix)
    print(matrix_min)   