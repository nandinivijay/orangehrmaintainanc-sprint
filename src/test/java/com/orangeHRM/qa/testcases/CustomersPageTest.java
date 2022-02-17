package com.orangeHRM.qa.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangeHRM.qa.base.BaseClass;
import com.orangeHRM.qa.base.BaseVerification;
import com.orangeHRM.qa.pages.CustomersPage;
import com.orangeHRM.qa.pages.ProjectInfoPage;
import com.orangeHRM.qa.pages.TimePage;
import com.orangeHRM.qa.testdata.ExcelReaderWithIndex;
import com.orangeHRM.qa.util.ActionsUtil;
import com.orangeHRM.qa.util.ColorValidationUtil;
import com.orangeHRM.qa.util.DropDownUtil;
import com.orangeHRM.qa.util.SynchronisationWaitsUtil;

public class CustomersPageTest extends BaseClass{
	
	TimePage timePage;
	ProjectInfoPage projectInfoPage;
	CustomersPage customersPage;
	@BeforeClass
	public void verifyCustomersPageNavigation()
	{
		login();
		BaseVerification baseVerification = new BaseVerification(driver);
		timePage = baseVerification.navigatingToTime();
		projectInfoPage = timePage.navigatingToProjectInfo();
		customersPage = projectInfoPage.navigatingToCustomers();
		Assert.assertTrue(customersPage.getcurrentUrl());
		System.out.println("Clicked Customers and asserted URL");
	}
	
	@Test(priority = 4)
	public void customersTesting()
	{
		System.out.println("Customers clicked");
	}

	
	//Checking colors for Add Del buttons
	
	@Test(priority=5)
	public void colorValidationForAddandDeleteButtonsInCustProjInfo()
	{
		 String addhex = ColorValidationUtil.GetColor(customersPage.projInfo_AddBtn());
		 System.out.println("add button color"+addhex);
		 Assert.assertEquals(addhex,"Green");
		     
		 String deletehex = ColorValidationUtil.GetColor(customersPage.projInfo_DelBtn());
	     System.out.println("Delete button color"+deletehex);
	     Assert.assertEquals(deletehex,"Red");
	     
	     Assert.assertNotEquals(addhex,deletehex);
	     System.out.println("add and delete button colors are different"); 
	   
	}      
		 
	
	@Test(priority = 6)
	public void mandatoryFieldValidationCustProjInfo
	() {
		customersPage.projInfo_AddBtn().click();
		customersPage.projInfo_SaveBtn().click();
		boolean flag;
		if (customersPage.projInfo_CustName().getAttribute("value").isEmpty()) {
			flag = true;
			System.out.println("Company Field Is Empty");
		} else {
			System.out.println("Company Field Is Not Empty");
			flag = false;
		}
		
		Assert.assertTrue(flag);
		System.out.println("All Mandatory Fields Are Tested Successfully");
		customersPage.projInfo_CancelBtn().click();;
		// driver.quit();
	} 
	
	
	//Add Customers in Proj Info
	
	@Test(priority=7)
	public void setcontextCustProjInfo(ITestContext context) {
	int	sheetnumber=39;
	context.setAttribute("index", sheetnumber);
	}
	
		
	@Test(dataProvider = "paramName",dataProviderClass=ExcelReaderWithIndex.class,priority=8 )
	public void verifyAddCustomerProjInfo(String name,String description) throws Exception
	{
		customersPage.projInfo_AddBtn().click();
		Thread.sleep(1000);
		
		customersPage.projInfo_CustName().sendKeys(name);
		System.out.println("Name :"+name);
		Thread.sleep(1000);
		
	    customersPage.projInfo_CustDescrip().sendKeys(description);
		System.out.println("Description : "+description);
		Thread.sleep(1000);
		
		customersPage.projInfo_SaveBtn().click();
		
	}
	
	
       //Table Validation
	
