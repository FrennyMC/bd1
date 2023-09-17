-- Table Forecast 

CREATE TABLE forecast(
    forecast_id INTEGER PRIMARY KEY AUTOINCREMENTE,
    country_name TEXT NOT NULL,
    city_name TEXT NOT NULL,
    zip_code TEXT NOT NULL,
    forecast_date DATE NOT NULL,
    temperature REAL NOT NULL,
);
