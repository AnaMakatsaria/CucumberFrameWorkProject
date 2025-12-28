package utils;


import pages.DashboardPage;
import pages.LoginPage;
import pages.PersonalDetailPage;
import pages.PimPage;

public class PageInitializer {
    public static LoginPage loginPage;
    public static PimPage pimPage;
    public static DashboardPage dashboardPage;
    public static PersonalDetailPage personalDetailPage;

    public static void initializePageObjects(){
        loginPage=new LoginPage();
        pimPage= new PimPage();
        dashboardPage=new DashboardPage();
        personalDetailPage=new PersonalDetailPage();
    }
}
