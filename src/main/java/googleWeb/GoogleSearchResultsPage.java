package googleWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchResultsPage {

    // Deklaracja listy elementów reprezentujących wyniki wyszukiwania
    @FindBy(css = "div.sATSHe > div > div.g.Ww4FFb.vt6azd.tF2Cxc.asEBEc > div.N54PNb.BToiNc.cvP2Ce > div.kb0PBd.cvP2Ce.jGGQ5e > div > div > span > a > h3")
    private List<WebElement> resultsLinks;

    // Konstruktor klasy, inicjalizuje elementy na stronie używając PageFactory
    public GoogleSearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Metoda służąca do pobrania tekstów z linków wyników wyszukiwania
    public List<String> getTextsFromResultsLinks() {
        List<String> result = new ArrayList<>();

        // Iterujemy przez listę resultsLinks
        for (WebElement we : resultsLinks) {
            // Pobieramy tekst z każdego elementu i dodajemy go do wynikowej listy
            result.add(we.getText());
        }

        // Zwracamy listę tekstów z wyników wyszukiwania
        return result;
    }
}