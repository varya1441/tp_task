public class Main {
    public static void main(String[] args) {
        AlgorithmExecution algo = new AlgorithmExecution();

        String[] students = {"Nikita", "Varya", "Tima", "Galya", "Zina"};
        int[][] matrix = {{7, 3, 6, 9, 5},
                {7, 5, 7, 5, 6},
                {7, 6, 8, 8, 9},
                {3, 1, 6, 5, 7},
                {2, 4, 9, 9, 5}};

        String[] solution;
        solution = algo.getSolution(students, matrix);

        System.out.println("Queue for an exam:");
        for (int i = 0; i < solution.length; ++i)
            System.out.println(solution[i]);
    }
}
