package com.aail.weatherapplicationv1;

public class Data {
	
	    public String description;
	    public int temperature;
		public int thumb;
	    public String location;
	    public int Max;
	    public int Min;
	    
	   
	  /*  public String getMax() {
			return Max;
		}
		public void setMax(String max) {
			Max = max;
		}
		public String getMin() {
			return Min;
		}
		public void setMin(String min) {
			Min = min;
		}*/
		public String getLocation() {
			return location;
		}
		public int getMax() {
			return Max;
		}
		public void setMax(int max) {
			Max = max;
		}
		public int getMin() {
			return Min;
		}
		public void setMin(int min) {
			Min = min;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public int getThumb() {
			return thumb;
		}
		public void setThumb(int thumb) {
			this.thumb = thumb;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		/*public String getTemperature() {
			return temperature;
		}
		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}*/
		public int getTemperature() {
			return temperature;
		}
		public void setTemperature(int temperature) {
			this.temperature = temperature;
		}
		
	
}
