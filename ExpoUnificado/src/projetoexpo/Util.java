package projetoexpo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date());
	}
	
	public static java.sql.Date getSqlDate() {
		long millis=System.currentTimeMillis();  
		java.util.Date date= new java.util.Date(millis);
        return new java.sql.Date(date.getTime());
        
	}
}
