package com.orangeHRM.qa.testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangeHRM.qa.base.BaseClass;
import com.orangeHRM.qa.base.BaseVerification;
import com.orangeHRM.qa.pages.RecruitmentPage;
import com.orangeHRM.qa.pages.VacanciesPage;
import com.orangeHRM.qa.testdata.ExcelReaderWithIndex;
import com.orangeHRM.qa.util.ColorValidationUtil;
import com.orangeHRM.qa.util.DropDownUtil;
import com.orangeHRM.qa.util.SynchronisationWaitsUtil;

public class VacanciesPageTest extends BaseClass {
	RecruitmentPage recruitmentPage;
	VacanciesPage vacanciesPage;

	@BeforeClass
	public void verifyVacanciesPageNavigation() {
		login();
		BaseVerification baseVerification = new BaseVerification(driver);
		recruitmentPage = baseVerification.navigatingToRecruitment();
		vacanciesPage = recruitmentPage.navigatingToVacancies();
		Assert.assertTrue(vacanciesPage.getcurrentUrl());
		System.out.println("Clicked Vacancies and asserted URL" + "\n");
	}

	@Test(priority = 2)
	public void vacanciesTesting() {
		System.out.println("Vacancies clicked" + "\n");
	}

	@Test(priority = 3)
	public void vacancy_HeaderValidate() {

		boolean vacTableHdr = driver.findElement(By.id("srchVacancy")).isDisplayed();
		Assert.assertTrue(vacTableHdr);
		System.out.println("Vacancy Header Table is Validated" + "\n");

	}

	@Test(priority = 4)
	public void vacancy_History_HdrValidate() {
		boolean vacHistoryHdrValidate = driver.findElement(By.id("search-results")).isDisplayed();
		Assert.assertTrue(vacHistoryHdrValidate);
		System.out.println("Vacancy History Header Table is Validated" + "\n");
	}

	@Test(priority = 5)
	public void setcontext(ITestContext context) {
		int sheetnumber = 29;
		context.setAttribute("index", sheetnumber);
	}

