package com.bsu.pt.exam.algo;

import com.bsu.pt.exam.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmExecution {

    public AlgorithmExecution() {
    }
//TO Check if null
    public List<String> getSolution(List<Student> students) {
        int n = students.size();
        List<String> studentsID = new ArrayList<>();
        List<List<Integer>> priorityMatrix = new ArrayList<>();
        int index = 0;
        for (Student s : students
        ) {

            studentsID.add(s.getLogin());

            priorityMatrix.add(new ArrayList<>(s.getPriority().getPriorities().values()));
            index++;


        }

        //Max to min transform
        for (int i = 0; i < n; ++i) {
            int max = 0;
            for (int j = 0; j < n; ++j) {
                if (priorityMatrix.get(i).get(j) > max) {
                    max = priorityMatrix.get(i).get(j);
                }
            }
            for (int j = 0; j < n; ++j) {
                priorityMatrix.get(i).set(j, max - priorityMatrix.get(i).get(j));
            }
        }

        List<String> solution = new ArrayList<>(n);
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
                        double current = priorityMatrix.get(i0 - 1).get(j - 1) - u[i0] - v[j];

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
            solution.add(i-1,studentsID.get(student_number));
        }

        return solution;
    }
}
