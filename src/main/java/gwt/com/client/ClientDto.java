package gwt.com.client;

import java.io.Serializable;

public class ClientDto implements Serializable{
	
	private boolean ascSort = false;
	private int [] numbers;
	private int size;
	
	public boolean isAscSort() {
		return ascSort;
	}
	public void setAscSort(boolean ascSort) {
		this.ascSort = ascSort;
	}
	public int[] getNumbers() {
		return numbers;
	}
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
