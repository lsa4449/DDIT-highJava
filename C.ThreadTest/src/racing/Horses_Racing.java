package racing;

import java.util.Arrays;

public class Horses_Racing {
	public static void main(String[] args) {

		Horse[] horse = new Horse[10];
		for (int i = 0; i < 10; i++) {
			horse[i] = new Horse((i + 1) + "번말");
		}

		DisplayHorse dh = new DisplayHorse(horse);
		dh.start();
		for (int i = 0; i < horse.length; i++) {
			horse[i].start();
		}

		try {
			for (int i = 0; i < horse.length; i++) {
				horse[i].join();
			}
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}

		System.out.println();
		dh.interrupt();
	}

}

class DisplayHorse extends Thread {
	private Horse[] horse;

	public DisplayHorse(Horse[] horse) {
		this.horse = horse;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println("경기 종료!!! 경기 결과!!!");
				Arrays.sort(horse);
				for (int i = 0; i < horse.length; i++) {
					System.out.println((i + 1) + "등 : " + horse[i].getHorseName());
				}
				return;
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			for (int i = 0; i < horse.length; i++) {
				System.out.print(horse[i].getHorseName() + "\t: ");

				if (horse[i].getLocation() == 50) {
					for (int j = 0; j < 50; j++) {
						System.out.print("-");
					}
					System.out.println(" " + horse[i].getHorseName() + " " + horse[i].getRank() + "등");

				} else {
					for (int j = 0; j < 50; j++) {
						if (j == horse[i].getLocation()) {
							System.out.print(">");
						} else {
							System.out.print("-");
						}
					}
					horseRank();
					System.out.println(" : " + horse[i].getTimeRank() + "등");
				}
			}
		}
	}

	public void horseRank() {
		Horse[] horseTR = new Horse[horse.length];

		for (int i = 0; i < horse.length; i++) {
			horseTR[i] = horse[i];
		}
		
		for (int i = 0; i < horseTR.length; i++) {
			for (int j = 0; j < horseTR.length; j++) {
				if (horseTR[i].getLocation() > horseTR[j].getLocation()) {
					Horse temp;
					temp = horseTR[i];
					horseTR[i] = horseTR[j];
					horseTR[j] = temp;
				}
			}
		}
		for (int i = 0; i < horse.length; i++) {
			for (int j = 0; j < horseTR.length; j++) {
				if (horse[i].getHorseName() == horseTR[j].getHorseName()) {
					horse[i].setTimeRank(j + 1);
				}
			}
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {

	private String horseName;
	private int location = 0;
	private static int ranking = 1;
	private int rank;
	private int timeRank;

	Horse(String horseName) {
		this.horseName = horseName;
	}

	// 현재 위치
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int) (Math.random() * 800 + 300));
				this.location++;
			} catch (InterruptedException e) {
			}
		}
		this.rank = ranking;
		ranking++;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getTimeRank() {
		return timeRank;
	}

	public void setTimeRank(int timeRank) {
		this.timeRank = timeRank;
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.rank, h.rank);
	}

}
