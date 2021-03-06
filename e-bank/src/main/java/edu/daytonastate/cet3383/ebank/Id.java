package edu.daytonastate.cet3383.ebank;

public class Id {
	
	private final String id;
	
	public Id(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		
		if (obj instanceof Id) {
			Id anotherId = (Id) obj;
			equals = id.equals(anotherId.id);
		}
		
		return equals;
	}

	@Override
	public String toString() {
		return id;
	}
	
}