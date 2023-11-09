Feature: Search with Google
  # Definicja funkcjonalności, której testy będą opisane w tym pliku

  Scenario Outline: Search for phrase on Google Web
    # Scenariusz testowy, który zostanie wykonany przy użyciu różnych danych wejściowych

    Given Page 'https://www.google.pl/' opened in browser
    And Cookies are accepted
    # Krok początkowy - otwarcie przeglądarki i zaakceptowanie plików cookie

    When Phrase given '<SearchPhrase>' in search field
    And Button search in google clicked
    # Krok wprowadzenia frazy do pola wyszukiwania i kliknięcie przycisku wyszukiwania

    Then First <meaningfulPositions> search result text contain phrase '<SearchPhrase>'
    And Save screenshot
    And Close browser
    # Krok sprawdzenia, czy wyniki zawiera podaną frazę, zapisanie zrzutu ekranu, i zamknięcie przeglądarki

    Examples:
      # Tabela z przykładami danych wejściowych dla scenariusza
      | SearchPhrase      | meaningfulPositions |
      | Nikodem           | 4                   |
      | Krystyna Czubówna | 1                   |
#     | Andrzej               | 1                   |