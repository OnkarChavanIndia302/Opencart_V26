
package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    // Create Extent Report
    private static synchronized ExtentReports getExtentReport() {

        if (extent == null) {

            File reportFolder = new File("reports");

            if (!reportFolder.exists()) {
                reportFolder.mkdirs();
            }

            String timestamp = new SimpleDateFormat(
                    "yyyy.MM.dd.HH.mm.ss").format(new Date());

            String reportPath = new File(
                    reportFolder,
                    "OpenCart-Test-Report-" + timestamp + ".html")
                    .getAbsolutePath();

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle(
                    "OpenCart Automation Report");

            sparkReporter.config().setReportName(
                    "OpenCart Functional Testing");

            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "OpenCart");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User Name",
                    System.getProperty("user.name"));
            extent.setSystemInfo("Java Version",
                    System.getProperty("java.version"));
            extent.setSystemInfo("Operating System",
                    System.getProperty("os.name"));
        }

        return extent;
    }

    // Read XML parameters and initialize report
    @Override
    public void onStart(ITestContext context) {

        ExtentReports report = getExtentReport();

        String browser = context.getCurrentXmlTest()
                .getParameter("browser");

        String os = context.getCurrentXmlTest()
                .getParameter("os");

        report.setSystemInfo("Browser",
                browser != null ? browser : "Not specified");

        report.setSystemInfo("XML Operating System",
                os != null ? os : "Not specified");
    }

    // Test started
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                getExtentReport().createTest(
                        result.getMethod().getMethodName());

        test.set(extentTest);

        String[] groups = result.getMethod().getGroups();

        if (groups != null) {
            for (String group : groups) {
                extentTest.assignCategory(group);
            }
        }

        extentTest.log(Status.INFO,
                "Test execution started");

        extentTest.log(Status.INFO,
                "Test Class: " + result.getTestClass().getName());
    }

    // Test passed
    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest extentTest = getTest();

        if (extentTest != null) {
            extentTest.log(Status.PASS,
                    "Test passed successfully");
        }
    }

    // Test failed
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest = getTest();

        if (extentTest != null) {

            extentTest.log(Status.FAIL,
                    "Test failed");

            if (result.getThrowable() != null) {
                extentTest.log(Status.FAIL,
                        result.getThrowable());
            }

            captureScreenshot(result, extentTest);
        }
    }

    // Test skipped
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest extentTest = getTest();

        if (extentTest == null) {
            extentTest = getExtentReport().createTest(
                    result.getMethod().getMethodName());

            test.set(extentTest);
        }

        extentTest.log(Status.SKIP,
                "Test skipped");

        if (result.getThrowable() != null) {
            extentTest.log(Status.SKIP,
                    result.getThrowable());
        }
    }

    // Capture screenshot on failure
    private void captureScreenshot(
            ITestResult result, ExtentTest extentTest) {

        WebDriver driver = BaseClass.driver;

        if (driver == null) {
            extentTest.log(Status.WARNING,
                    "Screenshot unavailable: WebDriver is null");
            return;
        }

        try {
            File screenshotFolder = new File("screenshots");

            if (!screenshotFolder.exists()) {
                screenshotFolder.mkdirs();
            }

            String timestamp = new SimpleDateFormat(
                    "yyyy.MM.dd.HH.mm.ss").format(new Date());

            String screenshotName =
                    result.getMethod().getMethodName()
                    + "_" + timestamp + ".png";

            File destination = new File(
                    screenshotFolder, screenshotName);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            java.nio.file.Files.copy(
                    source.toPath(), destination.toPath());

            extentTest.addScreenCaptureFromPath(
                    destination.getAbsolutePath(),
                    "Failure Screenshot");

        } catch (Exception e) {
            extentTest.log(Status.WARNING,
                    "Unable to capture screenshot: "
                    + e.getMessage());
        }
    }

    // Test context finished
    @Override
    public void onFinish(ITestContext context) {

        ExtentReports report = extent;

        if (report != null) {
            report.flush();
        }

        System.out.println(
                "Extent Report Generated Successfully");
    }

    // Return current test
    public static ExtentTest getTest() {
        return test.get();
    }

    // Custom information log
    public static void info(String message) {
        ExtentTest extentTest = getTest();

        if (extentTest != null) {
            extentTest.log(Status.INFO, message);
        }
    }

    // Custom pass log
    public static void pass(String message) {
        ExtentTest extentTest = getTest();

        if (extentTest != null) {
            extentTest.log(Status.PASS, message);
        }
    }

    // Custom fail log
    public static void fail(String message) {
        ExtentTest extentTest = getTest();

        if (extentTest != null) {
            extentTest.log(Status.FAIL, message);
        }
    }
}
