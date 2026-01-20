package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	/*public WebDriver driver; If declared as instance variable, objects of BaseTest will hold their distinct copies of driver. 
	Since, we have not initialized instance variables, creating objects of BaseTest will invoke a default-automatically generated constructor 
	which will assign default values to instance variables. This will lead to copy of driver held by objects being initialized to null*/
	
	//public WebDriver driver; //static will associate it with class hence same instance will be shared across all objects of the class
	private ThreadLocal<WebDriver> tl=new ThreadLocal<>();
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(alwaysRun=true) //run always irrespective of which groups are included/excluded in the XML file
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException, URISyntaxException
	{
		logger=LogManager.getLogger(this.getClass());
		
		FileReader file=new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		String url=p.getProperty("appURL");
		
		String exeEnv=p.getProperty("execution_env");
		String hubUrl=p.getProperty("hubURL");
		
		if(exeEnv.equalsIgnoreCase("Remote")) 
		{
			if(br.equalsIgnoreCase("chrome")) {
				ChromeOptions options=new ChromeOptions();
				options.setPlatformName(os);
				tl.set(new RemoteWebDriver(new URI(hubUrl).toURL(),options));
				driver=tl.get();
				//driver=new RemoteWebDriver(new URI(hubUrl).toURL(),options);
			}
			else if(br.equalsIgnoreCase("firefox")) {
				FirefoxOptions options=new FirefoxOptions();
				options.setPlatformName(os);
				tl.set(new RemoteWebDriver(new URI(hubUrl).toURL(),options));
				driver=tl.get();
				//driver=new RemoteWebDriver(new URI(hubUrl).toURL(),options);
			}
		}
		
		if(exeEnv.equalsIgnoreCase("local")) 
		{
		switch(br.toLowerCase()){
			case "chrome": 
				tl.set(new ChromeDriver());
				driver=tl.get();
				break;  //driver=new ChromeDriver(); break;
			case "edge": 
				tl.set(new EdgeDriver());
				driver=tl.get();
				break;  //driver=new EdgeDriver(); break;
			case "firefox":
				tl.set(new FirefoxDriver());
				driver=tl.get();
				break;  //driver=new FirefoxDriver(); break;
			default: System.out.print("Invalid Browser"); return;	
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver=tl.get();
		driver.quit();
		tl.remove();
	}
	
	//RandomStringUtils.insecure().nextAlphabetic(5)
	public String randomAlphabetic(int len) {
		return (RandomStringUtils.insecure().nextAlphabetic(len));	
	}
	
	public String randomNumeric(int len) {
		return (RandomStringUtils.insecure().nextNumeric(len));	
	}
	
	public String randomAlphanumeric(int len) {
		return (RandomStringUtils.insecure().nextAlphanumeric(len));	
	}
	
	public String captureScreen(String tname, WebDriver driver) throws IOException {
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destPath=System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timestamp+".png";
		File target=new File(destPath);
		FileUtils.copyFile(source, target);
		return destPath;
	}
		
}


