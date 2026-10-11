package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Excel file path
    	String path = ".\\testData\\opencart_Login_Data.xlsx";
        // Create ExcelUtility object
        ExcelUtility xlutil = new ExcelUtility(path);

        // Get total rows and columns
        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        // Create 2D array
        String logindata[][] = new String[totalRows][totalCols];

        // Read data from Excel
        for (int i = 1; i <= totalRows; i++) {

            for (int j = 0; j < totalCols; j++) {

                logindata[i - 1][j] =
                        xlutil.getCellData("Sheet1", i, j);
            }
        }

        // Return 2D array
        return logindata;
    }

}