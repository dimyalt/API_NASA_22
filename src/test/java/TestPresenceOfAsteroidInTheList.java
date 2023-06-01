import data.DataForTest;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;


public class TestPresenceOfAsteroidInTheList {
    @Before
    public void setUp(){
        RestAssured.baseURI = "https://api.nasa.gov/neo/rest/v1/neo";
    }


    @Test
    @DisplayName("Проверка API получения общего набора данных об астероидах и поиск конкретного астероида")
    public void checkBrowseDataSetAndSearchAsteroid(){
        DataForTest testData = new DataForTest();
        given()
                .filter(new AllureRestAssured())
                .get("/browse?api_key="+testData.getAPI_KEY())
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body(("near_earth_objects.id"), hasItems(testData.getAsteroidId()))
                .and()
                .body(("near_earth_objects.name_limited"), hasItems(testData.getAsteroidName()));
    }

}
