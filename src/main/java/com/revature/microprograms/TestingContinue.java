package com.revature.microprograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Order;
import com.revature.util.SingletonScanner;

public class TestingContinue {

	private static Scanner scan = SingletonScanner.getScanner().getScan();
	
	public static void main(String[] args) {
		String[] veggiearray = {"carrots", "potatoes", "tomatoes", "cauliflower", "ginger", "garlic", "onions"};
		Arrays.sort(veggiearray);
		List<String> veggies = new ArrayList<String>();
		System.out.println("What kind of veggies would you like in your curry?");
		mainloop2: while(true) {
			System.out.println("Your options are: " + Arrays.toString(veggiearray));
			System.out.println("Type out your selections with 1 space in between.");
			String[] veg = scan.nextLine().split(" ");
			Arrays.sort(veg);
			System.out.println(Arrays.toString(veg));
			vegloop: for (String x: veg) {
				System.out.println(Arrays.binarySearch(veggiearray, x));
				if (Arrays.binarySearch(veggiearray, x) < 0) {
					// Just reading the binarySearch documentation, 
					// it seems like a negative value is only returned 
					// when the entry is not found.
					System.out.println("Those options are not on our menu. Try again.");
					continue mainloop2;
				} else {
					continue vegloop;
				}
			}
			veggies = Arrays.asList(veg);
			break;
		}
		System.out.println(veggies.toString());
	}
}
