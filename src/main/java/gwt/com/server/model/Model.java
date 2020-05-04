package gwt.com.server.model;

import java.util.Random;

import com.google.gwt.core.shared.GWT;

public class Model {

	private static final int MAX_RANDOM_NUMBER_VALUE = 1000;

	public static int[] setNumbers(int count) {
		boolean thirteenOrLess = false;
		int numbers[] = new int[count];
		GWT.log("count =" + count);
		Random rand = new Random();
		for (int i = 0; i < count; i++) {
			numbers[i] = rand.nextInt(MAX_RANDOM_NUMBER_VALUE) + 1;
			if (!thirteenOrLess && numbers[i] <= 30) {
				thirteenOrLess = true;
			}
		}
		if (!thirteenOrLess) {
			if (count > 1) {
				numbers[rand.nextInt(numbers.length - 1)] = rand.nextInt(29) + 1;
			} else {
				numbers[0] = rand.nextInt(29) + 1;
			}
		}
		return numbers;
	}	

	public static int[] sortArray(int[] numbers, boolean ascOrder) {

		quickSort(numbers, 0, numbers.length - 1,ascOrder);
		return numbers;
	}

//	public int getColumns() {
//		double column = Math.floorDiv(numbers.length - 1, 10) + 1;
//		return (int) Math.ceil(column);
//	}

	
	public static void quickSort(int arr[], int begin, int end, boolean ascOrder) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end ,ascOrder);
			quickSort(arr, begin, partitionIndex - 1,ascOrder);
			quickSort(arr, partitionIndex + 1, end,ascOrder);
		}
	}

	private static int partition(int arr[], int begin, int end, boolean ascOrder) {
		int pivot = arr[end];
		int i = (begin - 1);
		boolean needSwap ;

		for (int j = begin; j < end; j++) {
			needSwap = false;
			
			if ((ascOrder && arr[j] <= pivot)||(!ascOrder && arr[j] >= pivot)) {
				needSwap = true;
			}
			if (needSwap) {
				i++;
				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}

		int swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;

		return i + 1;
	}
}
