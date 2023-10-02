package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class SearchTests extends CoreTestCase {
    @Test
//находим поле поиска, вводим там Java, смотрим выдачу поиска и ищем там Object-oriented programming language
    public void testSearch(){



     /*   MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find Element Search",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find Object-oriented programming language in list",
                15
        );*/

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
//ищем поле ввода, пишем Java, очищаем ввод и проверяем что крестика нет на экране(что поиск закрыт)
    public void testCancelSearch(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.setSearchSkipButton();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        searchPageObject.waitForCancelButtonToDissappear();

    }




    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "Linkin park Discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "hfgdzgfxhh";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsOfSearch();

    }


}
