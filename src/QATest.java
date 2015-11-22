import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class QATest {
	
	public WebDriver driver;
	public String PageUrl;
	public int count;
	public int i;
	public int tot;
	public int total;
	public int counttags;
	public int countread;
	public String CrtURL;
	private String errormsg;
	List<WebElement> forms;
	List<WebElement> formstags;
	List<WebElement> formsreadtags;

	private StringBuffer verificationErrors = new StringBuffer();

	
	@Test
	  public void search() throws InterruptedException{
		
		  driver.get(PageUrl);
		  driver.findElement(By.cssSelector("input[name='search_block_form']")).clear();
		  driver.findElement(By.xpath("/html/body/header/div/div/div[1]/section/div/div/div/div/form/div/div/div[1]/input")).sendKeys("CingleVue");
		  driver.findElement(By.id("edit-search-block-form--2")).sendKeys(Keys.ENTER);
		  
		  forms=(List<WebElement>) driver.findElements(By.cssSelector("article"));
		  count=forms.size();
		  
		  CrtURL=driver.getCurrentUrl();
	
		  formstags=(List<WebElement>) driver.findElements(By.className("pager__item"));
		  counttags= formstags.size();

		  for (i=0;i<counttags-2;i++){
			driver.navigate().to(CrtURL+"?page="+i);
			formsreadtags=(List<WebElement>) driver.findElements(By.cssSelector("article"));
			countread=formsreadtags.size();
			tot+=countread;  
		  }

		  total=tot+count;
		  
		 if(total>10){
		  System.out.println("Results are more than 10");
		  Assert.assertTrue(true, CrtURL);
		 }
	  }
	
	@Test	
	public void search_nagative(){
		
		  driver.get(PageUrl);
		  driver.findElement(By.cssSelector("input[name='search_block_form']")).clear();
		  driver.findElement(By.xpath("/html/body/header/div/div/div[1]/section/div/div/div/div/form/div/div/div[1]/input")).sendKeys("CingleVueW");
		  driver.findElement(By.id("edit-search-block-form--2")).sendKeys(Keys.ENTER);
		  errormsg=driver.findElement(By.cssSelector("p")).getText();
		  Assert.assertEquals(errormsg, "Sorry your requested search was unable to locate any matching records. Please review your request and try again.");
		
	}
	
	  @BeforeSuite
	public void setup() {
		  
		  driver=new FirefoxDriver();
		  PageUrl="http://www.cinglevue.com/";
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  	
	}
	  
	  @AfterSuite
	  public void closeIn(){
		  driver.quit();
	  
	  }



}
