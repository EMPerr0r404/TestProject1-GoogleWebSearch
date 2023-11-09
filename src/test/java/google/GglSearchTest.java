package google;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)  // Adnotacja oznaczająca, że ta klasa będzie używana do uruchamiania testów Cucumber
@CucumberOptions(
        features = "src/test/resources/features/GglSearchCucu.feature",  // Ścieżka do pliku z opisem funkcjonalności (feature) Cucumber
        plugin = {"pretty", "html:out"}  // Konfiguracja raportów i wyjścia z testów
)
public class GglSearchTest {
}