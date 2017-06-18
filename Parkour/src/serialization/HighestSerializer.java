package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class HighestSerializer {
	public static String identifyHighest() {
		try {
//			String urlF = new String(Reader.class.getResource("Reader.class").toString());
//			urlF = urlF.substring(urlF.indexOf("/") + 1);
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));// ��õ�ǰ�Խ�������parentFile
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//			urlF = urlF.concat("/highestScore.hsd");
			File f = new File("D:\\Program Files\\SuperParkour1.0\\highestScore.hsd");
			if (!f.exists()) {
				f.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(new HighestScore("Admin",999));
				out.close();
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			HighestScore hs = (HighestScore)in.readObject();
			in.close();
			return "��ϲ "+hs.name+" ������� "+hs.score+"��!";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void recordHighest(Identity id){
		try {
//			String urlF = new String(Reader.class.getResource("Reader.class").toString());
//			urlF = urlF.substring(urlF.indexOf("/") + 1);
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));// ��õ�ǰ�Խ�������parentFile
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//			urlF = urlF.concat("/highestScore.hsd");
			File f = new File("D:\\Program Files\\SuperParkour1.0\\highestScore.hsd");
//			if (!f.exists()) {
//				f.createNewFile();
//				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
//				out.writeObject(new HighestScore("Developer Richard",999));
//				out.close();
//			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			HighestScore hs = (HighestScore)in.readObject();
			in.close();
			if(hs.score>=id.getHighestScore()){
				return;
			}
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(new HighestScore(id.getName(),id.getHighestScore()));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
