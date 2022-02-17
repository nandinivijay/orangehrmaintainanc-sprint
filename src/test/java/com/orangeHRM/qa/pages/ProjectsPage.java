package com.orangeHRM.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectsPage extends ProjectInfoPage{

	WebDriver driver;

	public ProjectsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean getcurrentUrl()
	{
		return driver.getCurrentUrl().contains("Projects");
	}

	
	By CustNameProjSearch_ProjInfo = By.id("searchProject_customer");
	By ProjectprojSearch_ProjInfo = By.id("searchProject_project");
	By ProjectAdminprojSearch_projInfo = By.id("searchProject_projectAdmin");
	By searchbtnProj_projInfo = By.id("btnSearch");
	By resetbtnProj_projInfo = By.id("btnReset");
	By addbtnProj_ProjInfo = By.id("btnAdd");
	By delbtnProj_ProjInfo = By.id("btnDelete");
	By addCustNameProjects_ProjInfo = By.id("addProject_customerName");
	By addNameProjects_projInfo = By.id("addProject_projectName");
	By addprojAdminProjects_ProjInfo = By.id("addProject_projectAdmin_1");
	By addDescripProjects_ProjInfo = By.id("addProject_description");
	By savebtnProj_ProjInfo = By.id("btnSave");
	By cancelbtnProj_ProjInfo = By.id("btnCancel");
	By addCustomerlinkProj_ProjInfo = By.xpath("//a[text()='Add Customer']");
	By editbtnProj_ProjInfo =By.id("btnSave");
	By okbtnDelDialogBox = By.id("dialogDeleteBtn");
	By delAllCheckBox = By.id("ohrmList_chkSelectAll");   
	By cnclbtnDialogBoxToDelAll = By.xpath("//*[@id=\"deleteConfModal\"]/div[3]/input[2]");
	By activitiesBox = By.id("search-results");
	By nameActivitySection = By.id("addProjectActivity_activityName");
	By saveBtnActivitySection = By.id("btnActSave");
	By delActivitySingleDelete = By.id("ohrmList_chkSelectRecord_86");
	By copyFromActivity = By.id("btnCopy");
	By projNameActivityDialogBox = By.id("projectName");
	By copyBtnActivityDialogBox = By.id("btnCopyDig");
	
	
	public WebElement custNameProjSearch_ProjInfo() {
		 return driver.findElement(CustNameProjSearch_ProjInfo);
	}	
	
	public WebElement projectProjSearch_ProjInfo() {
		 return driver.findElement(ProjectprojSearch_ProjInfo);
	}	
	
	public WebElement projectAdminProjSearch_ProjInfo() {
		 return driver.findElement(ProjectAdminprojSearch_projInfo);
	}	
	
	public WebElement searchBtnProj_ProjInfo() {
		 return driver.findElement(searchbtnProj_projInfo);
	}	
	
	public WebElement resetBtnProj_ProjInfo() {
		 return driver.findElement(resetbtnProj_projInfo);
	}
	
	public WebElement addBtnProj_ProjInfo() {
		 return driver.findElement(addbtnProj_ProjInfo);
	}
	
	public WebElement delBtnProj_ProjInfo() {
		 return driver.findElement(delbtnProj_ProjInfo);
	}
	
	public WebElement addCustNameProjects_ProjInfo() {
		 return driver.findElement(addCustNameProjects_ProjInfo);
	}
	
	public WebElement addNameProjects_ProjInfo() {
		 return driver.findElement(addNameProjects_projInfo);
	}
	
	public WebElement addProjAdminProjects_ProjInfo () {
		 return driver.findElement(addprojAdminProjects_ProjInfo);
	}
	
	public WebElement addDescripProjects_ProjInfo () {
		 return driver.findElement(addDescripProjects_ProjInfo);
	}
	
	public WebElement saveBtnProj_ProjInfo() {
		 return driver.findElement(savebtnProj_ProjInfo);
	}
	
	public WebElement cancelBtnProj_ProjInfo() {
		 return driver.findElement(cancelbtnProj_ProjInfo);
	}
	
	public WebElement addCustomerlinkProj_ProjInfo() {
		 return driver.findElement(addCustomerlinkProj_ProjInfo);
	}
	
	public WebElement editBtnProj_ProjInfo() {
		 return driver.findElement(editbtnProj_ProjInfo);
	}
	
	public WebElement  okbtnDelDialogBox() {
		 return driver.findElement(okbtnDelDialogBox);
	}
	
	public WebElement delAllCheckBox() {
		return driver.findElement(delAllCheckBox);
	}
	
	public WebElement cnclbtnDialogBoxToDelAll() {
		return driver.findElement(cnclbtnDialogBoxToDelAll);
	}
	
	public WebElement activitiesBox() {
		return driver.findElement(activitiesBox);
	}
	
	public WebElement nameActivitySection() {
	   return driver.findElement(nameActivitySection);
	}
	
	public WebElement saveBtnActivitySection() {
	   return driver.findElement(saveBtnActivitySection);
	}
	
	public WebElement delActivitySingleDelete() {
		 return driver.findElement(delActivitySingleDelete);
	}
	
	public WebElement copyFromActivity() {
		 return driver.findElement(copyFromActivity);
	}
	
	public WebElement projNameActivityDialogBox () {
		 return driver.findElement(projNameActivityDialogBox);
	}	
	
	public WebElement copyBtnActivityDialogBox() {
		 return driver.findElement(copyBtnActivityDialogBox);
	}	
	
}

