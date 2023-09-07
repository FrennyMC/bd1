package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.AplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(
    name = "create-forecast",
    aliases = { "cf" },
    description = "Create new forecast for a city"
)
public class CreateForecastCommand implements Runnable {
    
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The new forecast id")
    private int newForecastId;
    
    @CommandLine.Parameters(paramLabel = "<country name>", description = "The new country name")
    private String countryName;
    
    @CommandLine.Parameters(paramLabel = "<city name>", description = "The new city name")
    private String cityName;
    
    @CommandLine.Parameters(paramLabel = "<zip Code>", description = "The zip Code")
    private String zipCode;
    
    @CommandLine.Parameters(paramLabel = "<temperature>", description = "Temperature value")
    private float temperature;
    
    @Override
    public void run() {
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            var newForecast = new Forecast(newForecastId, countryName, cityName, zipCode, temperature);
            
            // Verificar si ya existe un pronóstico con el mismo ID
            if (weatherService.getWeatherRepository().findById(newForecastId).isPresent()) {
                System.err.println("A forecast with ID " + newForecastId + " already exists.");
                return;
            }
            
            weatherService.newForecast(newForecast);
            System.out.println("Created new forecast:");
            System.out.println(newForecast);
        } catch (Exception e) {
            System.err.println("Can't create forecast. " + e.getMessage());
        }
    }
}
