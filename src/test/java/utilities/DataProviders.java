package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException{
		
		String path="./testData/OpenCart_LoginData.xlsx";
		ExcelUtility xlutility=new ExcelUtility(path);
		int rowCount=xlutility.getRowCount("Sheet1");
		int colCount=xlutility.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				loginData[i-1][j]=xlutility.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}

}
