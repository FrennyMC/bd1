package tec.bd.weather.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import tec.bd.weather.entity.Forecast;
import tec.bd.weather.repository.InMemoryForecastRepository;
public class WeatherServiceImplTest {
    @Test
    public void GivenACity_WhenCityIsSupported_ThenReturnTemperature() {
        
        // Arrange
        
        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = mock(Forecast.class);
        
        
        given(forecast.getCityName()).willReturn("Alajuela");
        given(forecast.getTemperature()).willReturn(23.0f);
        given(forecastRepository.findAll()).willReturn(List.of(forecast));
        
        // Act
        var actual = weatherService.getCityTemperature("Alajuela");

        // Assert 

        verify(forecastRepository, times(1)).findAll();
        verify(forecast, times(1)).getCityName();
        verify(forecast, times(1)).getTemperature();

        assertThat(actual).isEqualTo(23.0f);
    }
    // Implementar
    
    @Test
    public void GivenACity_WhenCityIsNotSupported_ThenException(){
        // Arrange
        
        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);
        
        given(forecastRepository.findAll()).willReturn(Collections.emptyList());
        
        // Act
        try {
            weatherService.getCityTemperature("Alajuela");
            fail("We Should'nt reach this line!");  
        } catch (Exception e){

        }
        // Assert 

        verify(forecastRepository, times(1)).findAll();
    }
    
    @Test
    public void GivenAValidForecast_WhenCreateNewForecast_ThenForecastIsCreated() {
        
        // Arrange
        
        var forecastRepository = mock(InMemoryForecastRepository.class);
        
        given(forecastRepository.findById(anyInt())).willReturn(Optional.empty());
        
        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f );
        
        // Act
        weatherService.newForecast(forecast);

        // Assert 

        verify(forecastRepository, times(1)).findById(5);
        verify(forecastRepository, times(1)).save(forecast);
    }
    
    @Test
    public void GivenExistingForecast_WhenCreateNewForecast_ThenServiceException() {
        // Arrange
        
        var forecastRepository = mock(InMemoryForecastRepository.class);
        
        given(forecastRepository.findById(anyInt())).willReturn(Optional.of(new Forecast()));
        
        var weatherService = new WeatherServiceImpl(forecastRepository);
        var forecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f );
        
        // Act
        try {
            weatherService.newForecast(forecast);
            fail("We Should'nt reach this line!");  
        } catch (Exception e){

        }
        // Assert 

        verify(forecastRepository, times(1)).findById(5);
        verify(forecastRepository, never()).save(forecast);
    }
    // Implementar
    
    // Si se intenta ingresar un nuevo Forecast con miembros inválidos, el metodo no debe llamar al repositorio
    @Test
    public void GivenAInvalidForecast_WhenCreateNewForecast_ThenServiceException() {
        // Arrange
        var invalidForecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), -5.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        // Act
        Throwable exception = catchThrowable(() -> weatherService.newForecast(invalidForecast));

        // Assert
        assertThat(exception).isInstanceOf(RuntimeException.class)
                .hasMessage("Invalid temperature value");

        // Verificar que no se llamó al repositorio para guardar el pronóstico
        verify(forecastRepository, never()).save(invalidForecast);
    }
    
    // Prueba unitaria para probar que una actualización es exitosa

    @Test
    public void GivenValidForecast_WhenUpdatingTemperature_ThenNewTemperature(){
        // Arrange
        
        var currentForecast = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 23.0f );
        var forecastToBeUpdated = new Forecast(5, "Costa Rica", "Limon", "33122", new Date(), 19.0f );
        var forecastRepository = mock(InMemoryForecastRepository.class);
        
        given(forecastRepository.findById(anyInt())).willReturn(Optional.of(currentForecast));
        given(forecastRepository.update(forecastToBeUpdated)).willReturn(forecastToBeUpdated);
        given(forecastRepository.findAll()).willReturn(List.of(currentForecast));
        
        var weatherService = new WeatherServiceImpl(forecastRepository);
        // Act
        
        var oldForecast = weatherService.getCityTemperature("Limon");
        var actual = weatherService.updateForecast(forecastToBeUpdated);
        // Assert 
        verify(forecastRepository, times(1)).findById(5);
        verify(forecastRepository, times(1)).update(forecastToBeUpdated);
        
        assertThat(actual).isNotSameAs(oldForecast);
        
        assertThat(actual.getId()).isEqualTo(5);
        assertThat(actual.getZipCode()).isEqualTo("33122");
        assertThat(actual.getCountryName()).isEqualTo("Costa Rica");
        assertThat(actual.getCityName()).isEqualTo("Limon");
        assertThat(actual.getTemperature()).isEqualTo(19.0f);
    }
    // Prueba unitaria para probar que un Forecast que No exista no pueda ser actualizado
    
    @Test
    public void GivenNonExistentForecast_WhenUpdatingTemperature_ThenException() {
        // Arrange
        var forecastId = 5;
        var forecastToBeUpdated = new Forecast(forecastId, "Costa Rica", "Limon", "33122", new Date(), 19.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        given(forecastRepository.findById(forecastId)).willReturn(Optional.empty());

        // Act and Assert
        try {
            weatherService.updateForecast(forecastToBeUpdated);
            fail("Should throw an exception");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Weather forecast ID doesn't exist in database");
        }

        // Assert
        verify(forecastRepository, never()).update(forecastToBeUpdated);
    }
    
    // Prueba unitaria para probar que un Forecast que tiene miembros inválidos, el metodo no debe llamar al repositorio
    @Test
    public void GivenInvalidForecast_WhenUpdatingTemperature_ThenException() {
        // Arrange
        var forecastId = 5;
        var invalidForecast = new Forecast(forecastId, "Costa Rica", "Limon", "33122", new Date(), -5.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        // Act and Assert
        try {
            weatherService.updateForecast(invalidForecast);
            fail("Should throw an exception");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Invalid temperature value");
        }

        // Assert
        verify(forecastRepository, never()).update(invalidForecast);
    }
    
    // Prueba unitaria para probar la eliminación exitosos de un Forecast
    
    @Test
    // Buenooooo
    public void GivenExistingForecast_WhenDeletingForecast_ThenForecastIsDeleted() {
        // Arrange
        var forecastId = 5;
        var forecastToDelete = new Forecast(forecastId, "Costa Rica", "Limon", "33122", new Date(), 23.0f);

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        given(forecastRepository.findById(forecastId)).willReturn(Optional.of(forecastToDelete));

        // Act
        weatherService.removeForecast(forecastId);

        // Assert
        verify(forecastRepository, times(1)).findById(forecastId);
        verify(forecastRepository, times(1)).delete(forecastId);
    }

    // Prueba unitaria para probar que un Forecast Id que NO exista no pueda ser eliminado 
    
    @Test
    public void GivenNonExistentForecast_WhenDeletingForecast_ThenException() {
        // Arrange
        var forecastId = 99;

        var forecastRepository = mock(InMemoryForecastRepository.class);
        var weatherService = new WeatherServiceImpl(forecastRepository);

        given(forecastRepository.findById(forecastId)).willReturn(Optional.empty());

        // Act and Assert
        try {
            weatherService.removeForecast(forecastId);
            fail("Should throw an exception");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Weather forecast ID doesn't exist in database");
        }

        // Assert
        verify(forecastRepository, never()).delete(forecastId);
    }
    
}
