package openstreetmap;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationSteps {

    private static final String WEBSITE_HOST = "https://www.openstreetmap.org/";
    private final ChromeDriver driver;
    private final WebDriverWait wait;

    public NavigationSteps() {
        this.driver = BrowserState.getDriver();
        this.wait = BrowserState.getWait();
    }

    @Given("I am on the Open Street Map website")
    public void navigateToOpenStreetMapWebsite() {
        driver.get(WEBSITE_HOST);
    }

    @Given("I click on the 'Поиск маршрута между двумя точками' button")
    public void clickOnFindDirectionButton() {
        WebElement findButtonElement = driver.findElement(By.xpath("//body/div[@id='content']/div[@id='sidebar']/div[1]/form[1]/div[1]/div[2]/a[1]/img[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(findButtonElement)).click();
    }

    @Given("I input direction from {string} and to {string}")
    public void inputStartAndFinishPoints(String from, String to) {
        WebElement routFromElement = driver.findElement(By.xpath("//body/div[@id='content']/div[@id='sidebar']/div[1]/form[2]/div[2]/div[2]/input[1]"));
        wait.until(ExpectedConditions.visibilityOf(routFromElement)).sendKeys(from);

        WebElement routToElement = driver.findElement(By.xpath("//body/div[@id='content']/div[@id='sidebar']/div[1]/form[2]/div[3]/div[2]/input[1]"));
        wait.until(ExpectedConditions.visibilityOf(routToElement)).sendKeys(to);
    }

    @Given("I chose value {string} from the transport option dropdown")
    public void choseTransportOption(String option) {
        WebElement dropDown = driver.findElement(By.xpath("//body/div[@id='content']/div[@id='sidebar']/div[1]/form[2]/div[4]/div[1]/select[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(dropDown));

        Select selectObject = new Select(dropDown);
        selectObject.selectByVisibleText(option);
    }

    @When("I click on the 'Найти' button")
    public void clickGoButton() {
        WebElement goButtonElement = driver.findElement(By.xpath("//body/div[@id='content']/div[@id='sidebar']/div[1]/form[2]/div[4]/div[2]/input[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(goButtonElement)).click();
    }

    @Then("Verify that distance is {string}")
    public void verifyDistance(String expectedDistance) throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath(String.format("//p[contains(text(),'Расстояние: %s. Время: 154:28.')]", expectedDistance)));
    }
}
