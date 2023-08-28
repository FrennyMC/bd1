 package tec.bd.weather.service;

import tec.bd.weather.entity.Weather;
import tec.bd.weather.repository.Repository;
 
 
public class WeatherServiceImpl implements WeatherService{

    private Repository<Weather, Integer> weatherRepository;
    
    public WeatherServiceImpl(Repository<Weather, Integer> weatherRepository){
        this.weatherRepository = weatherRepository;
    }
    @Override
    public float getCityTemperature(String city){
        var weather = this.weatherRepository
                .findAll()
                .stream()
                .filter(e -> e.getCityName().equals(city))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(city + " is not supported"));
        return weather.getTemperature();
    }
    @Override
    public float getZipCodeTemperature(String zipCode){
        return 0;
    }
    
    @Override
    public void newForecast(Weather newWeather) {
        Weather.validate(newWeather);
        var current = this.weatherRepository.findById(newWeather.getId());
        if (current.isPresent()){
            throw new RuntimeException("Weather forecast ID already exist in database");
        }
        
        this.weatherRepository.save(newWeather);
    }
}
