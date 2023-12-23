package google;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import googleWeb.GoogleCookiesAndMainPage;
import googleWeb.GoogleSearchResultsPage;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GglSearchSteps {
    public static WebDriver driver;
    GoogleCookiesAndMainPage gglMainPage;
    GoogleSearchResultsPage gglResultsPage;

    // Krok: Otwarcie strony w przeglądarce
    @Given("Page {string} opened in browser")
    public void pageOpen(String string) {
        // Inicjalizacja przeglądarki Chrome
        driver = new ChromeDriver();

        // Ustawienie czasu oczekiwania na elementy na stronie
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Maksymalizacja okna przeglądarki
        driver.manage().window().maximize();

        // Otwarcie podanej strony URL
        driver.get(string);

        // Inicjalizacja stron za pomocą Page Object Pattern
        gglMainPage = new GoogleCookiesAndMainPage(driver);
        gglResultsPage = new GoogleSearchResultsPage(driver);
    }

    // Krok: Akceptacja plików cookie
    @Given("Cookies are accepted")
    public void cookiesAccept() {
        gglMainPage.acceptCookies();
    }

    // Krok: Wpisanie frazy do pola wyszukiwania
    @When("Phrase given {string} in search field")
    public void phraseSearch(String searchPhrase) {
        gglMainPage.fillSearchInput(searchPhrase);
    }

    // Krok: Kliknięcie przycisku wyszukiwania w Google
    @When("Button search in google clicked")
    public void buttonClick() {
        gglMainPage.clickSearchButton();
    }

    // Krok: Sprawdzenie, czy <liczba> wyników zawiera frazę
    @Then("First {int} search result text contain phrase {string}")
    public void firstSearchContains(Integer count, String searchPhrase) {
        List<String> textsFromResultsLinks = gglResultsPage.getTextsFromResultsLinks();
        for (int i = 0; i < count; i++) {
            String linkTextLower = textsFromResultsLinks.get(i).toLowerCase();
            String searchPhraseLower = searchPhrase.toLowerCase();
            if (!linkTextLower.contains(searchPhraseLower)) {
                // Jeśli tekst z wyniku nie zawiera frazy, to test jest nieudany
                Assert.fail(String.format("search result should contain: %s, but was: %s", searchPhraseLower, linkTextLower));
            }
        }
    }

    // Krok: Zapisanie zrzutu ekranu
    @And("Save screenshot")
    public void saveScreenshot() throws IOException {
        // Zrobienie zrzutu ekranu (zostanie zapisany w domyślnym miejscu) i automatycznie usunięty po teście
        File tmpScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Skopiowanie zrzutu ekranu do wybranego miejsca
        // Ścieżka do miejsca zapisania zrzutu ekranu
        // (katalog na zrzuty ekranu MUSI istnieć: C:\test-evidence) na przykład:
        String currentDateTime = LocalDateTime.now().toString().replaceAll(":", "_");
        Files.copy(tmpScreenshot.toPath(), Paths.get("C:", "test", "ggl-search" + currentDateTime + ".png"));
    }

    // Krok: Zamknięcie przeglądarki
    @Then("Close browser")
    public void closeBrowser() {
        System.out.println("Zamykamy przeglądarkę, prawda?");
        driver.quit();
    }
}
