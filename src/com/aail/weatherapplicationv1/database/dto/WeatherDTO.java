package com.aail.weatherapplicationv1.database.dto;


public class WeatherDTO implements DTO
{
	private String idWeather;
	private String temperature;
	private String pressure;
	private String windspeed;
	private String precipitation;
	private String humidity;
	private String dewpoint;
	private String date;
	private String sealevel;
	private String groundlevel;
	private String weather;
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getWindspeed() {
		return windspeed;
	}
	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}
	public String getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getDewpoint() {
		return dewpoint;
	}
	public void setDewpoint(String dewpoint) {
		this.dewpoint = dewpoint;
	}
	public String getIdWeather() {
		return idWeather;
	}
	public void setIdWeather(String idWeather) {
		this.idWeather = idWeather;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSealevel() {
		return sealevel;
	}
	public void setSealevel(String sealevel) {
		this.sealevel = sealevel;
	}
	public String getGroundlevel() {
		return groundlevel;
	}
	public void setGroundlevel(String groundlevel) {
		this.groundlevel = groundlevel;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	
}