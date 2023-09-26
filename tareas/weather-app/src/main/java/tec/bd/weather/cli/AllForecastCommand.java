package tec.bd.weather.cli;


import picocli.CommandLine;
import tec.bd.weather.AplicationContext;
import tec.bd.weather.entity.Forecast;

@CommandLine.Command(name = "all-forecasts", aliases = {"allf"}, description = "Get all forecasts from the database")
public class AllForecastCommand {
    // Puedes agregar opciones y argumentos según sea necesario
    // Por ejemplo, si deseas filtrar por algún criterio específico.
    
    // Implementa el método run para ejecutar el comando
    public void run() {
        try {
            var appContext = new AplicationContext();
            var weatherService = appContext.getWeatherService();
            var allForecasts = weatherService.getAllForecasts(); // Debes implementar este método en WeatherService
            for (Forecast forecast : allForecasts) {
                System.out.println(forecast);
            }
        } catch (Exception e) {
            System.err.println("Can't retrieve forecasts. " + e.getMessage());
        }
    }
}
