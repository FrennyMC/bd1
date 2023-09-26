
package tec.bd.weather.service;

import java.util.List;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

/**
 *
 * @FMC
 */
public interface WeatherService {
    float getCityTemperature(String city);
    
    float getZipCodeTemperature(String zipCode);
    
    //void newForecast(Forecast weather);
    
    List<Forecast> getAllForecasts();
    
    Forecast newForecast(Forecast weather);
    
    Forecast updateForecast(Forecast weather);
    
    //Implementar jsjsj 
    
    void removeForecast(int forecastId);
    
    Repository<Forecast, Integer> getWeatherRepository();
    
}
