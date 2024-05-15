package systemTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseClass;

public class addToCart extends BaseClass {

	
	// Login 
	@Test(priority = 1)
	public void Login() throws InterruptedException {

		LoginScreen login = new LoginScreen();
	    login.abilityToLoginSuccessfully();
	    Thread.sleep(5000);
	}
	
	
	// books
	@Test(priority = 2)
	public void AddToCart() throws InterruptedException {
		
		  //click books
			By locator = By.xpath("//a[normalize-space()='Books']");
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			
		//driver.findElement(By.xpath("//a[normalize-space()='Books']")).click();
			Thread.sleep(5000);
           //click paperback
			driver.findElement(By.xpath("//li[2]//a[1]//img[1]")).click();
			Thread.sleep(5000);
	

		//driver.findElement(By.xpath("//div[@class='contentpanel']//div[1]//div[2]//div[3]//a[1]//i[1]")).click();
			//click add to cart of the item to be added
		driver.findElement(By.xpath("//div[3]//div[2]//div[3]//a[1]//i[1]")).click();
		Thread.sleep(5000);
		
		//click viewcart
		driver.findElement(By.xpath("//ul[@id='main_menu_top']//a[@class='top nobackground']")).click();
		Thread.sleep(5000);
		
		//validate the cart
		list<WebElement> productdetail = driver.findElement(By.xpath("//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]"));
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Allegiant by Veronica Roth");
		map.put(1,"$7.99");
		map.put(1, "1");
		map.put(1,"$7.99");
		for(int i=0;i<productdetail.size();i++) {
			assertTrue(productdetail.get(i).getText().contain(map.get(1)));
		}
		Thread.sleep(5000);
	}
	
	//verify product quantity
		@Test(priority = 3)
		public void removefromcart()throws InterruptedException {
			//verify cart page
			String text = driver.findElement(By.xpath("//span[@class='maintext']")).getText();
			assertEquals(text," Shopping Cart");
			//remove
			driver.findElement(By.xpath("//i[@class='fa fa-trash-o fa-fw']")).click();
			Thread.sleep(5000);
			String carttext = driver.findElement(By.xpath("//div[@class='contentpanel']")).getText();
			assertEquals(carttext, "Your shopping cart is empty!"); 
		}
		
		
	//verify product quantity
	@Test(priority = 4)
	public void verifyquantity() throws InterruptedException {
		
		//click books
		By locator = By.xpath("//a[normalize-space()='Books']");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
		Thread.sleep(5000);

		//click paperback
		driver.findElement(By.xpath("//li[2]//a[1]//img[1]")).click();
		Thread.sleep(5000);
		//click book "veronica"
		driver.findElement(By.xpath("//div[@class='thumbnails grid row list-inline']//div[3]//div[2]//a[1]//img[1]")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@id='product_quantity']")).clear();
		driver.findElement(By.xpath("//input[@id='product_quantity']")).sendKeys("4");
		
		//add to cart
		driver.findElement(By.xpath("//a[@class='cart']")).click();
		Thread.sleep(5000);
		
		//click cart to verify
		driver.findElement(By.xpath("//ul[@id='main_menu_top']//a[@class='top nobackground']")).click();
		Thread.sleep(5000);
		String qty = driver.findElement(By.xpath("//input[@id='cart_quantity114']")).getText();
		assertEquals(qty,"4");
	}
}

