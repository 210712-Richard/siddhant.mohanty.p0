package com.revature.microprograms;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Order;
import com.revature.util.SingletonScanner;

public class TestingContinue {

	private static Scanner scan = SingletonScanner.getScanner().getScan();
	
	public static void main(String[] args) {
		System.out.println("What kind of veggies would you like in your curry?");
		mainloop2: while(true) {
			System.out.println("Your options are: " + Arrays.toString(Order.veggies));
			System.out.println("Type out your selections with 1 space in between.");
			String[] veg = scan.nextLine().split(" ");
			List<String> veggies = Arrays.asList(veg);
			vegloop1: for (String y : veg) {
				vegloop2: for (String x : Order.veggies) {
					while(!x.equals(y)) {
						continue vegloop2; 
					}
					continue vegloop1;
				}
				break mainloop2;
			}
			System.out.println("Your veggies don't match what we have on our menu. Try again. ");
		}
	}
}
