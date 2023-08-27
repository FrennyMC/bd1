package tec.bd.weather;

/**
 *
 * @FMC
 */

public class WeatherServiceImpl implements WeatherService{
    public WeatherServiceImpl(){
    }
    @Override
    public float getTemperature(String city){
        return 22.0f; 
    }
}
