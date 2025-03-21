package alcadienoquarkus.valueObject;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class Coordenada {

    @NotNull(message = "Latitude não pode ser nula")
    @DecimalMin(value = "-90.0", message = "Latitude mínima é -90")
    @DecimalMax(value = "90.0", message = "Latitude máxima é 90")
    private final Float latitude;

    @NotNull(message = "Longitude não pode ser nula")
    @DecimalMin(value = "-180.0", message = "Longitude mínima é -180")
    @DecimalMax(value = "180.0", message = "Longitude máxima é 180")
    private final Float longitude;

    public Coordenada(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        validar();
    }

    private void validar() {
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Latitude e Longitude não podem ser nulas");
        }
    }

    public static Coordenada parseStringToFloat(String latitudeStr, String longitudeStr) {
        Float latitude = Float.parseFloat(latitudeStr);
        Float longitude = Float.parseFloat(longitudeStr);
        return new Coordenada(latitude, longitude);
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenada that = (Coordenada) obj;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }
}
