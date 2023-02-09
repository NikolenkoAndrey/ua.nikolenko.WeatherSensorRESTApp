package ua.nikolenko.WeatherSensorRESTApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@NoArgsConstructor
@ToString
public class Measurement implements Serializable {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "temperature")
    @Min(value = -100)
    @Max(value = 100)
    @NotNull
    private Double temperature;

    @Getter
    @Setter
    @NotNull
    @Column(name = "raining")
    private Boolean raining;

    @Getter
    @Setter
    @NotNull
    @Column(name = "scanned_in")
    private LocalDateTime scannedIn;

    @Getter
    @Setter
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "scanner_name", referencedColumnName = "scanner_name")
    private Scanner scanner;

    public Measurement(Double temperature, Boolean raining) {
        this.temperature = temperature;
        this.raining = raining;
    }
}
