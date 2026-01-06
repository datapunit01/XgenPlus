package utilities;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReport() {

        if (extent == null) {

            //  STEP 1: Create reports folder (CRITICAL for Jenkins)
            String reportDirPath = System.getProperty("user.dir") + "/reports";
            File reportDir = new File(reportDirPath);

            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            //  STEP 2: Define report path
            String reportPath = reportDirPath + "/ExtentReport.html";

            //  STEP 3: Create Spark Reporter
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
           // Jenkins-safe config
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Execution Results");
            spark.config().setEncoding("utf-8");

            // Prevent Jenkins CSP issues
            spark.config().setCss("");
            spark.config().setJs("");

            //  STEP 4: Attach report
            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Punit");
            extent.setSystemInfo(
                "Execution",
                ExecutionEnvironment.isRunningOnJenkins() ? "Jenkins" : "Local"
            );
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }
        return extent;
    }
}