	  @Test(priority=8)  
		public void tableValidatingCustProjInfo() throws InterruptedException 
		{
			
		   List<WebElement> li = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/thead/tr/th"));
			int n = li.size();
			
			List<String> s = new ArrayList<String>();
			System.out.println("size of Columns : " + n);  
			
			for (int i = 0; i < n; i++) 
			{
				s.add(li.get(i).getText());
			}

			System.out.println("List String : " + s);
			System.out.println("No . of columns in a table are : " + n);
			Assert.assertEquals(3, n);

			for (int i = 1; i <= n-1 ; i++) 
			{
				WebElement header = driver.findElement(By.xpath("//table[@id='resultTable']/thead/tr/th[" + (i + 1) + "]"));
				System.out.print(header.getText());
				Thread.sleep(1000);
				if (header.getText().equalsIgnoreCase("Customer")) 
				{
					System.out.println(": is in the header");
					Assert.assertTrue(header.getText().contains("Customer"));
				} 
				else if (header.getText().equalsIgnoreCase("Description")) 
				{
					System.out.println(": is  in the header ");
					Assert.assertTrue(header.getText().contains("Description"));
				} 
				
			}
		}    


	    //Edit customer
	   
	  	@Test(priority=9)
		public void setcontextEditCustomerProjInfo(ITestContext context) {
		int	sheetnumber=40;
		context.setAttribute("index", sheetnumber);
		}
		
		@Test(dataProvider = "paramName" ,dataProviderClass=ExcelReaderWithIndex.class ,priority=10,dependsOnMethods = { "verifyAddCustomerProjInfo" })
		public void editCustProjInfo(String name1,String description1) throws Exception 
		{
			System.out.println("Customers clicked");
			
			SynchronisationWaitsUtil.implicitwait(12,TimeUnit.SECONDS, driver);
			System.out.println("i am here");
			System.out.println("Current Url of Proj Info_CustPage :" +customersPage.getcurrentUrl());
			driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[2]/td[2]/a")).click(); 
			customersPage.projInfo_EditBtn().click();
							 
			SynchronisationWaitsUtil.threadsleep(2000);
			
			customersPage.projInfo_CustName().clear();
			customersPage.projInfo_CustName().sendKeys(name1);
			
			customersPage.projInfo_CustDescrip().clear();	
			customersPage.projInfo_CustDescrip().sendKeys(description1);
			
		    customersPage.projInfo_SaveBtn().click();
			
			SynchronisationWaitsUtil.threadsleep(2000);
			
	        String custmr = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[2]/td[2]/a")).getText(); 
			  
			  System.out.println("Custmr : "+custmr +"Customer "+name1);
			  Assert.assertEquals(custmr, name1);
			  System.out.println("Customer Edited Successfully");
					
			}  
		
	/*	//Deleting two customer's
		
		int finalCustomersnumRows;
		@Test(priority = 11, dependsOnMethods = { "verifyAddCustomerProjInfo" })
		public void deletingSingleanddouble_Customer() throws InterruptedException {
			
			if (customersPage.projInfo_DelBtn().isDisplayed())
				
			{
				List<WebElement> rows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr")); 
				Thread.sleep(2000);
				int numRows = rows.size();
				System.out.println("No. of Rows Before Deleting : " + numRows);
				
				driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[2]/td[1]")).click();
				customersPage.projInfo_DelBtn().click();
				Thread.sleep(2000);
				customersPage.projInfo_OKBtn().click();   
				
			    Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[7]/td[1]")).click();
				customersPage.projInfo_DelBtn().click();
				Thread.sleep(2000);
				customersPage.projInfo_OKBtn().click();
				
				List<WebElement> finalRows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
								 
				finalCustomersnumRows = finalRows.size();  
				Thread.sleep(1000);
				System.out.println("No. of Rows After Deleting : " + finalCustomersnumRows);
				Assert.assertEquals(finalCustomersnumRows, numRows - 2);
			} else {
				Assert.assertFalse(customersPage.projInfo_DelBtn().isDisplayed());
				System.out.println("No Rows Displayed");
			}
		}    */
		
		
		
			//Deleting Single Customer
			
