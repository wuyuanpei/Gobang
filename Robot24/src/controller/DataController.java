package controller;


public class DataController {
	
	public static final double NO_NUM = 0.1929113;
	
	private static double num1 = NO_NUM,num2 = NO_NUM;
	
	private static double result = NO_NUM;
	
	private static int cmd;
	
	public static final int PLUS_CMD = 1;
	public static final int MINUS_CMD = 2;
	public static final int MULTIPLY_CMD = 3;
	public static final int DIVIDE_CMD = 4;
	
	/**
	 * 
	 * @param num 按钮数字
	 * @return true 第一个数赋值（替换）成功
	 *                 false 第二个数以及计算成功
	 */
	public static boolean  setNum(double num) {
		//在下一次输入Num1时清除数据，保留是因为联想功能
		if(result!=NO_NUM){
			DataController.clear();
		}
		if(cmd == 0){
			num1 = num;//给1赋值
			return true;
		}else{
			num2 = num;
			calculate();
			return false;
		}
	}
	public static void clear(){
		num1 = NO_NUM;
		num2 = NO_NUM;
		cmd = 0;
		result = NO_NUM;
	}
	public static void calculate(){
		if(cmd == PLUS_CMD){
			result = num1+num2;
		}else
		if(cmd == MINUS_CMD){
			result = num1-num2;
		}else
		if(cmd == MULTIPLY_CMD){
			result = num1*num2;
		}else
		if(cmd == DIVIDE_CMD){
			if(num2!=0)
			result = num1/num2;
			else
			result = 0;
		}
	}
	public static int getCmd() {
		return cmd;
	}

	public static void setCmd(int cmd) {
		if(result !=NO_NUM){
			double temp = result;
			clear();
			num1 = temp;
			NumActionListener.button1 = NumActionListener.button2;
		}
		if(num1!=NO_NUM)
			DataController.cmd = cmd;
	}

	public static double getNum1() {
		return num1;
	}

	public static void setNum1(double num1) {
		DataController.num1 = num1;
	}

	public static double getNum2() {
		return num2;
	}

	public static void setNum2(double num2) {
		DataController.num2 = num2;
	}
	public static double getResult() {
		return result;
	}
	public static void setResult(double result) {
		DataController.result = result;
	}
	
}
