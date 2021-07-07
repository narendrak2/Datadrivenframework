package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Testproperties {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		Properties config=new Properties();
		Properties OR=new Properties();
		FileInputStream fi=new FileInputStream("/Users/narendra/git/Datadrivenframework/Datadrivenframework/src/test/resources/properties/config.properties");
		config.load(fi);
		fi=new FileInputStream("/Users/narendra/git/Datadrivenframework/Datadrivenframework/src/test/resources/properties/Objectstorage.properties");
		OR.load(fi);
	}

}
