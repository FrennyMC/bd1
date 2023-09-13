package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.AplicationContext;


@CommandLine.Command(name = "delete-forecast", aliases = { "df" }, description = "Delete a forecast")
public class DeleteForecastCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id to delete")
    private int forecastId;

    public void run() {
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            
            // Intenta obtener el pronóstico del servicio utilizando el repositorio
            var forecastToRemove = weatherService.getWeatherRepository().findById(forecastId);

            if (forecastToRemove.isPresent()) {
                // Elimina el pronóstico si se encuentra
                weatherService.getWeatherRepository().delete(forecastId);
<<<<<<< HEAD
                System.out.println("Forecast with ID " + forecastId + " Forecast deleted successfully.");
=======
                System.out.println("Forecast deleted successfully.");
>>>>>>> tarea-3
            } else {
                System.err.println("Forecast with ID " + forecastId + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Can't delete forecast. " + e.getMessage());
        }
    }
}