			int finalCustomersnumRows;
			@Test(priority = 11, dependsOnMethods = { "verifyAddCustomerProjInfo" })
			public void deletingSingleanddouble_Customer() throws InterruptedException {
				
				if (customersPage.projInfo_DelBtn().isDisplayed())
					
				{
					List<WebElement> rows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr")); 
					Thread.sleep(2000);
					int numRows = rows.size();
					System.out.println("No. of Rows Before Deleting : " + numRows);
					
					driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[2]/td[1]")).click();
					customersPage.projInfo_DelBtn().click();
					Thread.sleep(2000);
					customersPage.projInfo_OKBtn().click();   
					
				  	List<WebElement> finalRows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
									 
					finalCustomersnumRows = finalRows.size();  
					Thread.sleep(1000);
					System.out.println("No. of Rows After Deleting : " + finalCustomersnumRows);
					Assert.assertEquals(finalCustomersnumRows, numRows - 1);
				} else {
					Assert.assertFalse(customersPage.projInfo_DelBtn().isDisplayed());
					System.out.println("No Rows Displayed");
				}
			}    
			
				
		//Del All but instead press cancel button
			
		@Test(priority = 12)
		public void deletingall_CustProjInfo() throws InterruptedException {
			
			if (finalCustomersnumRows > 0) {
				
				List<WebElement> rowsCustList = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
				int numCustomerRows = rowsCustList.size();
				System.out.println("No. of Rows Before Deleting All Customers : " + rowsCustList); 
				
				customersPage.projInfo_DelAllChkBox().click();
				Thread.sleep(1000);
				
				customersPage.projInfo_DelBtn().click();
				Thread.sleep(1000);
				
				customersPage.ProjInfo_NoDelAllCancelBtn().click();
				Thread.sleep(1000);

			} else {

				System.out.println("No rows Displayed");
			}
		} 
		
		
		@Test(priority=13)
		public void ascendingOrderSorting_CustprojInfo() {
			
			List<WebElement> li = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]"));
			int n = li.size();
			System.out.println(" Total Number of Customers:" +n);
			
			List<String> s = new ArrayList<String>();
			
			for(int i=0; i < n; i++)
			{
				s.add(li.get(i).getText());
				
			}
			System.out.println("Names of Customers before Ascending sort:" + s +"\n");
			
			Collections.sort(s);
			
			System.out.println("Asc Sorted Names of Customers" + s);
			
			//Ascending order sort on Customer name in Proj Info.
			
			driver.findElement(By.xpath("//*[@id=\"resultTable\"]/thead/tr/th[2]/a")).click();
			
			List<WebElement> li1 = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]"));
			int n1 = li1.size();
			System.out.println("No.of Customers after clicking the Customer Header" + n1);
			
			List<String> s1 = new ArrayList<String>();
			
			for(int i=0; i<n ; i++)
			{
				s1.add(li1.get(i).getText());
			}
			System.out.println("Names of Customers after clicking the customer header" + s1);
			
			Assert.assertEquals(s,s1);
			System.out.println(" Ascending Order Sort On Customer-names In Proj Info is Validated Successfully");
			
		}
		
		    @Test(priority=14)
		    public void descendingOrderSorting_CustprojInfo() {
		    	
		    driver.findElement(By.xpath("//*[@id=\"resultTable\"]/thead/tr/th[2]/a")).click();
		   						
		  	List<WebElement> li = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]"));
			int n = li.size();
			System.out.println("Total Number of Customers:" +n);
				
			List<String> s = new ArrayList<String>();
				
			for(int i=0; i < n; i++)
			{
				s.add(li.get(i).getText());
				
			}
			System.out.println("Names of Customers before Descending sort:" + s +"\n");
			
			Collections.sort(s,Collections.reverseOrder());
				
			System.out.println("Des Sorted Names of Customers" + s);
				
			 // Descending order sort Check
			
          	List<WebElement> li1 = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]"));
			int n1 = li1.size();
			System.out.println("No.of Customers after clicking the Customer Header twice" + n1);
			
			List<String> s1 = new ArrayList<String>();
			
			for(int i=0; i<n ; i++)
			{
				s1.add(li1.get(i).getText());
			}
			System.out.println("Names of Customers after clicking the customer header twice" + s1);
			
		    Collections.sort(s1, Collections.reverseOrder());
		    Assert.assertEquals(s, s1);
		    
		    System.out.println("Descending Order Sort On Customer-names In Proj Info is Validated Successfully");
		}
}
