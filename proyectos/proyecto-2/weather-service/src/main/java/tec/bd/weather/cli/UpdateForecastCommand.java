package tec.bd.weather.cli;

import java.util.Date;
import picocli.CommandLine;
import tec.bd.weather.AplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "update-forecast", aliases = { "uf" }, description = "Update existing forecast data")
public class UpdateForecastCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The new forecast id")
    private int newForecastId;
    
    @CommandLine.Parameters(paramLabel = "<country name>", description = "The new country name")
    private String countryName;
    
    @CommandLine.Parameters(paramLabel = "<city name>", description = "The new city name")
    private String cityName;
    
    @CommandLine.Parameters(paramLabel = "<zip Code>", description = "The zip Code")
    private String zipCode;
    
    @CommandLine.Parameters(paramLabel = "<forecast Date>", description = "The forecast date")
    private Date forecastDate;
    
    @CommandLine.Parameters(paramLabel = "<temperature>", description = "Temperature value")
    private float temperature;
    
    @Override
    public void run(){
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            var newForecast = new Forecast(newForecastId, countryName, cityName, zipCode, forecastDate, temperature);
            var updatedForecast = weatherService.updateForecast(newForecast);
            weatherService.newForecast(newForecast);
            System.out.println(updatedForecast);
        } catch (Exception e) {
            System.err.println("Can't update forecast. " + e.getMessage());
        }
    }
}

