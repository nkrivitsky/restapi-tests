package tests;

import framework.DataProvider;
import framework.base.BaseTest;
import framework.base.PropertiesResourceManager;
import framework.generateinfo.GeneratorInfo;
import framework.utils.JSONUtils;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import models.Pet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import requests.RequestUrls;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;


public class ApiRequests extends BaseTest {
    private static PropertiesResourceManager web = new PropertiesResourceManager("web");

    @BeforeClass
    public static void beforeSuite() {
        RestAssured.baseURI = web.getProperty("base.url");
        RestAssured.requestSpecification = given()
                .header("Content-type", "application/json")
                .header("Accept", "application/json")
                .log().all();
    }

    @SneakyThrows
    @Test
    public void testCreatePet() {
        Pet newPet = GeneratorInfo.generateNewPet();

        Pet responsePet = given().body(JSONUtils.mapJSONObjectToString(newPet))
                .when()
                .post(RequestUrls.PET_POST_NEW.getUrl())
                .then()
                .statusCode(HTTP_OK)
                .log().body()
                .extract().as(Pet.class);

        DataProvider.addPet(responsePet);

        Assert.assertTrue(responsePet.compareData(newPet), formatMessage("pet.dataCompare"));
        Assert.assertTrue(responsePet.getId() != null && responsePet.getId() != 0, formatMessage("pet.idIsAdded"));
    }


    @Test(enabled = false)
    public void testUpdatePet(){

    }

    @Test(enabled = false)
    public void testDeletePet(){

    }
}
