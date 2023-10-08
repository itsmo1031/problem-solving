import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] solution;
    static int solutionCnt;

    static class Result {
        int solutionA, solutionB;

        public Result(int solutionA, int solutionB) {
            if (solutionB > solutionA) {
                this.solutionA = solutionA;
                this.solutionB = solutionB;
            } else {
                this.solutionA = solutionB;
                this.solutionB = solutionA;
            }
        }

        void print() {
            System.out.printf("%d %d\n", solutionA, solutionB);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solutionCnt = Integer.parseInt(br.readLine().trim());

        solution = new int[solutionCnt];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < solutionCnt; idx++) {
            solution[idx] = Integer.parseInt(st.nextToken());
        }

        search().print();
    }

    private static Result search() {
        int sum = Integer.MAX_VALUE;
        int resA = 0, resB = 1;

        binarySearch: for (int idx = 0; idx < solutionCnt; idx++) {
            int now = solution[idx];
            int start = idx + 1;
            int end = solutionCnt - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                int tempSum = Math.abs(now + solution[mid]);

                if (tempSum < sum) {
                    sum = tempSum;
                    resA = idx;
                    resB = mid;
                    if (tempSum == 0)
                        break binarySearch;
                }
                if (solution[mid] < -now) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return new Result(solution[resA], solution[resB]);
    }
}