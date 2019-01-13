package pl.sdacademy.spring.car_dealer.repository.finders;

import java.time.LocalDate;
import java.util.Map;

public interface AccountancyFinder {
    Map<LocalDate, Long> purchasesValueByDay();

}
