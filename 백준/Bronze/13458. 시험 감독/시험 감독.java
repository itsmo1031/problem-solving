import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int locationCnt;
    static int[] locationList;
    static int mainDirector, subDirector;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        locationCnt = Integer.parseInt(br.readLine().trim());

        locationList = new int[locationCnt];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < locationCnt; idx++) {
            locationList[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        mainDirector = Integer.parseInt(st.nextToken());
        subDirector = Integer.parseInt(st.nextToken());

        long answer = getMinDirector();

        System.out.println(answer);
    }

    static long getMinDirector() {
        long result = locationCnt;

        for (int loc : locationList) {
            loc = loc - mainDirector;
            if (loc > 0)
                result += Math.ceil(loc / (double) subDirector);
        }

        return result;
    }

}