
package tec.bd.weather;

/**
 *
 * @FMC
 */
public interface WeatherService {
    float getCityTemperature(String city);
    
    float getZipCodeTemperature(String zipCode);
}
