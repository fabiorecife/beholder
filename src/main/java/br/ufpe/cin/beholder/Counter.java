package br.ufpe.cin.beholder;

public class Counter {

	private int count;


	public Counter(int count) {
		this.count = count;
	}

	public void toClear() {
		count = 0;
	}

	public void toIncrement() {
		count++;
	}

	public int getCount() {
		return count;
	}

}
