package tec.bd.weather.cli;

import java.util.Date;
import picocli.CommandLine;
import tec.bd.weather.AplicationContext;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.sql.ForecastRepository; // Importa ForecastRepository

@CommandLine.Command(name = "create-forecast", aliases = {"cf"}, description = "Create new forecast for a city")
public class CreateForecastCommand {
    
    
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
    
    //@Override
    public void run() {
        try {
            var appContext = new AplicationContext();
            var sqliteDataSource = appContext.getSqliteDataSource();
            var forecastRepository = new ForecastRepository(sqliteDataSource);
            var forecastToBeCreated = new Forecast(countryName, cityName, zipCode, forecastDate, temperature);
            var newForecast = forecastRepository.save(forecastToBeCreated);
            System.out.println(newForecast);
        } catch (Exception e) {
            System.err.println("Can't create forecast. " + e.getMessage());
        }
    }

}
