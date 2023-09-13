import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int questionCnt, deadline;
    static Question[] question;
    static int[] schedule;

    static class Question {
        int day, fare;

        public Question(int day, int fare) {
            this.day = day;
            this.fare = fare;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        questionCnt = Integer.parseInt(st.nextToken());
        deadline = Integer.parseInt(st.nextToken());

        question = new Question[questionCnt];
        schedule = new int[deadline + 1];
        int answer = 0;

        for (int idx = 0; idx < questionCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int day = Integer.parseInt(st.nextToken());
            int fare = Integer.parseInt(st.nextToken());
            answer += fare;
            question[idx] = new Question(day, fare);
        }

        for (Question q : question) {
            for (int idx = deadline; idx >= q.day; idx--) {
                schedule[idx] = Math.max(schedule[idx - q.day] + q.fare, schedule[idx]);
            }
        }

        answer -= schedule[deadline];
        System.out.println(answer);
    }
}