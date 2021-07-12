package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Testbase;

public class Addcusomer extends Testbase{

	
	
	@Test(dataProvider="data-provider")
	public void addcustomer(String firstname,String lastname,String postcode) {
		
		findelemntAndClickXpath(OR.getProperty("managerbtn"));
		findelemntAndClickXpath(OR.getProperty("addcustomer"));
		findelementAndSendkeys(OR.getProperty("firstname"),firstname);
		findelementAndSendkeys(OR.getProperty("lastname"),lastname);
		findelementAndSendkeys(OR.getProperty("postcode"),postcode);
	}
	
	@DataProvider(name = "data-provider")
	public Object[][] getData(){
		
		String sheetname="Testdata";
		int colNum = 0,rowNum = 0;
		
		String rows=excel.getCellData(sheetname, colNum, rowNum);

		 Object[][] data= new Object[rowNum-1][colNum];
for(int rownumber=2;rownumber<=rowNum;rownumber++) {
	
	for(int columnnumber=2;columnnumber<=colNum;columnnumber++) {
data[rownumber-2][columnnumber]=excel.getCellData(sheetname, columnnumber, rownumber);
	
}
}

return data;
	}
}



