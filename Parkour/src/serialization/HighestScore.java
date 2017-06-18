package serialization;

import java.io.Serializable;

public class HighestScore implements Serializable{
	
	private static final long serialVersionUID = -1290882042322967050L;

	public String name;
	public int score;
	public HighestScore(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
}
