package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject()
    {
        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter esp=new ExtentSparkReporter(path);
        esp.config().setReportName("Web Automation Results");
        esp.config().setDocumentTitle("Test Results");

       ExtentReports extent =new ExtentReports();
        extent.attachReporter(esp);
        extent.setSystemInfo("Tester","Fasiha");
        return extent;

    }

}
