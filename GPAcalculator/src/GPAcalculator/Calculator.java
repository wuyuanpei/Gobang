package GPAcalculator;

/**
 * 计算器类 程序入口及计算方法
 * 
 * @author lenovo
 *
 */
public class Calculator {

	public static void main(String[] args) {
		// 处理一个循环使用下来时的位置信息 并创建输入窗口（第一窗口）
		if (args.length == 1) {
			// System.out.println(args[0]);
			int a = args[0].indexOf(",");
			// System.out.println(a);
			String xs = args[0].substring(17, a);
			String ys = args[0].substring(a + 3, args[0].length() - 1);
			// System.out.println(xs+","+ys);
			int x = Integer.valueOf(xs);
			int y = Integer.valueOf(ys);
			MyFrame mf = new MyFrame(x, y);
			mf.setVisible(true);
		} else {
			MyFrame mf = new MyFrame(-1, -1);
			mf.setVisible(true);
		}
		
	}
	/**
	 * aplevel底分判定
	 * @param score
	 * @return
	 */
	private static double aplevel(int score) {
		if (score >= 93)
			return 5;
		if (score >= 90)
			return 4.7;
		if (score >= 87)
			return 4.3;
		if (score >= 83)
			return 4;
		if (score >= 80)
			return 3.7;
		if (score >= 77)
			return 3.3;
		if (score >= 73)
			return 3;
		if (score >= 66)
			return 2.7;
		if (score >= 60)
			return 2.4;
		return 0;
	}
	/**
	 * hlevel底分判定
	 * @param score
	 * @return
	 */
	private static double hlevel(int score) {
		if (score >= 93)
			return 4.5;
		if (score >= 90)
			return 4.2;
		if (score >= 87)
			return 3.8;
		if (score >= 83)
			return 3.5;
		if (score >= 80)
			return 3.2;
		if (score >= 77)
			return 2.8;
		if (score >= 73)
			return 2.5;
		if (score >= 66)
			return 2.2;
		if (score >= 60)
			return 1.9;
		return 0;
	}
	/**
	 * slevel底分判定
	 * @param score
	 * @return
	 */
	private static double slevel(int score) {
		if (score >= 93)
			return 4;
		if (score >= 90)
			return 3.7;
		if (score >= 87)
			return 3.3;
		if (score >= 83)
			return 3;
		if (score >= 80)
			return 2.7;
		if (score >= 77)
			return 2.3;
		if (score >= 73)
			return 2;
		if (score >= 66)
			return 1.7;
		if (score >= 60)
			return 1.4;
		return 0;
	}
	/**
	 * englishhpluslevel底分判定
	 * @param score
	 * @return
	 */
	private static double englishhpluslevel(int score) {
		if (score >= 93)
			return 4.5;
		if (score >= 90)
			return 4.2;
		if (score >= 87)
			return 3.8;
		if (score >= 83)
			return 3.5;
		if (score >= 80)
			return 3.2;
		if (score >= 77)
			return 2.8;
		if (score >= 73)
			return 2.5;
		if (score >= 66)
			return 2.2;
		if (score >= 60)
			return 1.9;
		return 0;
	}
	/**
	 * englishhlevel底分判定
	 * @param score
	 * @return
	 */
	private static double englishhlevel(int score) {
		if (score >= 93)
			return 4.4;
		if (score >= 90)
			return 4.1;
		if (score >= 87)
			return 3.7;
		if (score >= 83)
			return 3.4;
		if (score >= 80)
			return 3.1;
		if (score >= 77)
			return 2.7;
		if (score >= 73)
			return 2.4;
		if (score >= 66)
			return 2.1;
		if (score >= 60)
			return 1.8;
		return 0;
	}
	/**
	 * englishhminuslevel底分判定
	 * @param score
	 * @return
	 */
	private static double englishhminuslevel(int score) {
		if (score >= 93)
			return 4.3;
		if (score >= 90)
			return 4;
		if (score >= 87)
			return 3.6;
		if (score >= 83)
			return 3.3;
		if (score >= 80)
			return 3;
		if (score >= 77)
			return 2.6;
		if (score >= 73)
			return 2.3;
		if (score >= 66)
			return 2;
		if (score >= 60)
			return 1.7;
		return 0;
	}
	/**
	 * 计算函数
	 * @param yuwen
	 * @param qianghua
	 * @param wenxue
	 * @param e6
	 * @param shuxue
	 * @param ap1
	 * @param ap2
	 * @param lishi
	 * @param zhengzhi
	 * @param qianghualevel
	 * @param wenxuelevel
	 * @return
	 */
	public static double calculate(int yuwen, int qianghua, int wenxue, int e6, int shuxue, int ap1, int ap2, int lishi,
			int zhengzhi, int qianghualevel, int wenxuelevel) {
		double yuwengpa = hlevel(yuwen);//get YUWEN base
		double qianghuagpa = 0, wenxuegpa = 0;//get QIANGHUA & WENXUE base according to level
		if (qianghualevel == myActionListener.H)
			qianghuagpa = englishhlevel(qianghua);
		if (qianghualevel == myActionListener.H1)
			qianghuagpa = englishhpluslevel(qianghua);
		if (qianghualevel == myActionListener.H2)
			qianghuagpa = englishhminuslevel(qianghua);

		if (wenxuelevel == myActionListener.H)
			wenxuegpa = englishhlevel(wenxue);
		if (wenxuelevel == myActionListener.H1)
			wenxuegpa = englishhpluslevel(wenxue);
		if (wenxuelevel == myActionListener.H2)
			wenxuegpa = englishhminuslevel(wenxue);
		//get every subject's base according to specific level
		double e6gpa = slevel(e6);
		double shuxuegpa = aplevel(shuxue);
		double ap1gpa = aplevel(ap1);
		double ap2gpa = aplevel(ap2);
		double lishigpa = slevel(lishi);
		double zhengzhigpa = slevel(zhengzhi);
		double result = yuwengpa * 3 + qianghuagpa * 5 + wenxuegpa * 5 + e6gpa * 2 + shuxuegpa * 5 + ap1gpa * 5
				+ ap2gpa * 5 + lishigpa * 2 + zhengzhigpa * 2;//get result gross gpa
		result /= 34;//get final gpa
		return result;
	}
	/**
	 * get gpa round to 0.01
	 * @param result
	 * @return
	 */
	public static double roundTo(double result) {
		result *= 100;
		result = Math.round(result);
		result /= 100;
		return result;
	}
	/**
	 * get estimated rank according to gpa, through ESTIMSTION FUNCTION
	 * @param gpa
	 * @return
	 */
	public static int rank(double gpa) {
		int result = 0;
		if (gpa <= 4.2) {
			result = (int) (4 * gpa * gpa + 4.62857 * gpa);
		} else {
			result = (int) (65.0802 * gpa - 5 * gpa * gpa - 95.137);
		}
		return 100 - result;
	}
}
