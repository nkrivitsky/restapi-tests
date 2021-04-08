package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class City {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("coord")
    private Coord coord = new Coord();

    public City() {
    }

    public City(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            country = resultSet.getString("country");
            coord.setLat(resultSet.getDouble("lat"));
            coord.setLon(resultSet.getDouble("lon"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLon() {
        return coord.getLon();
    }

    public void setLon(Double lon) {
        coord.setLon(lon);
    }

    public Double getLat() {
        return coord.getLat();
    }

    public void setLat(Double lat) {
        coord.setLat(lat);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        City city = (City) o;
        return id.equals(city.id) &&
                name.equals(city.name) &&
                country.equals(city.country) &&
                getLat().equals(city.getLat()) &&
                getLon().equals(city.getLon());
    }
}
