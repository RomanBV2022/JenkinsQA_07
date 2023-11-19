package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class AllCreateProjectPage extends BasePage {

    public AllCreateProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@name='name']")
    private WebElement inputNameOfProject;

    @FindBy(xpath ="//li[@class='hudson_model_FreeStyleProject']")
    private WebElement createGenerallyProject;

    @FindBy(xpath = "//span[text()='Pipeline']")
    private WebElement createPipelineProject;

    @FindBy(xpath = "//span[text()='Multi-configuration project']")
    private WebElement createMultiConfigurationProject;

    @FindBy(xpath = "//*[@id='j-add-item-type-nested-projects']/ul/li[1]/label")
    private WebElement createFolderProject;

    @FindBy(xpath = "//span[text()='Multibranch Pipeline']")
    private WebElement createMultiBranchPipelineProject;

    @FindBy(xpath = "//span[text()='Organization Folder']")
    private WebElement createOrganizationProject;

    @FindBy(xpath = "//button[@id='ok-button']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//a[text()='Dashboard']")
    private WebElement dashboard;




    public void writeNameOfProject(String name){
        inputNameOfProject.sendKeys(name);
    }
    public void clickCreateGenerallyProject(){
        createGenerallyProject.click();
    }

    public void clickCreatePipelineProject(){
        createPipelineProject.click();
    }

    public void clickCreateMultiConfigurationProject(){
        createMultiConfigurationProject.click();
    }

    public void clickCreateFolderProject(){
        createFolderProject.click();
    }

    public void clickCreateMultiBranchPipelineProject(){
        createMultiBranchPipelineProject.click();
    }

    public void clickCreateOrganizationProject(){
        createOrganizationProject.click();
    }


    public void clickButtonSubmit(){
        buttonSubmit.click();
    }

    public void clickDashboard(){
        dashboard.click();
    }






}
