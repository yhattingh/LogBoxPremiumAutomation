package pageObjectsLogBox;

import java.io.File;

import frameWork.BasePageFrameWork;

public class BasePageLogBox extends BasePageFrameWork {

	//Declare Homepage Url
	String homePageURL = "https://qa.logbox.co.za/premium/#/";
		
	//Method: Navigate Home
		public void navigateToHomePage() {
			driver.get(homePageURL);
			waitForUrl(30, "premium");		
		}
		
	//Method: Click Activity Feed main menu item
		
	//Method: Click Practice main menu item
	
}
