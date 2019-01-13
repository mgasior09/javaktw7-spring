package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.sdacademy.spring.car_dealer.annotation.Finder;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.repository.finders.CustomerFinder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Finder
public class JdbcTemplateCustomerFinder implements CustomerFinder {
    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_BY_LAST_NAME = "SELECT id, name, surname, documentNo, telephone, version FROM Customer " +
            "WHERE surname like ?";
    private final String SELECT_BY_NAME = SELECT_BY_LAST_NAME + " or name like ?";

    public JdbcTemplateCustomerFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return jdbcTemplate.query(SELECT_BY_LAST_NAME, new Object[]{"%" + lastName + "%"}, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public List<Customer> searchByFirstOrLastName(String name) {
        return jdbcTemplate.query(SELECT_BY_NAME, new Object[]{"%" + name + "%", "%" + name + "%"}, new CustomerRowMapper());
    }

    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.setName(resultSet.getString("name"));
            customer.setSurname(resultSet.getString("surname"));
            customer.setDocumentNo(resultSet.getString("documentNo"));
            customer.setTelephone(resultSet.getString("telephone"));
            customer.setVersion(resultSet.getLong("version"));
            return customer;
        }
    }
}
