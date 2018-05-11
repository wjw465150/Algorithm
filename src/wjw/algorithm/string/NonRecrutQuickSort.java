package wjw.algorithm.string;

import java.util.Stack;

//��������ǵݹ��㷨��javaʵ��
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
			System.out.println("�ǵݹ���ţ�����ջ");
			int a[] = { 10, 1, 4, 7, 8, 6, 13, 41, 4, 34, 5, 9, 2 };
			printArray(a);
			nonRecrutTwoStackSort(a);
			printArray(a);
		}

		System.out.println("");

		{
			System.out.println("�ǵݹ���ţ���һ��ջ�洢�ֽ��λ��");
			int a[] = { 10, 1, 4, 7, 8, 6, 13, 41, 4, 34, 5, 9, 2 };
			printArray(a);
			nonRecrutOneStackQuickSort(a);
			printArray(a);
		}
	}

	public void nonRecrutTwoStackSort(int[] a) {//�ǵݹ���ţ�����ջ    
		if (a == null || a.length < 2) {
			return;
		}
		Stack<Integer> startStack = new Stack<Integer>();//���浱ǰ���ֵ����λ    
		Stack<Integer> endStack = new Stack<Integer>();//���浱ǰ���ֵ����λ    
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
		Stack<Integer> index = new Stack<Integer>(); //��һ��ջ�洢�ֽ��λ�õ�  
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

	public int partition(int[] a, int start, int end) {//�ֿ鷽����������a�У����±��start��end�����н��л���    
		int pivot = a[start]; //�ѱ�pivot(��ʼ��pivot=a[start]С�����ƶ���pivot�����    
		while (start < end) { //�ѱ�pivot������ƶ���pivot���ұ�    
			while (start < end && a[end] >= pivot)
				end--;
			a[start] = a[end];
			while (start < end && a[start] <= pivot)
				start++;
			a[end] = a[start];
		}
		a[start] = pivot;

		System.out.println("�ָ��:a["+start+"]:"+a[start]);
		printArray(a);    
		return start;//���ػ��ֺ��pivot��λ��    
	}

	public void printArray(int a[]) {//��ӡ�������ݵķ��������ڲ���    
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

}