package com.bsu.pt.exam.algo;

import java.util.Arrays;
import java.util.List;

public class AlgorithmExecution {

    public AlgorithmExecution() {
    }

    public String[] getSolution(List<String> studentsID, int[][] priorityMatrix) {
        int n = studentsID.size();

        //Max to min transform
        for (int i = 0; i < n; ++i) {
            int max = 0;
            for (int j = 0; j < n; ++j) {
                if (priorityMatrix[i][j] > max) {
                    max = priorityMatrix[i][j];
                }
            }
            for (int j = 0; j < n; ++j) {
                priorityMatrix[i][j] = max - priorityMatrix[i][j];
            }
        }

        String[] solution = new String[n];
        double[] u = new double[n + 1];
        double[] v = new double[n + 1];
        int[] p = new int[n + 1];
        int[] way = new int[n + 1];

        for (int row = 0; row < n; row++) {
            p[0] = row + 1;
            int j0 = 0;

            double[] minV = new double[n + 1];
            Arrays.fill(minV, Double.POSITIVE_INFINITY);

            boolean[] used = new boolean[n + 1];
            Arrays.fill(used, false);

            int j1 = 0;

            do {
                used[j0] = true;

                int i0 = p[j0];
                double delta = Double.POSITIVE_INFINITY;

                for (int j = 1; j < n + 1; ++j) {
                    if (!used[j]) {
                        double current = priorityMatrix[i0 - 1][j - 1] - u[i0] - v[j];

                        if (current < minV[j]) {
                            minV[j] = current;
                            way[j] = j0;
                        }

                        if (minV[j] < delta) {
                            delta = minV[j];
                            j1 = j;
                        }
                    }
                }

                for (int j = 0; j < n + 1; ++j) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minV[j] -= delta;
                    }
                }

                j0 = j1;
            } while (p[j0] != 0);

            do {
                j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        //Creating results
        for (int i = 1; i < n + 1; ++i) {
            int student_number = p[i] - 1;
            solution[i - 1] = studentsID.get(student_number);
        }

        return solution;
    }
}
