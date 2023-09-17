/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tec.bd.weather.repository.sql;

/**
 *
 * @author usuario
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.Repository;

public class ForecastRepository implements Repository<Forecast,Integer>{
    @Override 
    public Optional<Forecast> findById(Integer integer){
        return Optional.empty();
    }
    
    @Override 
    public List<Forecast> findAll(){
        String url = "jbbc:sqlite::resource:sqlite/weather-service.db";
        
        try (Connection connection = DriverManager.getConnection(url)){
            System.out.println("Connected to SQLite database");
            
            String sql = "SELECT id, country_name, city_name, zip_code, forecast_date, temperature FROM forecast";
            
            try (Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)){
                
                while (rs.next()){
                      System.out.println(rs.getInt("forecast_id") + "\t" +
                              rs.getString("country_name") + "\t" +
                              rs.getString("city_name") + "\t" +
                              rs.getString("zip_code") + "\t" +
                              rs.getDate("forecast_Date") + "\t" +
                              rs.getFloat("temperature"));  
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }catch (SQLException e){
            System.err.println("Error connecting to database" + e.getMessage());
        }
        
        return Collections.emptyList();
    }
    
    @Override
    public void save(Forecast forecast){
        
    }
    
    @Override
    public void delete(Integer integer){
        
    }
    
    @Override
    public Forecast update(Forecast source){
        return null;
    }

}
