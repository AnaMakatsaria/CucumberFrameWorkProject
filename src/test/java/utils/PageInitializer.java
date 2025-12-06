package utils;

import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PersonalDetailPage;

public class PageInitializer {
    public static LoginPage loginPage;
    public static AddEmployeePage addEmployeePage;
    public static DashboardPage dashboardPage;
    public static PersonalDetailPage personalDetailPage;

    public static void initializePageObjects(){
        loginPage=new LoginPage();
        addEmployeePage= new AddEmployeePage();
        dashboardPage=new DashboardPage();
        personalDetailPage=new PersonalDetailPage();

    }
}
