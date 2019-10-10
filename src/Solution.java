import java.util.Arrays;
import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(sc.nextLine());
			int[] distance = new int[N];
			boolean[] isBound = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				distance[i] = Math.abs(sc.nextInt()) + Math.abs(sc.nextInt());
				sc.nextLine();
			}

			int step = 0;
			while (true) {
				for (int i = 0; i < N; i++) {
					if (distance[i] - step > 1) {
						distance[i] -= step;
					} else {
						isBound[i] = true;
						distance[i] = Math.abs(distance[i]-step)%2;
					}
				}
				
				if (checkAllInBound(isBound)) {
					break;
				}
				step++;
			}
			int d = distance[0];
			if (Arrays.stream(distance).allMatch(i -> i == d)) {
				if (d%2 == 1) {
					if (step%2 == 1) {
						step+=2;
					} else {
						step+=1;
					}
				}
				System.out.println("#"+test_case+" "+step);
			} else {
				System.out.println("#"+test_case+" -1");
			}
			
		}
	}

	private static boolean checkAllInBound(boolean[] isBound) {
		for (int i = 0; i < isBound.length; i++) {
			if (isBound[i] == false)
				return false;
		}
		return true;
	}
	
	
}