package testCases;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver ;
	public Logger logger ;	
	public Properties p ;

	@SuppressWarnings({ "deprecation" })
	@BeforeClass (groups = {"regression" , "sanity" , "master"})
	@Parameters({"os" , "browser"})
	public void setup(String os , String br) throws IOException {
		logger = LogManager.getLogger(this.getClass());


		// reading data from the config.properties
		FileReader datafile = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(datafile) ;


		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capability = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				capability.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac")) {
				capability.setPlatform(Platform.MAC);
			} 
			else if (os.equalsIgnoreCase("linux")) {
				capability.setPlatform(Platform.LINUX);
			} 
			else {
				throw new IllegalArgumentException("Invalid OS: " + os);
			}

			switch (br.toLowerCase()) {
			
			case "chrome" : capability.setBrowserName("chrome") ;
			break;
				
			case "edge": capability.setBrowserName("MicrosoftEdge");
			break;
				
			case "firefox": capability.setBrowserName("firefox");
			break;
				
			default:
				throw new IllegalArgumentException("Invalid browser: " + br);
				
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444"),capability);    //----------> use these web while run xml with docker 
		}

		//http://10.132.1.234:4444 -----> use these to run xml without docer 
		


		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch(br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case"firefox" :driver = new FirefoxDriver();break;
			default: System.out.println("invalid browser..."); return ;
			}
		}


		driver.get(p.getProperty("appurl"));           //reading the url form the config properties file 	

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;

	}

	@SuppressWarnings("deprecation")
	public String generaterandom_text() {
		String genraterandomestring = RandomStringUtils.randomAlphabetic(5);
		return genraterandomestring;
	}

	@SuppressWarnings("deprecation")
	public String generaterandom_pass() {

		String generatepass = RandomStringUtils.randomAlphanumeric(8) ;
		return generatepass;
	}

	public String captureScreenshot(String tname) {
		String timestamp = new SimpleDateFormat("yyyy-MM-DD__HH-mm-SS").format(new Date() ) ;
		TakesScreenshot ts = (TakesScreenshot) driver ;

		File hold = ts.getScreenshotAs(OutputType.FILE);

		String targetfilepath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";


		File store = new File(targetfilepath);

		hold.renameTo(store) ;
		return targetfilepath;

	}



	@AfterClass(groups = {"sanity" ,"regression" , "master"})
	public void closee() throws InterruptedException {
		Thread.sleep(4000);
		driver.quit();


	}



}
