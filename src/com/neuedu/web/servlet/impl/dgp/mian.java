package com.neuedu.web.servlet.impl.dgp;



public class mian {
	public static void main(String[] args) {
			String s="fsladasqewqfqw";
			int min = 20;

			int[] a = new int[11000];
			for (int i = 0; i < s.length(); i++) {
				int x = s.charAt(i);
				a[x]++;
				System.out.print(a[x]+","); 
			}

			for (int i = 0; i < s.length(); i++) {
				int x = s.charAt(i);
				if (a[x] < min) {
					min = a[x];
				}
			}

			for (int i = 0; i < s.length(); i++) {
				int x = s.charAt(i);
				if (a[x] != min) {
					System.out.print(s.charAt(i));
				}
			}
			System.out.println();

	}
}
