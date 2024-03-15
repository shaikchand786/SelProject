package ChandNaz.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentReporterNG {
	
	public static ExtentReports getExtentReport() {
		//ExtentReports & ExtentSparkReporter --> these two classes are very helpful to generate the Reports.
		//How to create the project path Dynamically?
		String path = ".\\reports\\index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setReportName("Web Automation Results");
		sparkReporter.config().setDocumentTitle("Test Reports");
		
		ExtentReports extReports = new ExtentReports();
		extReports.attachReporter(sparkReporter);
		extReports.setSystemInfo("Tester", "ChandNaz");
		return extReports;
		
	}
	
}
