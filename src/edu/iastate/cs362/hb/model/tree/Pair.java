package edu.iastate.cs362.hb.model.tree;


/*
 * Class holding a pair of two object
 */
public class Pair<T1, T2> {
	// First object
	public T1 fir;
	
	// Second object
	public T2 sec;
	
	// Creates a pair with the given items
	public Pair(T1 fir, T2 sec) {
		this.fir = fir;
		this.sec = sec;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fir == null) ? 0 : fir.hashCode());
		result = prime * result + ((sec == null) ? 0 : sec.hashCode());
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
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (fir == null) {
			if (other.fir != null)
				return false;
		} else if (!fir.equals(other.fir))
			return false;
		if (sec == null) {
			if (other.sec != null)
				return false;
		} else if (!sec.equals(other.sec))
			return false;
		return true;
	}
}
