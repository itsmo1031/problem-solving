import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int countyCnt = Integer.parseInt(br.readLine().trim());
    int[] counties = new int[countyCnt];

    StringTokenizer st = new StringTokenizer(br.readLine().trim());

    for (int idx = 0; idx < countyCnt; idx++) {
      counties[idx] = Integer.parseInt(st.nextToken());
    }

    int limit = Integer.parseInt(br.readLine().trim());
    int hi = limit;

    Arrays.sort(counties);

    if (limit > counties[countyCnt - 1]) {
      hi = counties[countyCnt - 1];
    }

    System.out.println(lowerBound(counties, hi, limit));
  }

  static int lowerBound(int[] counties, int hi, int limit) {
    int lo = 0;

    while (lo <= hi) {
      int mid = (lo + hi) / 2;

      if (calc(counties, mid, limit)) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }

    return lo - 1;
  }

  static boolean calc(int[] counties, int budget, int limit) {
    int sum = 0;

    for (int i = 0; i < counties.length; i++) {
      if (counties[i] <= budget) {
        sum += counties[i];
      } else {
        sum += budget;
      }

      if (sum > limit) {
        return false;
      }
    }

    return true;
  }
}