"""
Exam queue creation
_______________
This programme is for hungarian algorithm execution. 
Also its role is to format given data into numpy matrix
and send results(queue list of users) to a desirable destination.
________________
Imports:
DataFormatting.py   <- for matrix creation from a given data
Algorithm.py        <- for queue creation from a given matrix
"""

import Algorithm
import DataFormatting
import numpy as np
import json
import requests 


if __name__ == "__main__":
    """By receiving json from request data in formatting and passed to algorithm.
    Then algorithm will be executed and results returned"""
    example= [[15,16,17,18,19],
            [1,1,3,1,16],
            [2,8,8,6,17],
            [2,2,2,8,18],
            [4,6,7,8,19]]
    example= np.asarray(example, dtype=np.float32)

    result,price=Algorithm.hungarian_algorithm(example,max_problem=True)

    print("For matrix:\n{}\nQueue is the following {}\nTotal cost: {}\n".format(example,result,price))

