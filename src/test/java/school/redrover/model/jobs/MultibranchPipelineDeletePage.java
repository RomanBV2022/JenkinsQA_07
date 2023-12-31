package school.redrover.model.jobs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.HomePage;
import school.redrover.model.base.BasePage;

public class MultibranchPipelineDeletePage extends BasePage<MultibranchPipelineDeletePage> {
    @FindBy(xpath = "//*[@id='main-panel']/form/button")
    private WebElement  redButtonYes;

    public MultibranchPipelineDeletePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickRedButtonYes() {
        redButtonYes.click();

        return new HomePage(getDriver());
    }
}
