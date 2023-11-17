package tec.bd.weather.cli;


import java.util.List;
import picocli.CommandLine;
import tec.bd.weather.AplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "all-forecasts", aliases = {"af"}, description = "Get all forecasts from the database")
public class AllForecastCommand implements Runnable{
    
    @Override
    public void run() {
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            List<Forecast> allForecasts = weatherService.getAllForecasts();
            for (Forecast forecast : allForecasts) {
                System.out.println(forecast);
            }
        } catch (Exception e) {
            System.err.println("Can't retrieve forecasts. " + e.getMessage());
        }
    }
}
