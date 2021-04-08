package tests;

import db.CityDb;
import framework.base.BaseTest;
import framework.generateinfo.GeneratorInfo;
import framework.utils.JSONUtils;
import framework.utils.ResourceFile;
import models.City;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestDBSuite extends BaseTest {
    private static final String JSON_FILE = "city.list.json";
    private static List<City> listFromFile = new ArrayList<>();
    private static CityDb cityDb = null;

    @BeforeSuite
    private void loadDataFromFile() {
        cityDb = new CityDb();
        cityDb.deleteAll();
        String jSONData = ResourceFile.getStringFromResourceFile(JSON_FILE);
        listFromFile = Arrays.asList(JSONUtils.mapJSONToObject(jSONData, City[].class));
    }

    @BeforeTest
    public void addingDataToDB() {
        cityDb.insertList(listFromFile);
    }

    @AfterTest
    public void removeAllAddedDataFromDB() {
        cityDb.deleteList(listFromFile);
    }

    @Test
    public void testInsertData() {
        List<City> insertedList = cityDb.getAll();
        Assert.assertFalse(insertedList.isEmpty(), "DB doesn't contains any records!");
        Assert.assertTrue(insertedList.containsAll(listFromFile), "Some data is corrupted or not added to the DB!");
    }

    @Test
    public void testUpdateRandomData() {
        int randomCityListId = GeneratorInfo.generateRandomInt(listFromFile.size());

        int prevID = listFromFile.get(randomCityListId).getId();

        listFromFile.get(randomCityListId).setCountry(GeneratorInfo.generateRandomCountryName());
        listFromFile.get(randomCityListId).setName(GeneratorInfo.generateRandomShortCountryName());
        listFromFile.get(randomCityListId).setId(GeneratorInfo.generateRandomInt());
        listFromFile.get(randomCityListId).setLat(GeneratorInfo.generateRandomCoordinate());
        listFromFile.get(randomCityListId).setLon(GeneratorInfo.generateRandomCoordinate());

        City localUpdatedCity = listFromFile.get(randomCityListId);

        cityDb.update(prevID, listFromFile.get(randomCityListId));

        List<City> cityList = cityDb.get(localUpdatedCity.getId());

        Assert.assertFalse(cityList.isEmpty(), "Updated data is not found in DB");

        Assert.assertTrue(cityList.contains(localUpdatedCity),"Updated data is not correspond in DB!");
    }
}
