package com.geocoder.demo.dao;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int[] a = new int[]{38, 27, 43, 3, 9, 82, 10};
		int[] longA = new int[500];
		for(int i=0; i<500; i++){
			longA[i] = (int)(Math.random()*100 + 1);
		}
		//bubbleSort(longA);
		//selectionSort(a);
		//insertionSort(longA);
		//int res = mergeSort(a, 0, a.length-1);
		//partition(a, 0, a.length-1);
		//Arrays.sort(a);
		quickSort(longA, 0, longA.length-1);
		for(int i : longA){
			System.out.print(i+", ");
		}
		//System.out.println("res - " + res);
	}
	
	static void bubbleSort(int[] a){
		int size = a.length;
		long time = System.currentTimeMillis();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size-1; j++){
				if(a[j] > a[j+1]){
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
				}
			}
		}
		System.out.println(String.format("Time taken by bubble Sort - %s", System.currentTimeMillis() - time));
	}
	
	static void selectionSort(int[] a){
		int min;
		int size = a.length;
		for(int i=0; i<size-1; i++){
			min = i;
			for(int j=i+1; j<size; j++){
				if(a[j] < a[min]){
					min = j;
				}
			}
			swap(a, min, i);
		}
	}
	
	static void insertionSort(int[] a){
		
		for(int i=1; i<a.length; i++){
			int k=i-1;
			int element =  a[i];
			while(k>=0){
				if(a[k]>element){
					a[k+1] = a[k];
					k--;
				}
				else break;
			}
			a[k+1] = element;
		}
	}
	
	static int mergeSort(int []a, int start, int end){
		int count = 0;
		if(start<end) {
		
		int mid = (start+end)/2;
		
		count = mergeSort(a, start, mid);
		count += mergeSort(a, mid+1, end);
		
		count += merge(a, start, mid, end);
		}
		return count;
	}
	
	static int merge(int[] a, int start, int mid, int end){
		int p = start, q = mid+1;
		int count = 0;
		int[] arr = new int[end-start+1];
		int arrInd = 0;
		for(int i=start; i<=end; i++){
			
			if(p>mid){
				arr[arrInd++] = a[q++];
			}
			else if(q>end){
				arr[arrInd++] = a[p++];
			}
			else if(a[p]<a[q]){
				arr[arrInd++] = a[p++];
			}
			else {
				count = count + mid - p + 1;
				arr[arrInd++] = a[q++];
			}
		}
		for(int j=0; j<arrInd; j++){
			a[start++] = arr[j];
		}
		return count;
	}
	
	static void quickSort(int[] arr, int start, int end){
		if(start<end){
			int m = partition(arr, start, end);
			/*for(int i : arr){
				System.out.print(i+", ");
			}
			System.out.println();*/
			quickSort(arr, start, m-1);
			quickSort(arr, m+1, end);
		}
	}
	
	static int partition(int[] arr, int start, int end) {
		int piv = arr[end];
		int j=end-1;
		
		for(int i=j; i>=start; i--) {
			if(arr[i] > piv){
				swap(arr, i, j);
				j--;
			}
		}
		swap(arr, end, j+1);
		return j+1;
	}
	
	static void swap(int[] arr, int a, int b){
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
