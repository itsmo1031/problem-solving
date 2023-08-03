import java.util.Scanner;

public class Main {
	static int N;

	static boolean isPrime(int number) {
		for (int index = 2; index < (int) Math.sqrt(number) + 1; index++) {
			if (number % index == 0)
				return false;
		}
		return true;
	}

	static void getMagicNum(int cnt, int current) {
		if (cnt == N) {
			if (isPrime(current))
				System.out.println(current);
			return;
		}
		if (isPrime(current)) {
			for (int next = 0; next < 10; next++) {
				getMagicNum(cnt + 1, concat(current, next));
			}
		}
	}

	static int concat(int num1, int num2) {
		return Integer.parseInt(String.valueOf(num1).concat(String.valueOf(num2)));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int prime : new int[] { 2, 3, 5, 7 }) {
			getMagicNum(1, prime);
		}
	}
}
