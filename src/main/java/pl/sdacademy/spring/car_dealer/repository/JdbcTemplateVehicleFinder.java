package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.sdacademy.spring.car_dealer.annotation.Finder;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.finders.VehicleFinder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Finder
public class JdbcTemplateVehicleFinder implements VehicleFinder {
    private  final JdbcTemplate jdbcTemplate;
    private static final String COUNT_ALL = "SELECT count (*) FROM Vehicle";
    private static final String COUNT_ALL_AVAILABLE = COUNT_ALL + " WHERE sold = false";
    private static final String SELECT_ALL = "SELECT id, " +
            "manufacturer, model, productionYear, " +
            "mileage, fuel, price, sold, version FROM Vehicle";

    private final String SELECT_BY_SOLD = SELECT_ALL + " WHERE sold =?";

    JdbcTemplateVehicleFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Vehicle> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    @Override
    public List<Vehicle> getAvailable() {
        return jdbcTemplate.query(SELECT_BY_SOLD, new Object[]{false}, new VehicleRowMapper());
    }

    @Override
    public Long totalCount() {
        return jdbcTemplate.queryForObject(COUNT_ALL, Long.class);
    }

    @Override
    public Long totalCountForAvailable() {
        return jdbcTemplate.queryForObject(COUNT_ALL_AVAILABLE, Long.class);
    }

    private class VehicleRowMapper implements RowMapper<Vehicle> {
        @Override
        public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(resultSet.getLong("id"));
            vehicle.setManufacturer(resultSet.getString("manufacturer"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setProductionYear(resultSet.getLong("productionYear"));
            vehicle.setMileage(resultSet.getLong("mileage"));
            vehicle.setFuel(resultSet.getString("fuel"));
            vehicle.setPrice(resultSet.getLong("price"));
            vehicle.setSold(resultSet.getBoolean("sold"));
            vehicle.setVersion(resultSet.getLong("version"));
            return vehicle;
        }
    }

}
