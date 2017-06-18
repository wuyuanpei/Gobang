package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import global.Param;

public class Reader {
	/**
	 * 
	 * @param url
	 *            这里的url是用户输入的用户名
	 * @return
	 */
	public static Identity getTheIdentity(String url) {
		try {
//			String urlF = new String(Reader.class.getResource("Reader.class").toString());
//			urlF = urlF.substring(urlF.indexOf("/") + 1);
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));// 获得当前自节码所在parentFile
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//			urlF = urlF.substring(0, urlF.lastIndexOf("/"));
//			urlF = urlF.concat("/" + url + "Data.pkr");
			File f = new File("D:\\Program Files\\SuperParkour1.0\\"+url+"Data.pkr");
			if (!f.exists()) {
				f.createNewFile();
				Identity id = new Identity(url,10,100,0,Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				if(url.equals(Param.ADMIN_CODE))
					id = new Identity(url,99999,999999,0,Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
				out.writeObject(id);
				out.close();
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			Identity id = (Identity) in.readObject();
			in.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
