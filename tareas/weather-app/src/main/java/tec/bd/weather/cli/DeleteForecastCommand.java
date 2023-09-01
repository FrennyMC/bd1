package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.AplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "delete-forecast", aliases = { "df" }, description = "Delete a forecast")
public class DeleteForecastCommand {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id to delete")
    private int forecastId;
    
    public void run(){
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            
            // Aquí obtienes el pronóstico existente que deseas eliminar
            var existingForecast = weatherService.getForecastById(forecastId);
            
            if (existingForecast != null) {
                // Si el pronóstico existe, lo borras
                weatherService.deleteForecast(existingForecast.getId());
                System.out.println("Forecast deleted successfully.");
            } else {
                System.out.println("Forecast with ID " + forecastId + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Can't delete forecast. " + e.getMessage());
        }
    }
}
