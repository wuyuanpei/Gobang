package calculator;

import java.util.Random;

public class NumProducer {
	public static int[] result = new int[4];
	public static int[] getNums(int boundary){
		do{
			for(int i =0; i<result.length;i++){
				result[i] = new Random().nextInt(boundary)+1;
			}
		}while(!ValidCalculator.calculate(result));
		return result;
	}
}
