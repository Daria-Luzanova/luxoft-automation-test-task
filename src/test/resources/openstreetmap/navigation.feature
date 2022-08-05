Feature: NavigationTest


  Scenario: Verify distance between "Радищева, Київ" and "Кембридж"
    Given I am on the Open Street Map website
    And I click on the 'Поиск маршрута между двумя точками' button
    And I input direction from "Радищева, Київ" and to "Кембридж"
    And I chose value "Велосипед (OSRM)" from the transport option dropdown
    When I click on the 'Найти' button
    Then Verify that distance is "2425km"