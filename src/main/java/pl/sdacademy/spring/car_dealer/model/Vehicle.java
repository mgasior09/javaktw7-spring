package pl.sdacademy.spring.car_dealer.model;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
public class Vehicle extends BaseModel implements Serializable {
    @NotNull
    @Size(min = 1, max = 200)
    private String manufacturer;
    @NotNull
    @Size(min = 1, max = 200)
    private String model;
    @NotNull
    @Min(1801)
    private Long productionYear;
    @NotNull
    @Min(1)
    @Max(178000)
    private Long mileage;
    @NotNull
    @Pattern(regexp = "Oil|Gas|Petrol|Electric", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String fuel;
    @NotNull
    @Min(1)
    private Long price;
    private boolean sold;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
