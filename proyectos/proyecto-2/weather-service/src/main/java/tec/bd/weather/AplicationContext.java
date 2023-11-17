package tec.bd.weather;

import com.mysql.cj.jdbc.MysqlDataSource;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;
import tec.bd.weather.repository.memory.InMemoryForecastRepository;
import tec.bd.weather.repository.sql.ForecastRepository;
import tec.bd.weather.service.WeatherService;
import tec.bd.weather.service.WeatherServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AplicationContext {

    private static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/weather_service";
    private static final String DB_USER = "weatherappuser";
    private static final String DB_PASSWORD = "weatherapppass";

    private DataSource mysqlDataSource;

    private Repository<Forecast, Integer> sqlForecastRepository;

    private Repository<Forecast, Integer> inMemoryForecastRepository;

    private WeatherService weatherService;

    public AplicationContext() {
        initMysqlDataSource();
        initDatabase();
        initInMemoryForecastRepository();
        initSQLForecastRepository();
        initWeatherService();
    }

    private void initMysqlDataSource() {
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setUrl(MYSQL_DB_URL);
        mysqlDS.setUser(DB_USER);
        mysqlDS.setPassword(DB_PASSWORD);
        this.mysqlDataSource = mysqlDS;
    }

    private void initDatabase() {
        try (Connection connection = mysqlDataSource.getConnection();
             Statement stmt = connection.createStatement()) {

            // Crear la base de datos si no existe
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS weather_service");

            // Seleccionar la base de datos
            stmt.executeUpdate("USE weather_service");

            // Configurar usuario y privilegios
            stmt.executeUpdate("CREATE USER IF NOT EXISTS '" + DB_USER + "'@'localhost' IDENTIFIED BY '" + DB_PASSWORD + "'");
            stmt.executeUpdate("GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON weather_service.* TO '" + DB_USER + "'@'localhost'");
        } catch (SQLException e) {
            throw new RuntimeException("Error al configurar la base de datos", e);
        }
    }

    private void initInMemoryForecastRepository() {
        this.inMemoryForecastRepository = new InMemoryForecastRepository();
    }

    private void initSQLForecastRepository() {
        this.sqlForecastRepository = new ForecastRepository(this.mysqlDataSource);
    }

    private void initWeatherService() {
        this.weatherService = new WeatherServiceImpl(this.sqlForecastRepository);
    }

    public Repository<Forecast, Integer> getInMemoryForecastRepository() {
        return this.inMemoryForecastRepository;
    }

    public WeatherService getWeatherService() {
        return this.weatherService;
    }

    public DataSource getMysqlDataSource() {
        return this.mysqlDataSource;
    }
}
