package com.geocoder.demo.entities;

public class Greeting {

	private String greeting;
	
	public Greeting(String greeting){
		this.greeting = greeting;
	}
	
	public Greeting(){
		
	}
	
	public String getGreeting(){
		return this.greeting;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((greeting == null) ? 0 : greeting.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Greeting other = (Greeting) obj;
		if (greeting == null) {
			if (other.greeting != null)
				return false;
		} else if (!greeting.equals(other.greeting))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Greeting [greeting=" + greeting + "]";
	}
}
