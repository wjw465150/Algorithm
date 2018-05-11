package wjw.algorithm.string;

import java.util.Stack;

//快速排序非递归算法的java实现
public class NonRecrutQuickSort {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NonRecrutQuickSort t = new NonRecrutQuickSort();
		t.test();
	}

	public void test() {
		{
			System.out.println("非递归快排，两个栈");
			int a[] = { 10, 1, 4, 7, 8, 6, 13, 41, 4, 34, 5, 9, 2 };
			printArray(a);
			nonRecrutTwoStackSort(a);
			printArray(a);
		}

		System.out.println("");

		{
			System.out.println("非递归快排，用一个栈存储分界点位置");
			int a[] = { 10, 1, 4, 7, 8, 6, 13, 41, 4, 34, 5, 9, 2 };
			printArray(a);
			nonRecrutOneStackQuickSort(a);
			printArray(a);
		}
	}

	public void nonRecrutTwoStackSort(int[] a) {//非递归快排，两个栈    
		if (a == null || a.length < 2) {
			return;
		}
		Stack<Integer> startStack = new Stack<Integer>();//保存当前划分的最高位    
		Stack<Integer> endStack = new Stack<Integer>();//保存当前划分的最低位    
		int start = 0;
		int end = a.length - 1;

		int pivotPos;

		startStack.push(start);
		endStack.push(end);

		while (!startStack.isEmpty()) {
			start = startStack.pop();
			end = endStack.pop();
			pivotPos = partition(a, start, end);
			if (start < pivotPos - 1) {
				startStack.push(start);
				endStack.push(pivotPos - 1);
			}
			if (end > pivotPos + 1) {
				startStack.push(pivotPos + 1);
				endStack.push(end);
			}
		}
	}

	public void nonRecrutOneStackQuickSort(int a[]) {
		if (a == null || a.length < 2) {
			return;
		}
		Stack<Integer> index = new Stack<Integer>(); //用一个栈存储分界点位置的  
		int start = 0;
		int end = a.length - 1;

		int pivotPos;

		index.push(start);
		index.push(end);

		while (!index.isEmpty()) {
			end = index.pop();
			start = index.pop();

			pivotPos = partition(a, start, end);
			if (start < pivotPos - 1) {
				index.push(start);
				index.push(pivotPos - 1);
			}
			if (end > pivotPos + 1) {
				index.push(pivotPos + 1);
				index.push(end);
			}
		}
	}

	public int partition(int[] a, int start, int end) {//分块方法，在数组a中，对下标从start到end的数列进行划分    
		int pivot = a[start]; //把比pivot(初始的pivot=a[start]小的数移动到pivot的左边    
		while (start < end) { //把比pivot大的数移动到pivot的右边    
			while (start < end && a[end] >= pivot)
				end--;
			a[start] = a[end];
			while (start < end && a[start] <= pivot)
				start++;
			a[end] = a[start];
		}
		a[start] = pivot;

		System.out.println("分割点:a["+start+"]:"+a[start]);
		printArray(a);    
		return start;//返回划分后的pivot的位置    
	}

	public void printArray(int a[]) {//打印数组内容的方法，用于测试    
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

}