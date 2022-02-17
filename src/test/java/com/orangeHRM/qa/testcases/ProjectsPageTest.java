package com.orangeHRM.qa.testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangeHRM.qa.base.BaseClass;
import com.orangeHRM.qa.base.BaseVerification;
import com.orangeHRM.qa.pages.ProjectInfoPage;
import com.orangeHRM.qa.pages.ProjectsPage;
import com.orangeHRM.qa.pages.TimePage;
import com.orangeHRM.qa.testdata.ExcelReaderWithIndex;
import com.orangeHRM.qa.util.ColorValidationUtil;
import com.orangeHRM.qa.util.SynchronisationWaitsUtil;

public class ProjectsPageTest extends BaseClass{
	TimePage timePage;
	ProjectInfoPage projectInfoPage;
	ProjectsPage projectsPage;
	@BeforeClass
	public void verifyProjectsPageNavigation()
	{
		login();
		BaseVerification baseVerification = new BaseVerification(driver);
		timePage = baseVerification.navigatingToTime();
		projectInfoPage = timePage.navigatingToProjectInfo();
		projectsPage = projectInfoPage.navigatingToProjects();
		Assert.assertTrue(projectsPage.getcurrentUrl());
		System.out.println("Clicked Projects and asserted URL");
	}
	
	@Test(priority = 3)
	public void projectsTesting()
	{
		System.out.println("Projects clicked");
	}
	
		
	//Checking colors for Add Del buttons
	
	@Test(priority=4)
	public void colorValidationForAddandDeleteButtonsInProjectsProjInfo()
	{
		 String addhex = ColorValidationUtil.GetColor(projectsPage.addBtnProj_ProjInfo());
		 System.out.println("add button color"+addhex);
		 Assert.assertEquals(addhex,"Green");
		     
		 String deletehex = ColorValidationUtil.GetColor(projectsPage.delBtnProj_ProjInfo());
	     System.out.println("Delete button color"+deletehex);
	     Assert.assertEquals(deletehex,"Red");
	     
	     Assert.assertNotEquals(addhex,deletehex);
	     System.out.println("add and delete button colors are different"); 
	   
	}      
		 
	
	@Test(priority = 5)
	public void mandatoryFieldValidationProjectsProjInfo
	() {
		projectsPage.addBtnProj_ProjInfo().click();
		projectsPage.saveBtnProj_ProjInfo().click();
		
		boolean flag;
		
		if (projectsPage.addCustNameProjects_ProjInfo().getAttribute("value").isEmpty()) {
			flag = true;
			System.out.println("Customer Name Field for Projects In ProjInfo Is Empty");
		} else {
			System.out.println("Company Name Field for Projects In ProjInfo Is Not Empty");
			flag = false;
		}
		
		if (projectsPage.addNameProjects_ProjInfo().getAttribute("value").isEmpty()) {
			flag = true;
			System.out.println("Name Field for Projects In ProjInfo Is Empty");
		} else {
			System.out.println("Name Field for Projects In ProjInfo Is Not Empty");
			flag = false;
		}
		
		Assert.assertTrue(flag);
		System.out.println("All Mandatory Fields Are Tested Successfully");
		projectsPage.cancelBtnProj_ProjInfo().click();
		
	} 
	
	
	//Add Project in Proj-Info under Time Tab
	
	@Test(priority=6)
	public void setcontextProjectsProjInfo(ITestContext context) {
	int	sheetnumber=41;
	context.setAttribute("index", sheetnumber);
	}
	
		
	@Test(dataProvider = "paramName",dataProviderClass=ExcelReaderWithIndex.class,priority=7 )
	public void verifyAddProjectsProjInfo(String custname,String name,String projAdmin,String description) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
		
		projectsPage.addBtnProj_ProjInfo().click();
		Thread.sleep(1000);
		
        projectsPage.addCustNameProjects_ProjInfo().sendKeys(custname);
       // System.out.println("Customer Name :" + custname);
       List<WebElement> list = driver.findElements(By.xpath("//div[@class='ac_results']//li"));
	 		
		int total = list.size();
		//Thread.sleep(2000);	
		System.out.println("Total List of Customer Names := " + total);
		
		try
		{
		
		  for(int i=0;i<total;i++)	
		{
			if(list.get(i).getText().contains("Training"))
			{
				list.get(i).click();
				break;
			} 
		}	
		}
		
		catch(StaleElementReferenceException e)
		{
			list = driver.findElements(By.xpath("//div[@class='ac_results']//li"));
			 
			for(int i=0;i<total;i++)
		{
			if(list.get(i).getText().contains("Training"))
			{
				
				list.get(i).click();
				break;
			} 
		}	
		}
			
		        
		projectsPage.addNameProjects_ProjInfo().sendKeys(name);
		System.out.println("Name :"+name);
		Thread.sleep(1000);
		
