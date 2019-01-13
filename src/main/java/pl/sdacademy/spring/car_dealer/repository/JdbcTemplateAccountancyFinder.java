package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import pl.sdacademy.spring.car_dealer.annotation.Finder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Finder
public class JdbcTemplateAccountancyFinder implements AccountancyFinder {
    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_DATE = "SELECT date, price= FROM Purchase";

    public JdbcTemplateAccountancyFinder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Map<LocalDate, Long> purchasesValueByDay() {
        return jdbcTemplate.query(FIND_BY_DATE, new DayPurchasesExtractor());
    }

    private class DayPurchasesExtractor implements ResultSetExtractor<Map<LocalDate, Long>> {


        @Override
        public Map<LocalDate, Long> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<LocalDate, Long> purchasesDay = new HashMap<>();
            while (resultSet.next()) {
                purchasesDay.merge(resultSet.getDate("date").toLocalDate(), resultSet.getLong("price"), Long::sum);
            }
            return purchasesDay;
        }
    }
}

//    private final String FIND_BY_CUSTOMER = "SELECT id, vehicleId, customerId, date, price, version FROM Purchase" +
//            "LEFT outer join Customer on customer.id=? ";
