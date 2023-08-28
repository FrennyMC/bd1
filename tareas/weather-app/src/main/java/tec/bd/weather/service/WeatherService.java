
package tec.bd.weather.service;

import tec.bd.weather.entity.Weather;

/**
 *
 * @FMC
 */
public interface WeatherService {
    float getCityTemperature(String city);
    
    float getZipCodeTemperature(String zipCode);
    
    void newForecast(Weather weather);
}
