
public class DiskScheduler {

	String[] str;

	String[] cylinder;

	int x[];

	int nearest = -1;




	public int findClosest(int NeedNum) {

		int difference = 1000;

		int d;

		int i;

		for (int k = 0; k < x.length; k++) {

			if (nearest == x[k])

				x[k] = 5000;

		}




		for (i = 1; i < x.length; i++) {

			d = Math.abs(NeedNum - x[i]);

			if (d < difference) {

				difference = d;

				nearest = x[i];

			}

		}

		return nearest;

	}




	public int[] getSftf(int[] x, int st) {

		this.x = x;

		int[] sstf = new int[x.length + 1];

		sstf[0] = st;




		int NeedNum = st;

		System.out.println("10 requests: ");

		for (int count = 0; count < x.length - 1; count++) {

			sstf[count + 1] = findClosest(NeedNum);




			System.out.println(sstf[count + 1]);

			NeedNum = nearest;

		}

		return sstf;

	}

}
