package ua.nikolenko.WeatherSensorRESTApp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "scanner")
@ToString
public class Scanner implements Serializable {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "scanner_name")
    @NotEmpty
    @Size(min = 3, max = 30, message = "Scanner name must be between 3 and 30 characters")
    private String scannerName;

    @Getter
    @Setter
    private LocalDateTime registeredAt;

    public Scanner() {
    }

    public Scanner(String scannerName) {
        this.scannerName = scannerName;
    }
}
