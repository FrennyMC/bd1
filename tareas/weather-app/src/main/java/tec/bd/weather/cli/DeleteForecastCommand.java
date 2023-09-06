package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.AplicationContext;

@CommandLine.Command(name = "delete-forecast", aliases = { "df" }, description = "Delete a forecast")
public class DeleteForecastCommand {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id to delete")
    private int forecastId;
    
    public void run(){
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            
            // Aquí llamamos al método removeForecast para eliminar el pronóstico
            weatherService.removeForecast(forecastId);
            System.out.println("Forecast with ID " + forecastId + " deleted successfully.");
        } catch (Exception e) {
            System.err.println("Can't delete forecast. " + e.getMessage());
        }
    }
}
