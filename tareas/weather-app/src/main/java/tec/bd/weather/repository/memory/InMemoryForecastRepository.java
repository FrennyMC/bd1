package tec.bd.weather.repository.memory;

import java.util.*;

import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

public class InMemoryForecastRepository implements Repository<Forecast, Integer>{
    
    private Set<Forecast> inMemoryForecastData;
    private int currentMaxId = 0;
    public InMemoryForecastRepository(){
        // "Inicializando" la base de Datos 
        this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData = new HashSet<>();
        this.inMemoryForecastData.add(new Forecast(1, "Alajuela", "Costa Rica", "10101", new Date(), 23.0f));
        this.inMemoryForecastData.add(new Forecast(2, "Cartago", "Costa Rica", "20201", new Date(), 24.0f));
        this.inMemoryForecastData.add(new Forecast(3, "San Jose", "Costa Rica", "30301", new Date(), 25.0f));
        this.inMemoryForecastData.add(new Forecast(5, "Limon", "Costa Rica", "40401", new Date(), 23.0f));
    }
    
    public int getCurrentMaxId(){
        return this.inMemoryForecastData
                .stream()
                .max(Comparator.comparing(Forecast::getId))
                .map(Forecast::getId)
                .orElse(0);
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
    public Forecast save(Forecast forecast) {
        forecast.setId(this.getCurrentMaxId() + 1);
        this.inMemoryForecastData.add(forecast);
        return forecast;
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
