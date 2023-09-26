package tec.bd.weather;

import org.sqlite.SQLiteDataSource;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;
import tec.bd.weather.repository.sql.ForecastRepository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

import javax.sql.DataSource;

public class AplicationContext {
    
    private static final String SQLITE_DB_URL = "jdbc:sqlite::resource:sqlite/weather-service.db";
    
    private DataSource sqliteDataSource;
    
    private Repository<Forecast, Integer> sqlForecastRepository;
    
    private Repository<Forecast, Integer> inMemoryForecastRepository;    
    
    private WeatherService weatherService;
    
    public AplicationContext(){
        initSqliteDataSource();
        initInMomeoryForecastRepository();
        initSQLForecastRepository();
        initWeatherService();
    }
    private void initSqliteDataSource(){
        var sqliteDS = new SQLiteDataSource();
        sqliteDS.setUrl(SQLITE_DB_URL);
        this.sqliteDataSource = sqliteDS;
    }
    
    private void initInMomeoryForecastRepository(){
        this.inMemoryForecastRepository = new InMemoryForecastRepository();
    }
    
    private void initSQLForecastRepository(){
        this.sqlForecastRepository = new ForecastRepository(this.sqliteDataSource);
    }
    
    private void initWeatherService(){
        this.weatherService = new WeatherServiceImpl(this.sqlForecastRepository);
    }
    
    public Repository<Forecast, Integer> getInMemoryForecastRepository(){
        return this.inMemoryForecastRepository;
    }
    public WeatherService getWeatherService(){
        return this.weatherService;
    }
    
    public DataSource getSqliteDataSource() {
        return this.sqliteDataSource;
    }

}

