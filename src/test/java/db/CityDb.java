package db;

import dao.IDao;
import models.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDb implements IDao<City> {
    private static final String SQL_GET_ATTRIBUTES = "SELECT id, name, country, lon, lat FROM CITY";
    private static final String SQL_INSERT = "INSERT INTO CITY (id, name, country, lon, lat) values (?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE CITY SET " +
            "id = ?, " +
            "name = ?, " +
            "country = ?, " +
            "lon = ?, " +
            "lat = ? " +
            " WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM CITY WHERE id = ?";
    private static final String SQL_DELETE_LIST = "DELETE FROM CITY WHERE id in (";
    private static final String SQL_DELETE_ALL = "DELETE FROM CITY";

    @Override
    public List<City> get(long id) {
        Connection conn = DatabaseConnection.getConnection();
        ResultSet rs;
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET_ATTRIBUTES)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> getAll() {
        Connection conn = DatabaseConnection.getConnection();
        ResultSet rs;
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET_ATTRIBUTES)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public void insert(City city) {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_INSERT)) {
            statement.setInt(1, city.getId());
            statement.setString(2, city.getName());
            statement.setString(3, city.getCountry());
            statement.setDouble(4, city.getLon());
            statement.setDouble(5, city.getLat());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(long cityId, City city) {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)) {
            statement.setInt(1, city.getId());
            statement.setString(2, city.getName());
            statement.setString(3, city.getCountry());
            statement.setDouble(4, city.getLon());
            statement.setDouble(5, city.getLat());
            statement.setDouble(6, cityId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(City city) {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, city.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_DELETE_ALL)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteList(List<City> cityList) {
        Connection conn = DatabaseConnection.getConnection();

        final int batchSize = 500;
        PreparedStatement statement = null;
        int count = 0;
        try {
            StringBuilder tempQuery = new StringBuilder(SQL_DELETE_LIST);
            for (City city : cityList) {

                tempQuery.append(city.getId());
                tempQuery.append(",");
                count++;
                if (count % batchSize == 0) {
                    tempQuery.replace(tempQuery.length() - 1, tempQuery.length(), ")");
                    statement = conn.prepareStatement(tempQuery.toString());
                    statement.execute();
                    tempQuery = new StringBuilder(SQL_DELETE_LIST);
                }
            }
            tempQuery.replace(tempQuery.length() - 1, tempQuery.length(), ")");
            statement = conn.prepareStatement(tempQuery.toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertList(List<City> cityList) {
        Connection conn = DatabaseConnection.getConnection();
        final int batchSize = 500;
        int count = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
            for (City city : cityList) {
                statement.setInt(1, city.getId());
                statement.setString(2, city.getName());
                statement.setString(3, city.getCountry());
                statement.setDouble(4, city.getLon());
                statement.setDouble(5, city.getLat());
                statement.addBatch();
                count++;
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
