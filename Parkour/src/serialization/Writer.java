package serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Writer {
	public static void recordTheIdentity(Identity id){
		String url = id.getName();
//		String urlF = new String(Reader.class.getResource("Reader.class").toString());
//		urlF = urlF.substring(urlF.indexOf("/") + 1);
//		urlF = urlF.substring(0, urlF.lastIndexOf("/"));// 获得当前自节码所在parentFile
//		urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//		urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//		urlF = urlF.concat("/" + url + "Data.pkr");
		File f = new File("D:\\Program Files\\SuperParkour1.0\\"+url+"Data.pkr");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(id);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
