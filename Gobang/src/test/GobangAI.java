package test;

import java.util.ArrayList;

public class GobangAI {
	public static class Point {
		public int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static final int ROW_1 = 1;
	public static final int ROW_2 = 5;
	public static final int ROW_3 = 21;
	public static final int ROW_4 = 9999;
	public static int scoreA[][] = new int[15][15],scoreB[][]= new int[15][15],scoreC[][]= new int[15][15];
	/**
	 * boardΪ15-15��λ����board[0]Ϊ�������һ�� board[x][0]����ߵ�һ�� ����Point(x,y)��Ӧ��x��y��
	 * ע������������˳���෴
	 */
	public static Point go(int[][] board, boolean who){
		a:for(int i = 0;i<15;i++)
			for(int j = 0;j<15;j++){
				if(board[i][j]!=0)
					break a;
				if(i==14)
					return new Point(7,7);
			}
		int score[][] = goEach(board,who);//���ط���
		scoreA = score;
		int scoreB[][] = goEach(board,!who);//��������
		scoreC  = addUp(score,scoreB);
		GobangAI.scoreB = scoreB;
		int maxDe = findMax(score);
		int maxAt = findMax(scoreB);
		if(maxAt>=ROW_4){
			return randomMax(scoreB,maxAt);
		}
		else if(maxDe>=ROW_4){
			return randomMax(score,maxDe);
		}
		else if(maxAt>=2*ROW_3){
			return randomMax(scoreB,maxAt);
		}
		else if(maxDe>=2*ROW_3){
			return randomMax(score,maxDe);
		}
		return randomMax(scoreC,findMax(scoreC));
	}
	public static int[][] goEach(int[][] board, boolean who) {
		int theAim;
		if (who)
			theAim = 2;
		else
			theAim = 1;
		int[][] score = new int[15][15];
		// ����
		for (int i = 0; i < 15; i++) {
			int con = 0;
			for (int j = 0; j < 15; j++) {
				if (board[i][j] == theAim) {
					con++;
				} else {
					if (con == 1) {// 010���
						boolean head = j - 2 >= 0 && board[i][j - 2] == 0;
						boolean tail = board[i][j] == 0;
						if (head) {
							score[i][j - 2] += ROW_1;// 01x�����ǰһ��0
						}
						if (tail) {
							score[i][j] += ROW_1;// x10����ĺ�һ��0
						}
						if (tail && head) {
							score[i][j - 2] += ROW_1;
							score[i][j] += ROW_1;// 010��ǰ��
						}
					}
					if (con == 2) {// 0110���
						boolean head = j - 3 >= 0 && board[i][j - 3] == 0;
						boolean tail = board[i][j] == 0;
						if (head) {
							score[i][j - 3] += ROW_2;// 011x�����ǰһ��0
						}
						if (tail) {
							score[i][j] += ROW_2;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[i][j - 3] += ROW_2;
							score[i][j] += ROW_2;// 0110��ǰ��
						}
					}
					if (con == 3) {// 0110���
						boolean head = j - 4 >= 0 && board[i][j - 4] == 0;
						boolean tail = board[i][j] == 0;
						if (head) {
							score[i][j - 4] += ROW_3;// 0111x�����ǰһ��0
						}
						if (tail) {
							score[i][j] += ROW_3;// x1110����ĺ�һ��0
						}
						if (head && tail) {
							score[i][j - 4] += ROW_3;
							score[i][j] += ROW_3;// 01110��ǰ��
						}
					}
					if (con == 4) {// 011110���
						boolean head = j - 5 >= 0 && board[i][j - 5] == 0;
						boolean tail = board[i][j] == 0;
						if (head) {
							score[i][j - 5] += ROW_4;// 01111x�����ǰһ��0
						}
						if (tail) {
							score[i][j] += ROW_4;// x11110����ĺ�һ��0
						}
						if (head && tail) {
							score[i][j - 5] += ROW_4;
							score[i][j] += ROW_4;// 011110��ǰ��
						}
					}
					con = 0;
				}
			}
			if (board[i][14] == theAim) {
				int conn = 1;
				while (board[i][14 - conn] == theAim) {
					conn++;
				}
				if (board[i][14 - conn] == 0) {
					if (conn == 1) {
						score[i][14 - conn] += ROW_1;
					}
					if (conn == 2) {
						score[i][14 - conn] += ROW_2;
					}
					if (conn == 3) {
						score[i][14 - conn] += ROW_3;
					}
					if (conn == 4) {
						score[i][14 - conn] += ROW_4;
					}
				}
			}
		}
		// ����
		for (int i = 0; i < 15; i++) {
			int con = 0;
			for (int j = 0; j < 15; j++) {
				if (board[j][i] == theAim) {
					con++;
				} else {
					if (con == 1) {// 010���
						boolean head = j - 2 >= 0 && board[j - 2][i] == 0;
						boolean tail = board[j][i] == 0;
						if (head) {
							score[j - 2][i] += ROW_1;// 01x�����ǰһ��0
						}
						if (tail) {
							score[j][i] += ROW_1;// x10����ĺ�һ��0
						}
						if (tail && head) {
							score[j - 2][i] += ROW_1;
							score[j][i] += ROW_1;// 010��ǰ��
						}
					}
					if (con == 2) {// 0110���
						boolean head = j - 3 >= 0 && board[j - 3][i] == 0;
						boolean tail = board[j][i] == 0;
						if (head) {
							score[j - 3][i] += ROW_2;// 011x�����ǰһ��0
						}
						if (tail) {
							score[j][i] += ROW_2;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[j - 3][i] += ROW_2;
							score[j][i] += ROW_2;// 0110��ǰ��
						}
					}
					if (con == 3) {// 0110���
						boolean head = j - 4 >= 0 && board[j - 4][i] == 0;
						boolean tail = board[j][i] == 0;
						if (head) {
							score[j - 4][i] += ROW_3;// 011x�����ǰһ��0
						}
						if (tail) {
							score[j][i] += ROW_3;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[j - 4][i] += ROW_3;
							score[j][i] += ROW_3;// 0110��ǰ��
						}
					}
					if (con == 4) {// 0110���
						boolean head = j - 5 >= 0 && board[j - 5][i] == 0;
						boolean tail = board[j][i] == 0;
						if (head) {
							score[j - 5][i] += ROW_4;// 011x�����ǰһ��0
						}
						if (tail) {
							score[j][i] += ROW_4;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[j - 5][i] += ROW_4;
							score[j][i] += ROW_4;// 0110��ǰ��
						}
					}
					con = 0;
				}
			}
			if (board[14][i] == theAim) {
				int conn = 1;
				while (board[14 - conn][i] == theAim) {
					conn++;
				}
				if (board[14 - conn][i] == 0) {
					if (conn == 1) {
						score[14 - conn][i] += ROW_1;
					}
					if (conn == 2) {
						score[14 - conn][i] += ROW_2;
					}
					if (conn == 3) {
						score[14 - conn][i] += ROW_3;
					}
					if (conn == 4) {
						score[14 - conn][i] += ROW_4;
					}
				}
			}
		}
		/// ������ ���ϵ�������
		for (int i = 0; i < 15; i++) {
			int con = 0;
			for (int j = 0; j <= i; j++) {
				if (board[j][i - j] == theAim) {
					con++;
				} else {
					if (con == 1) {// 010���
						boolean head = j - 2 >= 0 && i - j + 2 < 15 && board[j - 2][i - j + 2] == 0;
						boolean tail = board[j][i - j] == 0;
						if (head) {
							score[j - 2][i - j + 2] += ROW_1;// 01x�����ǰһ��0
						}
						if (tail) {
							score[j][i - j] += ROW_1;// x10����ĺ�һ��0
						}
						if (tail && head) {
							score[j - 2][i - j + 2] +=ROW_1;
							score[j][i - j] += ROW_1;// 010��ǰ��
						}
					}
					if (con == 2) {// 0110���
						boolean head = j - 3 >= 0 && i - j + 3 < 15 && board[j - 3][i - j + 3] == 0;
						boolean tail = board[j][i - j] == 0;
						if (head) {
							score[j - 3][i - j + 3] += ROW_2;// 011x�����ǰһ��0
						}
						if (tail) {
							score[j][i - j] += ROW_2;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[j - 3][i - j + 3] += ROW_2;
							score[j][i - j] += ROW_2;// 0110��ǰ��
						}
					}
					if (con == 3) {// 0110���
						boolean head = j - 4 >= 0 && i - j + 4 < 15 && board[j - 4][i - j + 4] == 0;
						boolean tail = board[j][i - j] == 0;
						if (head) {
							score[j - 4][i - j + 4] += ROW_3;// 011x�����ǰһ��0
						}
						if (tail) {
							score[j][i - j] += ROW_3;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[j - 4][i - j + 4] += ROW_3;
							score[j][i - j] += ROW_3;// 0110��ǰ��
						}
					}
					if (con == 4) {// 0110���
						boolean head = j - 5 >= 0 && i - j + 5 < 15 && board[j - 5][i - j + 5] == 0;
						boolean tail = board[j][i - j] == 0;
						if (head) {
							score[j - 5][i - j + 5] += ROW_4;// 011x�����ǰһ��0
						}
						if (tail) {
							score[j][i - j] += ROW_4;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[j - 5][i - j + 5] += ROW_4;
							score[j][i - j] += ROW_4;// 0110��ǰ��
						}
					}
					con = 0;
				}
			}
			if (board[i][0] == theAim) {
				int conn = 1;
				while (i - conn >= 0 && board[i - conn][conn] == theAim) {
					conn++;
				}
				if (i - conn >= 0&&board[i-conn][conn]==0) {
					if (conn == 1) {
						score[i - conn][conn] += ROW_1;
					}
					if (conn == 2) {
						score[i - conn][conn] += ROW_2;
					}
					if (conn == 3) {
						score[i - conn][conn] += ROW_3;
					}
					if (conn == 4) {
						score[i - conn][conn] += ROW_4;
					}
				}
			}
		}
		/// ������ ���µ�������
		for (int i = 0; i < 14; i++) {
			int con = 0;
			for (int j = 0; j <= i; j++) {
				if (board[14 - j][14 - i + j] == theAim) {
					con++;
				} else {
					if (con == 1) {// 010���
						boolean head = 14 - j + 2 <= 14 && 14 - i + j - 2 >=0 && board[14 - j + 2][14 - i + j - 2] == 0;
						boolean tail = board[14 - j][14 - i + j] == 0;
						if (head) {
							score[14 - j + 2][14 - i + j - 2] += ROW_1;// 01x�����ǰһ��0
						}
						if (tail) {
							score[14 - j][14 - i + j] += ROW_1;// x10����ĺ�һ��0
						}
						if (tail && head) {
							score[14 - j + 2][14 - i + j - 2] += ROW_1;
							score[14 - j][14 - i + j] += ROW_1;// 010��ǰ��
						}
					}
					if (con == 2) {// 0110���
						boolean head = 14 - j + 3 <=14 && 14 - i + j - 3 >=0 && board[14 - j + 3][14 - i + j - 3] == 0;
						boolean tail = board[14 - j][14 - i + j] == 0;
						if (head) {
							score[14 - j + 3][14 - i + j - 3] += ROW_2;// 011x�����ǰһ��0
						}
						if (tail) {
							score[14 - j][14 - i + j] += ROW_2;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[14 - j + 3][14 - i + j - 3] += ROW_2;
							score[14 - j][14 - i + j] += ROW_2;// 0110��ǰ��
						}
					}
					if (con == 3) {// 0110���
						boolean head = 14 - j + 4 <=14 && 14 - i + j - 4 >=0 && board[14 - j + 4][14 - i + j - 4] == 0;
						boolean tail = board[14 - j][14 - i + j] == 0;
						if (head) {
							score[14 - j + 4][14 - i + j - 4] += ROW_3;// 011x�����ǰһ��0
						}
						if (tail) {
							score[14 - j][14 - i + j] += ROW_3;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[14 - j + 4][14 - i + j - 4] += ROW_3;
							score[14 - j][14 - i + j] += ROW_3;// 0110��ǰ��
						}
					}
					if (con == 4) {// 0110���
						boolean head = 14 - j + 5 <=14 && 14 - i + j - 5 >=0 && board[14 - j + 5][14 - i + j - 5] == 0;
						boolean tail = board[14 - j][14 - i + j] == 0;
						if (head) {
							score[14 - j + 5][14 - i + j - 5] += ROW_4;// 011x�����ǰһ��0
						}
						if (tail) {
							score[14 - j][14 - i + j] += ROW_4;// x110����ĺ�һ��0
						}
						if (head && tail) {
							score[14 - j + 5][14 - i + j - 5] += ROW_4;
							score[14 - j][14 - i + j] += ROW_4;// 0110��ǰ��
						}
					}
					con = 0;
				}
			}
			if (board[14 - i][14] == theAim) {
				int conn = 1;
				while (14 - i + conn <= 14 && board[14 - i + conn][14 - conn] == theAim) {
					conn++;
				}
				if (14 - i + conn <= 14&&board[14-i+conn][14-conn]==0) {
					if (conn == 1) {
						score[14 - i + conn][14 - conn] += ROW_1;
					}
					if (conn == 2) {
						score[14 - i + conn][14 - conn] += ROW_2;
					}
					if (conn == 3) {
						score[14 - i + conn][14 - conn] += ROW_3;
					}
					if (conn == 4) {
						score[14 - i + conn][14 - conn] += ROW_4;
					}
				}
			}
		}
		
		
		
		
		//\ ������ ���µ�������
				for (int i = 0; i < 15; i++) {
					int con = 0;
					for (int j = 0; j <= i; j++) {
						if (board[14-j][i - j] == theAim) {
							con++;
						} else {
							if (con == 1) {// 010���
								boolean head =14- j + 2 <= 14 && i - j + 2 < 15 && board[14-j + 2][i - j + 2] == 0;
								boolean tail = board[14-j][i - j] == 0;
								if (head) {
									score[14-j + 2][i - j + 2] += ROW_1;// 01x�����ǰһ��0
								}
								if (tail) {
									score[14-j][i - j] += ROW_1;// x10����ĺ�һ��0
								}
								if (tail && head) {
									score[14-j + 2][i - j + 2] += ROW_1;
									score[14-j][i - j] += ROW_1;// 010��ǰ��
								}
							}
							if (con == 2) {// 0110���
								boolean head = 14-j + 3 <15 && i - j + 3 < 15 && board[14-j + 3][i - j + 3] == 0;
								boolean tail = board[14-j][i - j] == 0;
								if (head) {
									score[14-j + 3][i - j + 3] += ROW_2;// 011x�����ǰһ��0
								}
								if (tail) {
									score[14-j][i - j] += ROW_2;// x110����ĺ�һ��0
								}
								if (head && tail) {
									score[14-j + 3][i - j + 3] += ROW_2;
									score[14-j][i - j] += ROW_2;// 0110��ǰ��
								}
							}
							if (con == 3) {// 0110���
								boolean head =14- j + 4 <15 && i - j + 4 < 15 && board[14-j + 4][i - j + 4] == 0;
								boolean tail = board[14-j][i - j] == 0;
								if (head) {
									score[14-j + 4][i - j + 4] += ROW_3;// 011x�����ǰһ��0
								}
								if (tail) {
									score[14-j][i - j] += ROW_3;// x110����ĺ�һ��0
								}
								if (head && tail) {
									score[14-j + 4][i - j + 4] += ROW_3;
									score[14-j][i - j] += ROW_3;// 0110��ǰ��
								}
							}
							if (con == 4) {// 0110���
								boolean head = 14-j + 5 <15 && i - j + 5 < 15 && board[14-j + 5][i - j + 5] == 0;
								boolean tail = board[14-j][i - j] == 0;
								if (head) {
									score[14-j + 5][i - j + 5] += ROW_4;// 011x�����ǰһ��0
								}
								if (tail) {
									score[14-j][i - j] += ROW_4;// x110����ĺ�һ��0
								}
								if (head && tail) {
									score[14-j + 5][i - j + 5] += ROW_4;
									score[14-j][i - j] += ROW_4;// 0110��ǰ��
								}
							}
							con = 0;
						}
					}
					if (board[14-i][0] == theAim) {
						int conn = 1;
						while (14-i + conn <15 && board[14-i + conn][conn] == theAim) {
							conn++;
						}
						if (14-i + conn <15&&board[14-i+conn][conn]==0) {
							if (conn == 1) {
								score[14-i + conn][conn] += ROW_1;
							}
							if (conn == 2) {
								score[14-i + conn][conn] += ROW_2;
							}
							if (conn == 3) {
								score[14-i + conn][conn] += ROW_3;
							}
							if (conn == 4) {
								score[14-i + conn][conn] += ROW_4;
							}
						}
					}
				}
				//\ ������ ���ϵ�������
				for (int i = 0; i < 14; i++) {
					int con = 0;
					for (int j = 0; j <= i; j++) {
						if (board[j][14 - i + j] == theAim) {
							con++;
						} else {
							if (con == 1) {// 010���
								boolean head = j-2 >=0 && 14 - i + j - 2 >=0 && board[j-2][14 - i + j - 2] == 0;
								boolean tail = board[j][14 - i + j] == 0;
								if (head) {
									score[j-2][14 - i + j - 2] += ROW_1;// 01x�����ǰһ��0
								}
								if (tail) {
									score[j][14 - i + j] += ROW_1;// x10����ĺ�һ��0
								}
								if (tail && head) {
									score[j-2][14 - i + j - 2] += ROW_1;
									score[j][14 - i + j] += ROW_1;// 010��ǰ��
								}
							}
							if (con == 2) {// 0110���
								boolean head = j-3>=0 && 14 - i + j - 3 >=0 && board[j-3][14 - i + j - 3] == 0;
								boolean tail = board[j][14 - i + j] == 0;
								if (head) {
									score[j-3][14 - i + j - 3] += ROW_2;// 011x�����ǰһ��0
								}
								if (tail) {
									score[j][14 - i + j] += ROW_2;// x110����ĺ�һ��0
								}
								if (head && tail) {
									score[j-3][14 - i + j - 3] += ROW_2;
									score[j][14 - i + j] += ROW_2;// 0110��ǰ��
								}
							}
							if (con == 3) {// 0110���
								boolean head = j-4>=0 && 14 - i + j - 4 >=0 && board[j-4][14 - i + j - 4] == 0;
								boolean tail = board[j][14 - i + j] == 0;
								if (head) {
									score[j-4][14 - i + j - 4] += ROW_3;// 011x�����ǰһ��0
								}
								if (tail) {
									score[j][14 - i + j] += ROW_3;// x110����ĺ�һ��0
								}
								if (head && tail) {
									score[j-4][14 - i + j - 4] += ROW_3;
									score[j][14 - i + j] += ROW_3;// 0110��ǰ��
								}
							}
							if (con == 4) {// 0110���
								boolean head = j-5>=0 && 14 - i + j - 5 >=0 && board[j-5][14 - i + j - 5] == 0;
								boolean tail = board[j][14 - i + j] == 0;
								if (head) {
									score[j-5][14 - i + j - 5] += ROW_4;// 011x�����ǰһ��0
								}
								if (tail) {
									score[j][14 - i + j] += ROW_4;// x110����ĺ�һ��0
								}
								if (head && tail) {
									score[j-5][14 - i + j - 5] += ROW_4;
									score[j][14 - i + j] += ROW_4;// 0110��ǰ��
								}
							}
							con = 0;
						}
					}
					if (board[i][14] == theAim) {
						int conn = 1;
						while (i-conn>=0 && board[i-conn][14 - conn] == theAim) {
							conn++;
						}
						if (i-conn>=0&&board[i-conn][14-conn]==0) {
							if (conn == 1) {
								score[i-conn][14 - conn] += ROW_1;
							}
							if (conn == 2) {
								score[i-conn][14 - conn]+= ROW_2;
							}
							if (conn == 3) {
								score[i-conn][14 - conn]+= ROW_3;
							}
							if (conn == 4) {
								score[i-conn][14 - conn] += ROW_4;
							}
						}
					}
				}
		return score;
	}

	private static int[][] addUp(int[][] a, int[][] b) {
		int[][] c = new int[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		return c;
	}

	private static int findMax(int[][] score) {
		int max = 0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (score[i][j] > max)
					max = score[i][j];
			}
		}
		return max;
	}

	private static Point randomMax(int[][] score, int max) {
		ArrayList<Point> ps = new ArrayList<Point>();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (score[i][j] == max)
					ps.add(new Point(j, i));
			}
		}
		int one = (int) (Math.random() * ps.size());
		return ps.get(one);
	}
}