	@Test(dataProvider = "paramName", dataProviderClass = ExcelReaderWithIndex.class, priority = 6)
	public void resetVacancies(String jobtitle, String vacancy, String hmanager, String status, String defaultopt)
			throws Exception {
		SynchronisationWaitsUtil.threadsleep(5000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobtitle);
		System.out.println(jobtitle + " is Selected");
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), vacancy);
		System.out.println(vacancy + " is Selected");
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectHiringManager(), hmanager);
		System.out.println(hmanager + " is Selected");
		SynchronisationWaitsUtil.threadsleep(5000);
		// Select status1 = new Select(vacanciesPage.SelectStatus());
		// status1.selectByVisibleText(status);
		// DropDownUtil.selectbytext(vacanciesPage.SelectStatus(), status);
		// System.out.println(status + " is Selected");
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SearchButton().click();
		vacanciesPage.ResetButton().click();

		// DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), defaultopt);
		Select select1 = new Select(vacanciesPage.SelectJobTitle());
		String sel1 = select1.getFirstSelectedOption().getText();
		Assert.assertEquals(sel1, defaultopt);

		// DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), defaultopt);
		Select select2 = new Select(vacanciesPage.SelectVacancy());
		String sel2 = select2.getFirstSelectedOption().getText();
		Assert.assertEquals(sel2, defaultopt);

		// DropDownUtil.selectbytext(vacanciesPage.SelectHiringManager(), defaultopt);
		Select select3 = new Select(vacanciesPage.SelectHiringManager());
		String sel3 = select3.getFirstSelectedOption().getText();
		Assert.assertEquals(sel3, defaultopt);

		System.out.println("Default Option Selected for all dropdowns");

	}

	// DropDownUtil.selectbyvalue(vacanciesPage.SelectStatus(), defaultopt);
	// Select select4 = new Select(vacanciesPage.SelectStatus());
	// String sel4 = select4.getFirstSelectedOption().getText();
	// Assert.assertEquals(sel4, defaultopt); */

	@Test(priority = 7)
	public void validateoptionsVacancies() {
		// Verifying fields in Vacancies Section
		Assert.assertTrue(vacanciesPage.SearchButton().isDisplayed(), "Search Button not Displayed");
		Assert.assertTrue(vacanciesPage.ResetButton().isDisplayed(), "Reset Button not Displayed");
		Assert.assertTrue(vacanciesPage.SelectJobTitle().isDisplayed(), "Job Title Dropdown not Displayed");
		Assert.assertTrue(vacanciesPage.SelectVacancy().isDisplayed(), "Vacancy Dropdown not Displayed");
		Assert.assertTrue(vacanciesPage.SelectHiringManager().isDisplayed(), "Hiring Manager Dropdown not Displayed");
		Assert.assertTrue(vacanciesPage.SelectStatus().isDisplayed(), "Status Dropdown not Displayed");

		// Verifying the number of options in job Titles, Vacancy, Hiring Manger, Status
		// dropdown
		// String jobtitles = vacanciesPage.SelectJobTitle().getText();
		// int i = jobtitles.length();
		Select jtitle = new Select(vacanciesPage.SelectJobTitle());
		List<WebElement> l = jtitle.getOptions();
		System.out.println("No. of elements in Job titles dropdown are: " + l.size());
		// Assert.assertEquals(l.size(), 28);
		// System.out.println(jobtitles);

		// String vacancies = vacanciesPage.SelectVacancy().getText();
		Select vlist = new Select(vacanciesPage.SelectVacancy());
		List<WebElement> l2 = vlist.getOptions();
		System.out.println("No. of elements in Vacancies dropdown are: " + l2.size());
		// Assert.assertEquals(l2.size(), 9);
		// System.out.println(vacancies);

		// String hmanagers = vacanciesPage.SelectHiringManager().getText();
		Select hmlist = new Select(vacanciesPage.SelectHiringManager());
		List<WebElement> l3 = hmlist.getOptions();
		System.out.println("No. of elements in Hiring Manager dropdown are: " + l3.size());
		// Assert.assertEquals(l3.size(), 6);
		// System.out.println(hmanagers);

		// String status = vacanciesPage.SelectStatus().getText();
		// Select slist = new Select(vacanciesPage.SelectStatus());
		// List<WebElement> l4 = slist.getOptions();
		// System.out.println("No. of elements in Hiring Manager dropdown are: " +
		// l4.size());
		// Assert.assertEquals(l4.size(),29);
		// System.out.println(status);

	}

	@Test(priority = 8)
	public void searchVacancies() {
		Assert.assertTrue(vacanciesPage.SearchButton().isDisplayed(), "Search Button not Displayed");
		Assert.assertTrue(vacanciesPage.ResetButton().isDisplayed(), "Reset Button not Displayed");
		Assert.assertTrue(vacanciesPage.VacancyTable().isDisplayed(), "Vacancy table not Dispalyed");

	}

	@Test(priority = 9)
	public static void VacanciesTableHeaderValidation() {
		// Verify table headers of vacancy table
		List<WebElement> li = driver.findElements(By.xpath("//table[@id = 'resultTable']/thead/tr/th"));
		int n = li.size();

		List<String> s = new ArrayList<String>();
		System.out.println("Number of Columns : " + n);

		for (int i = 0; i < n; i++) {
			s.add(li.get(i).getText());
		}
		System.out.println("List String : " + s);
		System.out.println("No . of columns in a table are : " + n);
		Assert.assertEquals(5, n);

		for (int i = 2; i <= n; i++) {
			WebElement header = driver.findElement(By.xpath("//table[@id = 'resultTable']/thead/tr/th[" + i + "]"));
			System.out.print(header.getText());

			if (header.getText().equalsIgnoreCase("Vacancy")) {
				System.out.println(" : is in the header");
				Assert.assertTrue(header.getText().contains("Vacancy"));
			} else if (header.getText().equalsIgnoreCase("Job Title")) {
				System.out.println(" : is  in the header ");
				Assert.assertTrue(header.getText().contains("Job Title"));
			} else if (header.getText().equalsIgnoreCase("Hiring Manager")) {
				System.out.println(": is in the header");
				Assert.assertTrue(header.getText().contains("Hiring Manager"));
			} else if (header.getText().equalsIgnoreCase("Status")) {
				System.out.println(": is in the header");
				Assert.assertTrue(header.getText().contains("Status"));
			} else {
				System.out.println(" The given text is not in the header");
			}

			// Verify the color of the header row in Vacancy table
			String color = ColorValidationUtil.GetColor(header);
			Assert.assertEquals("Orange", color);
		}
	}

	@Test(priority = 10)
	public void colorOfButtons() {

		String addhex = ColorValidationUtil.GetColor(vacanciesPage.addvacancyButton());
		System.out.println("Add Color Hex: " + addhex);
		String delhex = ColorValidationUtil.GetColor(vacanciesPage.delvacancyButton());
		System.out.println("Delete Color Hex: " + delhex);
		Assert.assertEquals(addhex, "Green");
		Assert.assertEquals(delhex, "Red");
		Assert.assertNotEquals(addhex, delhex);
		System.out.println("Add and Delete buttons color validated.");
	}

	@Test(priority = 11)
	public void presenceOfAddDeleteButton() {
		Assert.assertTrue(vacanciesPage.addvacancyButton().isDisplayed());
		System.out.println("Add Button Displayed.");
		Assert.assertTrue(vacanciesPage.delvacancyButton().isDisplayed());
		System.out.println("Delete Button Displayed.");

		boolean flag;
		{
			try {
				WebDriverWait wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.elementToBeClickable(vacanciesPage.delvacancyButton()));
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
		}
		Assert.assertFalse(flag);

	}

	@Test(priority = 12)
	public void verifyAddJobVacancyPageAndSaveButtonDisplay() {
		vacanciesPage.addvacancyButton().click();
		vacanciesPage.getAddJobVacancyUrl();
		Assert.assertTrue(vacanciesPage.addJobvacancytitle().isDisplayed());
		System.out.println("Add Job Vacancy Page Dispalyed");
		Assert.assertTrue(vacanciesPage.savevacancyButton().isDisplayed());
		// driver.navigate().back();
	}

	@Test(priority = 13)
	public void validatingManadatoryFieldsBeforeEnteringValues() {
		vacanciesPage.savevacancyButton().click();
		System.out.println("Save Button clicked");
		boolean flag;
		if (vacanciesPage.getJobTitleId().getAttribute("value").isEmpty()) {
			System.out.println("Job Title is Required.");
			flag = true;
			System.out.println(
					driver.findElement(By.xpath("//*[@id='frmAddJobVacancy']/fieldset/ol/li[1]/span")).getText()
							+ " is displayed.");
		}
		if (vacanciesPage.getVacancyNameId().getAttribute("value").isEmpty()) {
			System.out.println("Vacancy Name is required");
			flag = true;
			System.out.println(
					driver.findElement(By.xpath("//*[@id='frmAddJobVacancy']/fieldset/ol/li[2]/span")).getText()
							+ " is displayed.");
		}
		if (vacanciesPage.getHiringManagerId().getAttribute("value").equalsIgnoreCase("Type for hints...")) {
			System.out.println("Hiring Manager is Invalid. ");
			flag = true;
			System.out.println(vacanciesPage.getHiringManagerId().getAttribute("value") + " is displayed in field.");
			System.out.println(
					driver.findElement(By.xpath("//*[@id='frmAddJobVacancy']/fieldset/ol/li[3]/span")).getText()
							+ " is displayed.");
		} else {

			System.out.println("Mandatory fields are valid");
			flag = false;
		}
		Assert.assertTrue(flag);
		System.out.println("All Manatory fields are Empty");
		// driver.navigate().back();
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
	}

	@Test(priority = 14)
	public void setcontext2(ITestContext context) {
		int sheetnumber = 30;
		context.setAttribute("index", sheetnumber);
	}

	@Test(dataProvider = "paramName", dataProviderClass = ExcelReaderWithIndex.class, priority = 15)

	public void verifyAddVacancyTesting(String jobTitle, String vacancyName, String hiringManager, String nop,
			String description) {
		vacanciesPage.addvacancyButton().click();

		DropDownUtil.selectbytext(vacanciesPage.getJobTitleId(), jobTitle);
		vacanciesPage.getVacancyNameId().sendKeys(vacancyName);
		vacanciesPage.getHiringManagerId().sendKeys(hiringManager);
		vacanciesPage.getNoOfPositionsId().sendKeys(nop);
		vacanciesPage.getDescriptionId().sendKeys(description);
		// vacanciesPage.getActiveId().click();
		// vacanciesPage.getPublishId().click();
		vacanciesPage.savevacancyButton().click();
		System.out.println(driver.findElement(By.id("successBodyEdit")).getText() + " is Displayed");
		/*
		 * String msgcolor =
		 * ColorValidationUtil.GetColor(driver.findElement(By.id("successBodyEdit")));
		 * System.out.println("Msg Color Hex: " + msgcolor);
		 * Assert.assertEquals(msgcolor, "Green");
		 */
		// Assert.assertEquals(vacanciesPage.getJobTitleId().getText(),jobTitle);
		Assert.assertEquals(vacanciesPage.getVacancyNameId().getAttribute("value"), vacancyName);
		Assert.assertEquals(vacanciesPage.getHiringManagerId().getAttribute("value"), hiringManager);
		Assert.assertEquals(vacanciesPage.getNoOfPositionsId().getAttribute("value"), nop);
		Assert.assertEquals(vacanciesPage.getDescriptionId().getAttribute("value"), description);
		String btnvalue = vacanciesPage.savevacancyButton().getAttribute("value");
		Assert.assertEquals(btnvalue, "Edit");
		vacanciesPage.backvacancyButton().click();
		// driver.navigate().back();

	}

	// @Test(priority = 16)
	public void saveVacancyButtonDisplay() {
		if (vacanciesPage.savevacancyButton().isDisplayed())
			vacanciesPage.savevacancyButton().click();
		System.out.println(driver.findElement(By.id("successBodyEdit")).getText() + " is Displayed");
		if (vacanciesPage.savevacancyButton().getAttribute("value").equalsIgnoreCase("Edit"))
			Assert.assertTrue(vacanciesPage.savevacancyButton().isDisplayed());
		else
			Assert.assertFalse(vacanciesPage.savevacancyButton().isDisplayed());
		Assert.assertTrue(vacanciesPage.backvacancyButton().isDisplayed());
	}

	@Test(priority = 17)
	public void AddVacancyTableHeaderValidation() {

		List<WebElement> li = driver.findElements(By.xpath("//*[@id='resultTable']/thead/tr/th"));
		int n = li.size();
		List<String> s = new ArrayList<String>();
		System.out.println("size of Columns : " + n);
		for (int i = 0; i < n; i++) {
			s.add(li.get(i).getText());
		}
		System.out.println("List String : " + s);
		System.out.println("No . of columns in a table are : " + n);
		Assert.assertEquals(5, n);

		for (int i = 1; i <= n; i++) {
			WebElement header = driver.findElement(By.xpath("//*[@id='resultTable']/thead/tr/th[" + i + "]"));
			System.out.print(header.getText());

			if (header.getText().equalsIgnoreCase("Vacancy")) {
				System.out.println(" : is in the header");
				Assert.assertTrue(header.getText().contains("Vacancy"));
			} else if (header.getText().equalsIgnoreCase("Job Title")) {
				System.out.println(" : is  in the header ");
				Assert.assertTrue(header.getText().contains("Job Title"));
			} else if (header.getText().equalsIgnoreCase("Hiring Manager")) {
				System.out.println(": is in the header");
				Assert.assertTrue(header.getText().contains("Hiring Manager"));
			} else if (header.getText().equalsIgnoreCase("Status")) {
				System.out.println(": is in the header");
				Assert.assertTrue(header.getText().contains("Status"));
			} else {
				System.out.println(" The given text is not in the header");
			}
		}
	}

	@Test(priority = 18)
	public void setcontext3(ITestContext context) {
		int sheetnumber = 31;
		context.setAttribute("index", sheetnumber);
	}

	@Test(dataProvider = "paramName", dataProviderClass = ExcelReaderWithIndex.class, priority = 19)

	public void verifyEditVacancyTesting(String jobTitle, String vacancyName, String hiringManager, String nop,
			String description) throws Exception {
		System.out.println("Vacancies Page clicked");

		SynchronisationWaitsUtil.implicitwait(12, TimeUnit.SECONDS, driver);
		System.out.println("i am here");

		System.out.println("Current Url of Vacancies : " + vacanciesPage.getcurrentUrl());
		// *[@id="resultTable"]/tbody/tr[10]/td[2]/a
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='resultTable']")).isDisplayed());
		System.out.println("Asserted");
		driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[1]/td[2]/a")).click();
		SynchronisationWaitsUtil.threadsleep(2000);
		String headerText = driver.findElement(By.xpath("//*[@id=\"addJobVacancy\"]/div[1]/h1")).getText();
		System.out.println("Header Text  : " + headerText);
		Assert.assertEquals("Edit Job Vacancy", headerText);
		vacanciesPage.savevacancyButton().click();

		DropDownUtil.selectbytext(vacanciesPage.getJobTitleId(), jobTitle);
		vacanciesPage.getVacancyNameId().clear();
		vacanciesPage.getVacancyNameId().sendKeys(vacancyName);
		vacanciesPage.getHiringManagerId().clear();
		vacanciesPage.getHiringManagerId().sendKeys(hiringManager);
		vacanciesPage.getNoOfPositionsId().clear();
		vacanciesPage.getNoOfPositionsId().sendKeys(nop);
		vacanciesPage.getDescriptionId().clear();
		vacanciesPage.getDescriptionId().sendKeys(description);
		// vacanciesPage.getActiveId().click();
		// vacanciesPage.getPublishId().click();
		vacanciesPage.savevacancyButton().click();
		System.out.println(driver.findElement(By.id("successBodyEdit")).getText() + " is Displayed");
		vacanciesPage.backvacancyButton().click();
		System.out.println("back button clicked");
		SynchronisationWaitsUtil.threadsleep(2000);
		String title = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[1]/td[3]")).getText();

		Assert.assertEquals(title, jobTitle);
		System.out.println("Job Title Edited Successfully");

		String name = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[1]/td[2]")).getText();
		Assert.assertEquals(name, vacancyName);
		System.out.println("Vacancy Name Edited Successfully");

		String hmanager = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[1]/td[4]")).getText();
		Assert.assertEquals(hmanager, hiringManager);
		System.out.println("Hiring Manager Edited Successfully");
	}

	@Test(priority = 20)
	public void setcontext4(ITestContext context) {
		int sheetnumber = 33;
		context.setAttribute("index", sheetnumber);
	}

	@Test(dataProvider = "paramName", dataProviderClass = ExcelReaderWithIndex.class, priority = 21)
	public void searchFunctionalityForVacancies(String jobTitle, String vacancyName, String hiringManager,
			String status) throws Exception {
		System.out.println(jobTitle + " " + vacancyName + " " + hiringManager + " " + status);
		// 1. Search By Job Title.
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);
		vacanciesPage.SearchButton().click();
		String jtitle = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();

		if (jtitle.equals(jobTitle))
			Assert.assertEquals(jobTitle, jtitle);
		else
			System.out.println("No Match Found with : " + jtitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 2.Search By Vacancy Name
		// vacanciesPage.SelectVacancy().sendKeys(vacancyName);
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), vacancyName);
		vacanciesPage.SearchButton().click();
		SynchronisationWaitsUtil.threadsleep(1000);
		String vname = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		if (vname.equals(vacancyName))
			Assert.assertEquals(vacancyName, vname);
		else
			System.out.println("No Match Found with :" + vacancyName);

		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 3. Search By Hiring Manager
		// vacanciesPage.SelectHiringManager().sendKeys(hiringManager);
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectHiringManager(), hiringManager);
		vacanciesPage.SearchButton().click();
		String hmanager = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		if (hmanager.equals(hiringManager)) {

			Assert.assertEquals(hmanager, hiringManager);
			System.out.println("Entered Hiring Manager and Asserted.");
		} else {
			System.out.println("No Match Found with : " + hiringManager);
		}

		// 4. Search By Status

		// vacanciesPage.SelectHiringManager().sendKeys(status);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// DropDownUtil.selectbytext(vacanciesPage.SelectStatus(), status);
		// vacanciesPage.SearchButton().click();
		String status1 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();
		if (status1.equals(status)) {

			Assert.assertEquals(status1, status);
			System.out.println("Entered Hiring Manager and Asserted.");
		} else {
			System.out.println("No Match Found with : " + status);
		}

		// 5. Search By Job Title and vacancy name
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SearchButton().click();

		String jtitle2 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String vname2 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();

		if ((jtitle2.equals(jobTitle) && (vname2.equals(vacancyName)))) {
			Assert.assertEquals(jobTitle, jtitle2);
			Assert.assertEquals(vacancyName, vname2);
		} else
			System.out.println("No Match Found with : " + jtitle2 + vname2);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 6.Search By Vacancy Name and Hiring Manager
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), vacancyName);
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		vacanciesPage.SearchButton().click();
		String vname3 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String hmanager2 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();

		if ((vname3.equals(vacancyName) && (hmanager2.equals(hiringManager)))) {

			Assert.assertEquals(vacancyName, vname3);
			Assert.assertEquals(hiringManager, hmanager2);

		} else
			System.out.println("No Match Found with : " + vname3 + " " + hmanager2);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 7.Search By Hiring Manager and Status
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectHiringManager().sendKeys(hiringManager);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectStatus().sendKeys(status);

		// vacanciesPage.SearchButton().click();
		String hmanager3 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String status2 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		if ((hmanager3.equals(hiringManager)) && (status2.equals(status))) {
			Assert.assertEquals(hiringManager, hmanager3);
			Assert.assertEquals(status, status2);
		} else
			System.out.println("No Match Found with : " + hmanager3 + " " + status2);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 8.Search By Job Title and Hiring Manager
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		vacanciesPage.SearchButton().click();
		String jtitle3 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String hmanager4 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		if ((jtitle3.equals(jobTitle) && (hmanager4.equals(hiringManager)))) {
			Assert.assertEquals(jobTitle, jtitle3);
			Assert.assertEquals(hiringManager, hmanager4);
		} else
			System.out.println("No Match Found with : " + jtitle3 + " " + hmanager4);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 9.Search By Job Title and Status
		// SynchronisationWaitsUtil.threadsleep(1000);
		// DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectStatus().sendKeys(status);

		// vacanciesPage.SearchButton().click();
		String jtitle4 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String status3 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		if ((jtitle4.equals(jobTitle) && (status3.equals(status)))) {
			Assert.assertEquals(jobTitle, jtitle4);
			Assert.assertEquals(status, status3);
		} else
			System.out.println("No Match Found with : " + jtitle4 + " " + status3);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 10. Search by Vacancy Name and Status
		// SynchronisationWaitsUtil.threadsleep(1000);
		// DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), vacancyName);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectStatus().sendKeys(status);

		// vacanciesPage.SearchButton().click();
		String vname4 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String status4 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		if ((vname4.equals(vacancyName) && (status4.equals(status)))) {
			Assert.assertEquals(vacancyName, vname4);
			Assert.assertEquals(status, status4);
		} else
			System.out.println("No Match Found with : " + vname4 + " " + status4);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 11. Search By Vacancy Name and job title
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SearchButton().click();

		String vname5 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String jtitle5 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();

		if ((vname5.equals(vacancyName) && (jtitle5.equals(jobTitle)))) {
			Assert.assertEquals(vacancyName, vname5);
			Assert.assertEquals(jobTitle, jtitle5);

		} else
			System.out.println("No Match Found with : " + vname5 + jtitle5);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 12.Search By Hiring Manager and Vacancy Name
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), vacancyName);

		vacanciesPage.SearchButton().click();
		String hmanager5 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String vname6 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();

		if ((hmanager5.equals(hiringManager) && (vname6.equals(vacancyName)))) {

			Assert.assertEquals(vacancyName, vname6);
			Assert.assertEquals(hiringManager, hmanager5);

		} else
			System.out.println("No Match Found with : " + hmanager5 + " " + vname6);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 13.Search By Status and Hiring Manager
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectStatus().sendKeys(status);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		// vacanciesPage.SearchButton().click();
		String status5 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();
		String hmanager6 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();

		if ((status5.equals(status)) && (hmanager6.equals(hiringManager))) {
			Assert.assertEquals(hiringManager, hmanager6);
			Assert.assertEquals(status, status5);
		} else
			System.out.println("No Match Found with : " + status5 + " " + hmanager6);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 14.Search by Hiring Manager and job title
		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);
		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		vacanciesPage.SearchButton().click();
		String hmanager7 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String jtitle6 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();

		if ((hmanager7.equals(hiringManager) && (jtitle6.equals(jobTitle)))) {
			Assert.assertEquals(hiringManager, hmanager7);
			Assert.assertEquals(jobTitle, jtitle6);

		} else
			System.out.println("No Match Found with : " + hmanager7 + " " + jtitle6);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 15.Search By Status and Job Title
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectStatus().sendKeys(status);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		// vacanciesPage.SearchButton().click();
		String status6 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();
		String jtitle8 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();

		if ((status6.equals(status) && (jtitle8.equals(jobTitle)))) {
			Assert.assertEquals(status, status6);
			Assert.assertEquals(jobTitle, jtitle8);

		} else
			System.out.println("No Match Found with : " + status6 + " " + jtitle8);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 16. Search by Status and Vacancy Name
		// SynchronisationWaitsUtil.threadsleep(1000);
		// vacanciesPage.SelectStatus().sendKeys(status);
		// SynchronisationWaitsUtil.threadsleep(1000);
		// DropDownUtil.selectbytext(vacanciesPage.SelectVacancy(), vacancyName);

		// vacanciesPage.SearchButton().click();
		String status7 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();
		String vname7 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();

		if ((vname7.equals(vacancyName) && (status7.equals(status)))) {
			Assert.assertEquals(status, status7);
			Assert.assertEquals(vacancyName, vname7);

		} else
			System.out.println("No Match Found with : " + status7 + " " + vname7);
		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 17. Search By Job Title , vacancy name , Hiring Manager

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		vacanciesPage.SearchButton().click();

		String jtitle9 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String vname8 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String hmanager8 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();

		if ((jtitle9.equals(jobTitle) && (vname8.equals(vacancyName) && (hmanager8.equals(hmanager))))) {
			Assert.assertEquals(jobTitle, jtitle9);
			Assert.assertEquals(vacancyName, vname8);
			Assert.assertEquals(hmanager, hmanager8);

		} else
			System.out.println("No Match Found with : " + jtitle9 + " " + vname8 + " " + hmanager8);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

