package com.orangeHRM.qa.pages;

import org.openqa.selenium.WebDriver;

public class GeneralInformationPage extends OrganizationPage{

	WebDriver driver;

	public GeneralInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean getcurrentUrl()
	{
		return driver.getCurrentUrl().contains("GeneralInformation");
	}

}
