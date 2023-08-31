
package tec.bd.weather.service;

import tec.bd.weather.entity.Forecast;

/**
 *
 * @FMC
 */
public interface WeatherService {
    float getCityTemperature(String city);
    
    float getZipCodeTemperature(String zipCode);
    
    void newForecast(Forecast weather);
    
    Forecast updateForecast(Forecast weather);
    
    //Implementar jsjsj 
    
    void removeForecast(int forecastId);
    
}