// 18. Search By Job Title , Hiring Manager , Vacancy

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		vacanciesPage.SearchButton().click();

		String jtitle10 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String hmanager9 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String vname9 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();

		if ((jtitle10.equals(jobTitle) && (hmanager9.equals(hmanager) && (vname9.equals(vacancyName))))) {
			Assert.assertEquals(jobTitle, jtitle10);
			Assert.assertEquals(hmanager, hmanager9);
			Assert.assertEquals(vacancyName, vname9);

		} else
			System.out.println("No Match Found with : " + jtitle10 + " " + hmanager9 + " " + vname9);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

// 19. Search By Vacancy , Hiring Manager , Job Title

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		vacanciesPage.SearchButton().click();

		String vname10 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String hmanager10 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String jtitle7 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();

		if ((vname10.equals(vacancyName) && (hmanager10.equals(hmanager) && (jtitle7.equals(jobTitle))))) {
			Assert.assertEquals(vacancyName, vname10);
			Assert.assertEquals(hmanager, hmanager10);
			Assert.assertEquals(jobTitle, jtitle7);

		} else
			System.out.println("No Match Found with : " + vname10 + " " + hmanager10 + " " + jtitle7);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

// 20. Search By Vacancy ,Job Title ,Hiring Manager 

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		vacanciesPage.SearchButton().click();

		String vname11 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String jtitle11 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String hmanager11 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();

		if ((vname11.equals(vacancyName) && (jtitle11.equals(jobTitle) && (hmanager11.equals(hmanager))))) {
			Assert.assertEquals(vacancyName, vname11);
			Assert.assertEquals(jobTitle, jtitle11);
			Assert.assertEquals(hmanager, hmanager11);

		} else
			System.out.println("No Match Found with : " + vname11 + " " + jtitle11 + " " + hmanager11);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

