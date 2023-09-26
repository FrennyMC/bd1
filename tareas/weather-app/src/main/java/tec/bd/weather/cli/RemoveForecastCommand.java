package tec.bd.weather.cli;

import picocli.CommandLine;
import tec.bd.weather.AplicationContext;


@CommandLine.Command(name = "remove-forecast", aliases = { "rf" }, description = "Delete a forecast")
public class RemoveForecastCommand implements Runnable {
    @CommandLine.Parameters(paramLabel = "<forecast id>", description = "The forecast id to delete")
    private int forecastId;

    @Override
    public void run() {
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            weatherService.removeForecast(forecastId);
            System.out.println("Forecast with ID " + forecastId + " Forecast remove successfully.");

        } catch (Exception e) {
            System.err.println("Can't remove forecast. " + e.getMessage());
        }
    }
}

