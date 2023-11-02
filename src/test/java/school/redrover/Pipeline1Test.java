package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class Pipeline1Test extends BaseTest {
    private final static String PIPELINE_NAME = "Pipeline name test";
    private final static String HOME_PAGE = "jenkins-home-link";
    private final static String NEW_ITEM = "//a[@href='/view/all/newJob']";
    private final static String PIPELINE_BOARD_NAME = "//a[@class='jenkins-table__link model-link inside']";

    private void createProject() {
        getDriver().findElement(By.id(HOME_PAGE));
        getDriver().findElement(By.xpath(NEW_ITEM)).click();

        getDriver().findElement(By.xpath("//*[@class='jenkins-input']"))
                .sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("(//span[@class='label'])[2]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();

        getDriver().findElement(By.id(HOME_PAGE)).click();
    }
    @Test
    public void testCreatePipeline() {
        getDriver().findElement(By.id(HOME_PAGE));
        getDriver().findElement(By.xpath(NEW_ITEM)).click();

        getDriver().findElement(By.xpath("//*[@class='jenkins-input']"))
                .sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("(//span[@class='label'])[2]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();

        getDriver().findElement(By.id(HOME_PAGE)).click();
        String actualName = getDriver()
                .findElement(By.xpath(PIPELINE_BOARD_NAME)).getText();
        Assert.assertEquals(actualName, PIPELINE_NAME);
    }
    @Test
    public void testPipelineDelete() {
        createProject();
        getDriver().findElement(By.xpath(PIPELINE_BOARD_NAME)).click();
        getDriver().findElement(By.xpath("//span[text()='Delete Pipeline']")).click();
        getDriver().switchTo().alert().accept();
        List<WebElement> elements = getDriver().findElements(By.xpath(PIPELINE_BOARD_NAME));
        boolean deleted = elements.isEmpty();
        Assert.assertTrue(deleted, "Element is not present after deletion");
    }
}



