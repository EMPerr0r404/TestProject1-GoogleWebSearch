package googleWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCookiesAndMainPage {
    @FindBy(className = "sy4vM")
    private WebElement acceptButton; // Przycisk akceptacji plików cookie

    @FindBy(name = "q")
    private WebElement searchInput; // Pole wyszukiwania

    @FindBy(css = "div.A8SBwf > div.FPdoLc.lJ9FBc > center > input.gNO89b")
    private WebElement searchButton; // Przycisk wyszukiwania

    public GoogleCookiesAndMainPage(WebDriver driver) {
        // Inicjalizacja elementów na stronie za pomocą PageFactory
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        acceptButton.click(); // Mamy juz znaleziony w @FindBy i zdefiniowany przycisk akceptacji - tutaj go klikamy
    }

    public void fillSearchInput(String searchPhrase) {
        searchInput.sendKeys(searchPhrase); // Mamy juz znalezione w @FindBy i zdefiniowane pole wyszukiwania - tu je uzupełniamy stringiem 'searchPhrase' który otrzymujemy z zewnątrz.
    }

    public void clickSearchButton() {
        searchButton.click();
    } // Kliknięcie przycisku wyszukiwania
}