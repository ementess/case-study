import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Feature {

    public WebDriver driver;
    public String testURL = "https://www.trendyol.com/";
    public String[] tabs = {"KADIN", "ERKEK", "ÇOCUK", "AYAKKABI & ÇANTA", "SAAT & AKSESUAR", "KOZMETİK", "EV & YAŞAM", "ELEKTRONİK", "SÜPERMARKET"};
    public int invalidImageCount;

    @BeforeTest
    public void setupTest (){

        driver = new ChromeDriver();
        driver.navigate().to(testURL);
    }

    @Test
    public void LogIn()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);  // you can reuse this one

        WebElement elem = driver.findElement(By.xpath("//span[.='Giriş Yap']"));
        elem.click();

        driver.findElement(By.id("email")).sendKeys("emre mentes");


    }

    @Test
    public void AllTabsShouldBeDisplayedProperly ()
    {
        int tabCount = tabs.length;
        System.out.println("TAB SAYISI : " + tabCount);
        for (int i = 0; i < tabCount; i++)
        {
            System.out.println(tabs[i]);

            clickToLink(tabs[i]);


           // driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            validateInvalidImages();
        }




    }

    @Test
    public void GoToAnyBoutique()
    {

    }

    @Test
    public void details(){}

    @Test
    public void basket(){}

    @Test
    public void firstTest () {
        //Get page title
        String title = driver.getCurrentUrl();

        driver.findElement(By.name("q")).sendKeys("emre mentes");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //driver.findElement(By.name("btnK")).click();

        Assert.assertEquals("https://www.google.com/", title);
    }

    @Test
    public void secondTest () {
        //Get page title
        String title = driver.getTitle();

        //Print page's title
        System.out.println("Page Title: " + title);
        Assert.assertEquals("Google", title);
    }





    //-----------------------------------Feature TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
  //     driver.quit();
    }


    public void validateInvalidImages() {
        try {
            invalidImageCount = 0;
            List<WebElement> imagesList = driver.findElements(By.tagName("img"));
            System.out.println("Total no. of images are " + imagesList.size());
            for (WebElement imgElement : imagesList) {
                if (imgElement != null) {
                    verifyimageActive(imgElement);
                }
            }
            System.out.println("Total no. of invalid images are "	+ invalidImageCount);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public void verifyimageActive(WebElement imgElement) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(imgElement.getAttribute("src"));
            System.out.println(request);
            HttpResponse response = client.execute(request);

            // verifying response code he HttpStatus should be 200 if not,
            // increment as invalid images count
            if (response.getStatusLine().getStatusCode() != 200)
                invalidImageCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToLink(String linkText){

            WebElement linkByText = driver.findElement(By.linkText(linkText));
            linkByText.click();




    }


}