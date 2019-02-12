package br.com.castgroup.maxdifference;

import java.util.Scanner;

/**
 * App to compute the maximum difference between any item and any lower indexed smaller item 
 * for all the possible pairs.
 * @author alsantos
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = readInt(in, 0, 211); // n precisa ser maior ou igual a 1 e menor ou igual a 210 (0, 211)
		int a[] = readArray(in, n);
		
		int result = maxDifference(a);
		System.out.println("The maximum difference is " + result);
		
		in.close();
	}

	/**
	 * Calculate the maximum difference between 
	 * any item and any lower indexed smaller item 
	 * for all the possible pairs.
	 * @param a the array
	 * @return the maximum difference, or -1 if there are no lower 
	 * indexed smaller items for all the items
	 */
	public static int maxDifference(int[] a) {
		int min_element=a[0]; // primeiro elemento
	    int diff=a[1]-a[0]; // realizando a primeira diferenca
	    for(int i=1;i<a.length;i++)
	    {
	        if(a[i]-min_element>diff) {
	            diff=a[i]-min_element;
	        }
	        if(a[i]<min_element) {
	            min_element=a[i];
	        }
	    }
	    return diff;
	}

	/**
	 * Read a number from System.in
	 * @param in the Scanner object
	 * @param min the minimum number accepted
	 * @param max the maximum number accepted
	 * @return the read number
	 */
	public static int readInt(Scanner in, int min, int max) {	
		String value = null;
		int num = -1;
		do {
			System.out.println("Please, enter a valid number in following format: "+min+"<n"+"<"+ max);
			value = in.nextLine();
			try {
				num = Integer.parseInt(value);
				if (num <= 0 || num >= 211) {
					num = -1;
				}
			} catch (NumberFormatException e) {
				num = -1;
			}
			
		} while (num == -1);
		return num;
	}
	
	/**
	 * Fill the array
	 * @param in the Scanner object
	 * @param n the size of the array
	 * @return the array filled
	 */
	private static int[] readArray(Scanner in, int n) {
		int[] result =  new int[n];
		for (int i=0; i<n; i++) {
			result[i] = readInt(in, -106, 106);
		}
		return result;
	}
}