// 21. Search By Hiring Manager ,Job Title ,Vacancy 

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		vacanciesPage.SearchButton().click();

		String hmanager12 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String jtitle12 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String vname12 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();

		if ((hmanager12.equals(hmanager) && (jtitle12.equals(jobTitle) && (vname12.equals(vacancyName))))) {
			Assert.assertEquals(hmanager, hmanager12);
			Assert.assertEquals(jobTitle, jtitle12);
			Assert.assertEquals(vacancyName, vname12);

		} else

			System.out.println("No Match Found with : " + hmanager12 + " " + jtitle12 + " " + vname12);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 22. Search By Hiring Manager ,Vacancy , Job Title

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		vacanciesPage.SearchButton().click();

		String hmanager13 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String vname13 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String jtitle13 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();

		if ((hmanager13.equals(hmanager) && (vname13.equals(vacancyName) && (jtitle13.equals(jobTitle))))) {
			Assert.assertEquals(hmanager, hmanager13);
			Assert.assertEquals(vacancyName, vname13);
			Assert.assertEquals(jobTitle, jtitle13);

		} else

			System.out.println("No Match Found with : " + hmanager13 + " " + vname13 + " " + jtitle13);

		// 23. Search By Job Title , vacancy name , Status

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectStatus().sendKeys(status);

		vacanciesPage.SearchButton().click();

		String jtitle14 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String vname14 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String status8 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		if ((jtitle14.equals(jobTitle) && (vname14.equals(vacancyName) && (status8.equals(status))))) {
			Assert.assertEquals(jobTitle, jtitle14);
			Assert.assertEquals(vacancyName, vname14);
			Assert.assertEquals(status, status8);

		} else
			System.out.println("No Match Found with : " + jtitle14 + " " + vname14 + " " + status8);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 24. Search By Job Title , Hiring Manager , Status

		SynchronisationWaitsUtil.threadsleep(1000);
		DropDownUtil.selectbytext(vacanciesPage.SelectJobTitle(), jobTitle);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectStatus().sendKeys(status);

		vacanciesPage.SearchButton().click();

		String jtitle15 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[3]")).getText();
		String hmanager14 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String status9 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		if ((jtitle15.equals(jobTitle) && (hmanager14.equals(hmanager) && (status9.equals(status))))) {
			Assert.assertEquals(jobTitle, jtitle15);
			Assert.assertEquals(hmanager, hmanager14);
			Assert.assertEquals(status, status9);

		} else
			System.out.println("No Match Found with : " + jtitle15 + " " + hmanager14 + " " + status9);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

		// 25. Search By Vacancy , Hiring Manager , Status

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectVacancy().sendKeys(vacancyName);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectHiringManager().sendKeys(hiringManager);

		SynchronisationWaitsUtil.threadsleep(1000);
		vacanciesPage.SelectStatus().sendKeys(status);

		vacanciesPage.SearchButton().click();

		String vname15 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[2]/a")).getText();
		String hmanager15 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[4]")).getText();
		String status10 = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

		if ((vname15.equals(vacancyName) && (hmanager15.equals(hmanager) && (status10.equals(status))))) {
			Assert.assertEquals(vacancyName, vname15);
			Assert.assertEquals(hmanager, hmanager15);
			Assert.assertEquals(status, status10);

		} else
			System.out.println("No Match Found with : " + vname15 + " " + hmanager15 + " " + status10);

		SynchronisationWaitsUtil.threadsleep(1000);
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();

	}

	int finalvacancynumRows;

	@Test(priority = 22, dependsOnMethods = { "verifyAddVacancyTesting" })
	public void deletingSingle_vacancy() throws Exception {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
		int numRows = rows.size();
		System.out.println("No. of Rows Before Deleting : " + numRows);// *[@id="resultTable"]/tbody/tr[1]/td[1]
		driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr[1]/td[1]")).click();
		if (vacanciesPage.delvacancyButton().isEnabled()) {

			vacanciesPage.delvacancyButton().click();
			vacanciesPage.getOkbutton().click();
			List<WebElement> finalRows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
			finalvacancynumRows = finalRows.size();
			System.out.println("No. of Rows After Deleting : " + finalvacancynumRows);
			if (numRows > 1) {
				Assert.assertEquals(finalvacancynumRows, numRows - 1);
			}
		}

		else {
			// Assert.assertFalse(qualifiation_MyInfoPage.delSkillButton().isDisplayed());
			System.out.println("No Rows Displayed");
		}
	}

	@Test(priority = 23)
	public void deletingall_Vacancies() {
		String str = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr")).getText();
		System.out.println("String : " + str);
		if (str.equals("No Records Found")) {
			System.out.println("No rows Displayed");
		} else {
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
			int numRows = rows.size();
			System.out.println("No. of Rows Before Deleting : " + numRows);
			vacanciesPage.getVacancyCheckall().click();
			vacanciesPage.delvacancyButton().click();
			vacanciesPage.getOkbutton().click();

		}

	}

}