		projectsPage.addProjAdminProjects_ProjInfo().sendKeys(projAdmin);
		Thread.sleep(1000);
		
		 List<WebElement> list1 = driver.findElements(By.xpath("//div[@class='ac_results']//li"));
	 		
			int total1 = list1.size();
			//Thread.sleep(2000);	
			System.out.println("Total List of Project Admins are := " + total1);
			
			try
			{
			
			  for(int j=0;j<total1;j++)	
			{
				if(list1.get(j).getText().contains("Ora"))
				{
					Thread.sleep(1000);
					list1.get(j).click();
					break;
				} 
			}	
			}
			
			catch(StaleElementReferenceException e)
			{
				list1 = driver.findElements(By.xpath("//div[@class='ac_results']//li"));
				 
				for(int j=0;j<total1;j++)
			{
				if(list1.get(j).getText().contains("Ora"))
				{
					
					list1.get(j).click();
					break;
				} 
			}	
			}
				
			
		System.out.println("Project Admin :"+ projAdmin);
		Thread.sleep(2000);
		
		projectsPage.addDescripProjects_ProjInfo().sendKeys(description);
		System.out.println("Description :" + description);
		Thread.sleep(2000);
		
		projectsPage.saveBtnProj_ProjInfo().click();
		Thread.sleep(1000);
	}
	
	
       //Table Validation
	
	  @Test(priority=8)  
		public void tableValidatingProjectsProjInfo() throws InterruptedException 
		{
			
		  // List<WebElement> li = driver.findElements(By.xpath("//*[@id=\"resultTable\"]/thead/tr/th"));
		  List<WebElement> li = driver.findElements(By.xpath("//*[@id=\"frmList_ohrmListComponent\"]/div[4]/table/thead/tr/th"));
			int n = li.size();
			Thread.sleep(1000);
			
			List<String> s = new ArrayList<String>();
			System.out.println("size of Columns : " + n);  
			
			for (int i = 0; i < n; i++) 
			{
				s.add(li.get(i).getText());
			}

			System.out.println("List String : " + s);
			System.out.println("No . of columns in a table are : " + n);
			Assert.assertEquals(2, n);

			for (int i = 1; i <= n-1 ; i++) 
			{
				WebElement header = driver.findElement(By.xpath("//table[@id='resultTable']/thead/tr/th[" + (i + 1) + "]"));
				System.out.print(header.getText());
				Thread.sleep(1000);
				
				if (header.getText().equalsIgnoreCase("Customer Name")) 
				{
					System.out.println(": is in the header");
					Assert.assertTrue(header.getText().contains("Customer Name"));
				} 
				
				else if (header.getText().equalsIgnoreCase("Project")) 
				{
					System.out.println(": is in the header");
					Assert.assertTrue(header.getText().contains("Project"));
				} 
				
				else if (header.getText().equalsIgnoreCase("Project Admin")) 
				{
					System.out.println(": is  in the header ");
					Assert.assertTrue(header.getText().contains("Project Admin"));
				} 
				
			}
		}    


	    //Edit Project in Proj-Info under Time Tab
	   
	  	@Test(priority=9)
		public void setcontextEditProjectsProjInfo(ITestContext context) {
		int	sheetnumber=42;
		context.setAttribute("index", sheetnumber);
		}
		
		@Test(dataProvider = "paramName" ,dataProviderClass=ExcelReaderWithIndex.class ,priority=10,dependsOnMethods = { "verifyAddProjectsProjInfo" })
		public void editProjectsProjInfo(String name1) throws Exception 
		{
			System.out.println("Projects under ProjInfo clicked");
			
			SynchronisationWaitsUtil.implicitwait(12,TimeUnit.SECONDS, driver);
			System.out.println("i am here");
			System.out.println("Current Url of Projects_ProjInfo :" +projectsPage.getcurrentUrl());
			
			projectsPage.editBtnProj_ProjInfo().click();
			
			SynchronisationWaitsUtil.threadsleep(2000);
			
			projectsPage.addNameProjects_ProjInfo().clear();
			projectsPage.addNameProjects_ProjInfo().sendKeys(name1);
			
			projectsPage.saveBtnProj_ProjInfo().click();
			
			SynchronisationWaitsUtil.threadsleep(2000);
			
			projectsPage.cancelBtnProj_ProjInfo().click();
			SynchronisationWaitsUtil.threadsleep(1000);
			
	        String projectField = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[9]/td[3]/a")).getText(); 
	        Thread.sleep(1000);
			  
			  System.out.println("Project : "+projectField +"NameField "+name1);
			  Assert.assertEquals(projectField, name1);
			  System.out.println("Name Field in Projects_ProjInfo has been Edited Successfully");
					
			}  
		
		
		//AddProjectActivity under Activities as ActivityName
		
		@Test(priority = 11)
		public void setcontextActivitiesProjectsProjInfo(ITestContext context) {
		int	sheetnumber=43;
		context.setAttribute("index", sheetnumber);
		}
			
			@Test(dataProvider = "paramName" ,dataProviderClass=ExcelReaderWithIndex.class ,priority=12)
			public void activitiesInProjects_ProjInfo(String name2) throws Exception 
			{
				
				driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[9]/td[3]/a")).click(); 
				
				boolean activitiesBoxDisplayed = projectsPage.activitiesBox().isDisplayed();
				Assert.assertTrue(activitiesBoxDisplayed, "Activity Box is Displayed");
				System.out.println("Activities Box in Projects under Edit Button is Displayed" +"\n");
				
				projectsPage.addBtnProj_ProjInfo().click();
				projectsPage.nameActivitySection().sendKeys(name2);
				projectsPage.saveBtnActivitySection().click();
				SynchronisationWaitsUtil.threadsleep(1000);
				
				SynchronisationWaitsUtil.threadsleep(1000);
				String nameActivityfield = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a")).getText(); 
			    Thread.sleep(1000);
					  
				System.out.println("Name_ActivitySection : "+nameActivityfield +"NameField "+name2);
				Assert.assertEquals(nameActivityfield, name2);
				System.out.println("Name Field in Activities_ProjInfo are Equal");
				
			}
					
			
			// Copying From Activites in Projects under ProjInfo
			
			@Test(priority = 13)
			public void copyFromActivityProjInfo() throws InterruptedException {
				
				projectsPage.copyFromActivity().click();
				Thread.sleep(2000);
				projectsPage.projNameActivityDialogBox().sendKeys("Fre");
				System.out.println("Fresh Books software LTD has been selected");
			    Thread.sleep(1000);			       
			    List<WebElement> listActy = driver.findElements(By.xpath("//div[@class='ac_results']//li"));
					
				int totalActy = listActy.size();
				Thread.sleep(2000);	
				System.out.println(" Project Names total := " + totalActy);
					
					try
					{
					
					  for(int i=0;i<totalActy;i++)	
					{
						if(listActy.get(i).getText().contains("Fresh Books"))
						{
							listActy.get(i).click();
							Thread.sleep(1000);
							break;
						} 
					}	
					}
					
					catch(StaleElementReferenceException e)
					{
						listActy = driver.findElements(By.xpath("//div[@class='ac_results']//li"));
						 
						for(int i=0;i<totalActy;i++)
					{
						if(listActy.get(i).getText().contains("Fresh Books"))
						{
							listActy.get(i).click();
							break;
						} 
					}	
						
					}
					
					Thread.sleep(1000);
					projectsPage.copyBtnActivityDialogBox().click();
									
			}
			
			// Verify ActivitiesName is Same as EditPropertyActivityname
			
			@Test(priority = 14)
			public void verifyActyNameandEditProjActy() throws InterruptedException {
				
				driver.findElement(By.xpath("//*[@id=\"frmList_ohrmListComponent\"]/div[4]/table/tbody/tr[1]/td[2]/a")).click();
				Thread.sleep(1000);
				
				WebElement actyNameActySection = driver.findElement(By.xpath("//*[@id=\"frmList_ohrmListComponent\"]/div[4]/table/tbody/tr[1]/td[2]/a"));
				Assert.assertEquals(actyNameActySection.getText(),"Bug Fixes" );
				Thread.sleep(1000);
				
				String nameEditPropActySection = projectsPage.nameActivitySection().getText();
				System.out.println("Name :" +nameEditPropActySection );
				//Assert.assertEquals(nameEditPropActySection, "Bug Fixes");
				Thread.sleep(1000);
				
				System.out.println("Name Field in Edit Property Activity is same as the Activity link clicked ");
			}
			
			
			//EditProjectActivityName Edit the NameField which reflects ActivitiesName and Edit's Successfully
			
			@Test(priority = 15)
			public void setcontextEditActivitiesProjectsProjInfo(ITestContext context) {
			int	sheetnumber=44;
			context.setAttribute("index", sheetnumber);
			}
				
				@Test(dataProvider = "paramName" ,dataProviderClass=ExcelReaderWithIndex.class ,priority=16)
				public void editProjectActivityInProjects_ProjInfo(String name3) throws Exception 
				{
					
					projectsPage.nameActivitySection().clear();
					projectsPage.nameActivitySection().sendKeys(name3);
					projectsPage.saveBtnActivitySection().click();
					
					String activityNameActivitiesSection = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a")).getText(); 
				    Thread.sleep(1000);
						  
					System.out.println("ActivityName_ActivitySection : "+ activityNameActivitiesSection +"NameInEditProjectActivity "+name3);
					Assert.assertEquals(activityNameActivitiesSection, name3);
					System.out.println("Activity Name in Activities section is Edited successfully");
					
					//Thread.sleep(1000);
					//projectsPage.cancelBtnProj_ProjInfo().click();
								
					
				}
				
				
				//Deleting a Activity in Proj-Info
				
				int finalActivitynumRows;
				@Test(priority = 17)
				public void deletingSingleActivityInProjInfo() throws InterruptedException {
					
					if (projectsPage.delBtnProj_ProjInfo().isDisplayed())
						
					{
						List<WebElement> rowsActivity = driver.findElements(By.xpath("//*[@id=\"tableWrapper\"]/table/tbody/tr")); 
						Thread.sleep(2000);
						int numRowsActivity = rowsActivity.size();
						System.out.println("No. of Rows in Activity Section Before Deleting : " + numRowsActivity);
						
						driver.findElement(By.xpath("//*[@id=\"tableWrapper\"]/table/tbody/tr[4]/td[1]")).click();
						projectsPage.delBtnProj_ProjInfo().click();
						Thread.sleep(2000);
						projectsPage.okbtnDelDialogBox().click();  
						
						List<WebElement> finalRowsAct = driver.findElements(By.xpath("//*[@id=\"tableWrapper\"]/table/tbody/tr"));
						finalActivitynumRows = finalRowsAct.size();  
						Thread.sleep(1000);
						System.out.println("No. of Rows After Deleting Activity : " + finalActivitynumRows);
						Assert.assertEquals(finalActivitynumRows, numRowsActivity - 1);
					} else {
						Assert.assertFalse(projectsPage.delBtnProj_ProjInfo().isDisplayed());
						System.out.println("No Rows Displayed");
					}
					
					//Thread.sleep(1000);
					//projectsPage.cancelBtnProj_ProjInfo().click();
				}  
				
				
				//Delete All Activiteis
				
				@Test(priority = 18)
				public void deletingallActivities_ProjectsProjInfo() throws InterruptedException {
					
					if (finalActivitynumRows> 0) {
						
						List<WebElement> rowsActyList = driver.findElements(By.xpath("//*[@id=\"tableWrapper\"]/table/tbody/tr"));
						int numActyRows = rowsActyList.size();
						System.out.println("No. of Rows Before Deleting All Projects : " + rowsActyList); 
						
						projectsPage.delAllCheckBox().click();
						Thread.sleep(1000);
						
						projectsPage.delBtnProj_ProjInfo().click();
						Thread.sleep(1000);
						
						projectsPage.okbtnDelDialogBox().click();  
						
						//projectsPage.cnclbtnDialogBoxToDelAll().click();
						//Thread.sleep(1000);

					} else {

						System.out.println("No rows Displayed");
					}
					
					projectsPage.cancelBtnProj_ProjInfo().click();
				} 
	
		//Deleting a Project in Proj-Info
		
		int finalProjectsnumRows;
		@Test(priority = 19, dependsOnMethods = { "verifyAddProjectsProjInfo" })
		public void deletingSingleProjectInProjInfo() throws InterruptedException {
			
			if (projectsPage.delBtnProj_ProjInfo().isDisplayed())
				
			{
				List<WebElement> rows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr")); 
				Thread.sleep(2000);
				int numRows = rows.size();
				System.out.println("No. of Rows Before Deleting : " + numRows);
				
				driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[9]/td[1]")).click();
				projectsPage.delBtnProj_ProjInfo().click();
				Thread.sleep(2000);
				projectsPage.okbtnDelDialogBox().click();  
				
				List<WebElement> finalRows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
				Thread.sleep(1000);
				finalProjectsnumRows = finalRows.size();  
				Thread.sleep(1000);
				System.out.println("No. of Rows After Deleting : " + finalProjectsnumRows);
				Assert.assertEquals(finalProjectsnumRows, numRows - 1);
			} else {
				Assert.assertFalse(projectsPage.delBtnProj_ProjInfo().isDisplayed());
				System.out.println("No Rows Displayed");
			}
		} 
		
		
		//Del All but instead press cancel button
			
		@Test(priority = 20)
		public void deletingall_ProjectsProjInfo() throws InterruptedException {
			
			if (finalProjectsnumRows > 0) {
				
				List<WebElement> rowsProjList = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
				int numProjectRows = rowsProjList.size();
				System.out.println("No. of Rows Before Deleting All Projects : " + rowsProjList); 
				
				projectsPage.delAllCheckBox().click();
				Thread.sleep(1000);
				
				projectsPage.delBtnProj_ProjInfo().click();
				Thread.sleep(1000);
				
				projectsPage.cnclbtnDialogBoxToDelAll().click();
				Thread.sleep(1000);

			} else {

				System.out.println("No rows Displayed");
			}
		} 
		  
	
	
}

