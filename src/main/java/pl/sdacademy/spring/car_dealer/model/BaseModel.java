package pl.sdacademy.spring.car_dealer.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseModel implements Serializable {
    @Version
    public Long version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
