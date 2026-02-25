package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtil;

import static org.testng.Assert.assertTrue;

public class loginTestIcorrectUsername extends BaseTest {

    @Test
    public void testLoginUsingExcelData() {
        // Create Extent test node
        test = extent.createTest("Login Test Using Excel Data");

        String username = ExcelUtil.getCellData("Sheet1", 8, 0);
        String password = ExcelUtil.getCellData("Sheet1", 8, 1);

        test.info("Starting login test with username: " + username);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        test.info("Entered login credentials");

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.getPageTitle().contains("Swag Labs"), "Login failed - title mismatch");

        test.pass("Login successful and verified title");
    }
//    @Test
//    public void testLoginUsingExcelData2() {
//        // Create Extent test node
//        test = extent.createTest("Login Test Using Excel Data2");
//
//        String username = ExcelUtil.getCellData("Sheet1", 12, 0);
//        String password = ExcelUtil.getCellData("Sheet1", 1, 1);
//
//        test.info("Starting login test with username: " + username);
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login(username, password);
//        test.info("Entered login credentials");
//
//        HomePage homePage = new HomePage(driver);
//        assertTrue(homePage.getPageTitle().contains("Swag Labs"), "Login failed - title mismatch");
//
//        test.pass("Login successful and verified title");
//    }
}
