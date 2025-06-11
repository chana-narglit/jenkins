package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

    private final By title = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getText(title);
    }

    public void sortBy(String optionText) {
        Select select = new Select(find(sortDropdown));
        select.selectByVisibleText(optionText); // למשל "Price (low to high)"
    }

    public String getSelectedSortOption() {
        Select select = new Select(find(sortDropdown));
        return select.getFirstSelectedOption().getText();
    }
}
