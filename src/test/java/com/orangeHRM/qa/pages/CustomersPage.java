package com.orangeHRM.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomersPage extends ProjectInfoPage{

	WebDriver driver;

	public CustomersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean getcurrentUrl()
	{
		return driver.getCurrentUrl().contains("Customers");
	}
	
	By PrjInfo_AddBtn = By.id("btnAdd");
	By PrjInfo_DelBtn = By.id("btnDelete");
	By ProjInfo_CustName = By.id("addCustomer_customerName");
	By ProjInfo_CustDescrip = By.id("addCustomer_description");
	By ProjInfo_SaveBtn = By.id("btnSave");
	By ProjInfo_CancelBtn = By.id("btnCancel");
	By ProjInfo_EditBtn = By.id("btnSave");
	By ProjInfo_OKBtn = By.id("dialogDeleteBtn");
	By ProjInfo_DelAllChkBox = By.id("ohrmList_chkSelectAll");
	By ProjInfo_NoDelAllCancelBtn = By.xpath("//*[@id=\"deleteConfModal\"]/div[3]/input[2]");
	

	public WebElement projInfo_AddBtn() {

		return driver.findElement(PrjInfo_AddBtn);
	}
	
	public WebElement projInfo_DelBtn() {
		
		return driver.findElement(PrjInfo_DelBtn);
	}
	
     public WebElement projInfo_CustName() {
		
		return driver.findElement(ProjInfo_CustName);
     }	
     
     public WebElement projInfo_CustDescrip() {
 		
 		return driver.findElement(ProjInfo_CustDescrip);
      }	
     
     public WebElement projInfo_SaveBtn() {
  		
  		return driver.findElement(ProjInfo_SaveBtn);
       }	
     
     public WebElement projInfo_CancelBtn() {
   		
   		return driver.findElement(ProjInfo_CancelBtn);
   		}	
 
     public WebElement projInfo_EditBtn() {
    	 
    	 return driver.findElement(ProjInfo_EditBtn);
     }
     
     public WebElement projInfo_OKBtn() {
    	 
    	 return driver.findElement(ProjInfo_OKBtn);
     }
     
     public WebElement projInfo_DelAllChkBox() {
    	 
    	 return driver.findElement(ProjInfo_DelAllChkBox);
     }
     
     public WebElement ProjInfo_NoDelAllCancelBtn() {
    	 
    	 return driver.findElement(ProjInfo_NoDelAllCancelBtn);
     }
}

