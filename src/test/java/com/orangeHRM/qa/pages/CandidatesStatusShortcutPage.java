package com.orangeHRM.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CandidatesStatusShortcutPage extends CandidatesPage{

	WebDriver driver;
	public CandidatesStatusShortcutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean getcurrentUrl()
	{
		return driver.getCurrentUrl().contains("changeCandidateVacancyStatus");
		
	}
	
	By notesId = By.id("candidateVacancyStatus_notes");
	By shorlitBtnId = By.id("actionBtn");
	By cancelBtnId = By.id("cancelBtn");
	
	public WebElement notes()
	{
		return driver.findElement(notesId);
	}
	
	public WebElement shortListBtn()
	{
		return driver.findElement(shorlitBtnId);
	}
	
	public WebElement cancelBtn()
	{
		return driver.findElement(cancelBtnId);
	}
	
	public void shortlist(String option2)
	{
		if(option2.equals("Shortlist"))
		{
			shortListBtn().click();
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='addJobVacancy']/div[1]/h1")).isDisplayed());
			String text = driver.findElement(By.xpath("//*[@id='addJobVacancy']/div[1]/h1")).getText();
			Assert.assertEquals(text, "View Action History");
			driver.findElement(By.xpath("//*[@id='btnSave']")).click();
			driver.findElement(By.id("candidateVacancyStatus_notes")).sendKeys("Time to enter edit notes");
			driver.findElement(By.xpath("//*[@id='btnSave']")).click();
			driver.findElement(By.xpath("//*[@id='cancelBtn']")).click();
			String editStatus = driver.findElement(By.xpath("//*[@id='0']/span")).getText();
			System.out.println("Status changed to "+editStatus);
			driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewCandidates\"]")).click();
			}
			else if (option2.equals("back"))
				driver.findElement(By.id("cancelBtn")).click();
		
		
		
		
	}
}
