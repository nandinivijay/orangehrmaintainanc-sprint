/*package com.orangeHRM.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DependentsPage extends MyInfoPage {
	WebDriver driver;

	public DependentsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean getcurrentUrl() {

		return driver.getCurrentUrl().contains("viewDependents");
	}

	By addDependent = By.xpath("//input[@id='btnAddDependent']");
	By dependentName = By.id("dependent_name");// By.xpath("//input[@class='formInputText']");
	By dependentRelType = By.id("dependent_relationshipType");
	By pleaseSpecifyId = By.id("dependent_relationship");
	By datePicker_Class = By.className("ui-datepicker-trigger");
	By Month = By.className("ui-datepicker-month");
	By Year = By.className("ui-datepicker-year");
	By Day = By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody");
	By dependentDob = By.id("dependent_dateOfBirth");
	By cancelDependent = By.id("btnCancel");
	By deleteDependent = By.id("delDependentBtn");
	By checkboxDependent = By.className("checkbox");
	By saveDependent = By.id("btnSaveDependent");
	// When we click on name it goes to edit dependent.
	By editDependent = By.xpath("//td[@class='dependentName']/a");
	By attachmentAddBtn = By.id("btnAddAttachment");
	By chooseFile = By.xpath("//input[@id='ufile']");
	By comments = By.id("txtAttDesc");

	public WebElement adddepButton()

	{
		return driver.findElement(addDependent);
	}

	public WebElement deldepButton()

	{
		return driver.findElement(deleteDependent);
	}

	public WebElement getdependentName_ID()

	{
		return driver.findElement(dependentName);
	}

	public WebElement getdependentDob_ID()

	{
		return driver.findElement(dependentDob);
	}

	public WebElement canceldepButton()

	{
		return driver.findElement(cancelDependent);
	}

	public WebElement savedepButton()

	{
		return driver.findElement(saveDependent);
	}

	public WebElement editdepButton()

	{
		return driver.findElement(editDependent);
	}

	public WebElement attachmentAddButton()
	{
		return driver.findElement(attachmentAddBtn);
	}
	
	public WebElement chooseFile()
	{
		return driver.findElement(chooseFile);
	}
	
	public WebElement comments()
	{
		return driver.findElement(comments);
	}
	
	public WebElement depRelType() {
		return driver.findElement(dependentRelType);
	}

	public void selRelType() {

		Select rel = new Select(depRelType());
//		List<WebElement> reltype = rel.getOptions();
		rel.selectByIndex(1);

	}

	public WebElement getpleaseSpecify_ID()

	{
		return driver.findElement(pleaseSpecifyId);
	}

	public WebElement getDatePicker() {

		return driver.findElement(datePicker_Class);
	}

	public WebElement selMonth() {

		return driver.findElement(Month);
	}

	public WebElement SelYear() {

		return driver.findElement(Year);
	}

	public Object selDate() {

		return driver.findElement(Day);
	}

	public WebElement getCheckboxAttatchment() {

		return driver.findElement(checkboxDependent);
	}

	public void pleaseSpecify(String rel) {
		Assert.assertTrue(getpleaseSpecify_ID().isDisplayed());
		getpleaseSpecify_ID().sendKeys(rel);

	}

}

*/

package com.orangeHRM.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DependentsPage extends MyInfoPage {
	WebDriver driver;

	public DependentsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean getcurrentUrl() {

		return driver.getCurrentUrl().contains("viewDependents");
	}

	By addDependent = By.xpath("//input[@id='btnAddDependent']");
	By dependentName = By.id("dependent_name");// By.xpath("//input[@class='formInputText']");
	By dependentRelType = By.id("dependent_relationshipType");
	By pleaseSpecifyId = By.id("dependent_relationship");
	By datePicker_Class = By.className("ui-datepicker-trigger");
	By Month = By.className("ui-datepicker-month");
	By Year = By.className("ui-datepicker-year");
	By Day = By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody");
	By dependentDob = By.id("dependent_dateOfBirth");
	By cancelDependent = By.id("btnCancel");
	By deleteDependent = By.id("delDependentBtn");
	By checkboxDependent = By.className("checkbox");
	By saveDependent = By.id("btnSaveDependent");
	// When we click on name it goes to edit dependent.
	By editDependent = By.xpath("//td[@class='dependentName']/a");
	
	
		
	By AddAttatchmentBtn_ID = By.id("btnAddAttachment");
	By cf = By.xpath("//*[@id='ufile']");
	By cmts = By.xpath("//textarea[@id='txtAttDesc']");
	By uploadBtn = By.xpath("//input[@id='btnSaveAttachment']");
	By cancelbtn = By.xpath("//input[@id='cancelButton']");
	By filecheckboxbtn = By.xpath("//input[@name='chkattdel[]']");
	By deletebtn = By.xpath("//input[@id='btnDeleteAttachment']");

	By editLink = By.xpath("//*[@id='tblAttachments']/tbody/tr/td[8]/a");

	By checkboxAllAttatchment = By.id("attachmentsCheckAll");
	
	public WebElement adddepButton()

	{
		return driver.findElement(addDependent);
	}

	public WebElement deldepButton()

	{
		return driver.findElement(deleteDependent);
	}

	public WebElement getdependentName_ID()

	{
		return driver.findElement(dependentName);
	}

	public WebElement getdependentDob_ID()

	{
		return driver.findElement(dependentDob);
	}

	public WebElement canceldepButton()

	{
		return driver.findElement(cancelDependent);
	}

	public WebElement savedepButton()

	{
		return driver.findElement(saveDependent);
	}

	public WebElement editdepButton()

	{
		return driver.findElement(editDependent);
	}

	public WebElement depRelType() {
		return driver.findElement(dependentRelType);
	}

	public void selRelType() {

		Select rel = new Select(depRelType());
//		List<WebElement> reltype = rel.getOptions();
		rel.selectByIndex(1);

	}

	public WebElement getpleaseSpecify_ID()

	{
		return driver.findElement(pleaseSpecifyId);
	}

	public WebElement getDatePicker() {

		return driver.findElement(datePicker_Class);
	}

	public WebElement selMonth() {

		return driver.findElement(Month);
	}

	public WebElement SelYear() {

		return driver.findElement(Year);
	}

	public Object selDate() {

		return driver.findElement(Day);
	}

	public WebElement getCheckboxAttatchment() {

		return driver.findElement(checkboxDependent);
	}

	public void pleaseSpecify(String rel) {
		Assert.assertTrue(getpleaseSpecify_ID().isDisplayed());
		getpleaseSpecify_ID().sendKeys(rel);

	}

	

	public WebElement addAttatchmentBtn() {

		return driver.findElement(AddAttatchmentBtn_ID);

	}

	public WebElement editLinkClick() {

		return driver.findElement(editLink);
	}

	public WebElement checkboxallAttatchment() {

		return driver.findElement(checkboxAllAttatchment);
	}

	public WebElement deleteButton() {

		return driver.findElement(deletebtn);
	}

	public WebElement fileCheckbox() {
		return driver.findElement(filecheckboxbtn);
	}

	public WebElement cancel() {
		return driver.findElement(cancelbtn);
	}

	public WebElement upload() {
		return driver.findElement(uploadBtn);
	}

	public WebElement chooseFile() {
		return driver.findElement(cf);

	}

	public WebElement comments() {
		return driver.findElement(cmts);
	}
}