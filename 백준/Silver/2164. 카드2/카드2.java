import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Queue<Integer> card;

	static int doGame() {
		while (card.size() > 1) {
			card.poll();
			card.offer(card.poll());
		}

		return card.poll();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int cardSize = sc.nextInt();
		card = new ArrayDeque<>();

		for (int num = 1; num <= cardSize; num++) {
			card.offer(num);
		}

		System.out.println(doGame());
	}
}
