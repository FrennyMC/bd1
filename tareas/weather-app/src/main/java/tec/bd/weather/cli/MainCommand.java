package tec.bd.weather.cli;
 
import tec.bd.weather.cli.WeatherByZipCodeCommand;
import tec.bd.weather.cli.WeatherByCityCommand;
import picocli.CommandLine;

@CommandLine.Command(
    name = "Weather App",
    subcommands = {
        WeatherByCityCommand.class,
        WeatherByZipCodeCommand.class,
        CommandLine.HelpCommand.class
    },
    description = "Weather App Service by City and  Zip code")

public class MainCommand implements Runnable{
    @Override
    public void run() {
        
    }
}
