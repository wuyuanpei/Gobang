package calculator;

import java.util.ArrayList;

public class ValidCalculator {
	
	private static boolean canBeCalculated = false;
	private static StringBuffer hint = new StringBuffer();
	public static String hintFinal;
	
	/**
	 * 判断四个数是否可以计算24点
	 * @param a 整数a
	 * @param b 整数b
	 * @param c 整数c
	 * @param d 整数d
	 * @return 这四个数是否可以计算24点
	 */
	public static boolean calculate(int a, int b, int c, int d) {
		hint = new StringBuffer();
		hintFinal = "";
		canBeCalculated = false;
		double[] nums = {a,b,c,d};
		calculate(nums);
		return canBeCalculated;
	}
	public static boolean calculate(int[] nums){
		return calculate(nums[0],nums[1],nums[2],nums[3]);
	}
	private static void calculate(double[] nums) {
		if(canBeCalculated)
			return;
		if (nums.length == 1) {
			if (nums[0] !=24) {
				return;
			} else {
				canBeCalculated = true;
				hintFinal = hint.substring(hint.lastIndexOf("x")+1);
				return;
			}
		}

		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (j == i)
					continue;
				double a = nums[i] + nums[j];
				hint .append("x"+nums[i]+"＋"+nums[j]);
				putIntoList(a, nums, i, j);
				double b = nums[i] - nums[j];
				hint .append("x"+nums[i]+"－"+nums[j]);
				putIntoList(b, nums, i, j);
				double c = nums[i] * nums[j];
				hint .append("x"+nums[i]+"×"+nums[j]);
				putIntoList(c, nums, i, j);
				if (nums[j] != 0) {
					double d = nums[i] / nums[j];
					hint .append("x"+nums[i]+"÷"+nums[j]);
					putIntoList(d, nums, i, j);
				}
			}
		}
	}
	private static void putIntoList(double a, double[] nums, int i, int j){
		ArrayList<Double> temp = new ArrayList<>();
		temp.add(a);
		for (int k = 0; k < nums.length; k++) {
			if (k != i && k != j) {
				temp.add(nums[k]);
			}
		}
		double[] nums2 = new double[nums.length - 1];
		for (int k = 0; k < temp.size(); k++) {
			nums2[k] = temp.get(k);
		}
		calculate(nums2);
	}
}
