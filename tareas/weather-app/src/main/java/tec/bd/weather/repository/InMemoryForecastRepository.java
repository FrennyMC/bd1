package tec.bd.weather.repository;

import java.util.*;

import tec.bd.weather.entity.Forecast;

public class InMemoryForecastRepository implements Repository<Forecast, Integer>{
    
    private Set<Forecast> inMemoryForecastData;
    
    public InMemoryForecastRepository(){
        // "Inicializando" la base de Datos 
        this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData.add(new  Forecast(1, "Alajuela","Costa Rica","10101",23.0f));
        this.inMemoryForecastData.add(new  Forecast(2, "Cartago","Costa Rica","20201",24.0f));
        this.inMemoryForecastData.add(new  Forecast(3, "San Jose","Costa Rica","30301",25.0f));
        this.inMemoryForecastData.add(new  Forecast(4, "Limon","Costa Rica","40401",26.0f));
        
    }
    
    @Override
    public Optional<Forecast> findById(Integer id) {
        return this.inMemoryForecastData
                .stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst();
    }

    @Override
    public List<Forecast> findAll() {
        return new ArrayList<>(this.inMemoryForecastData);
    }

    @Override
    public void save(Forecast weather) {
        this.inMemoryForecastData.add(weather);
    }

    @Override
    public void delete(Integer id) {
        var weatherToDelete = this.findById(id);
        this.inMemoryForecastData.remove(weatherToDelete.get());
    }

    @Override
    public Forecast update(Forecast source) {
        
        var current = this.findById(source.getId()).get();
        
        current.setCountryName(source.getCountryName());
        current.setCityName(source.getCityName());
        current.setZipCode(source.getZipCode());
        current.setTemperature(source.getTemperature());
        
        // borramos el objeto existente y lo reeplazamos por 
        // el actualizado.
        this.delete(current.getId());
        this.save(current);
        
        return current;
    }
    
    //@Override
    public void removeForecast(int forecastId) {
        var forecastToRemove = findById(forecastId);
        forecastToRemove.ifPresent(forecast -> this.inMemoryForecastData.remove(forecast));
    }
    
    
}
