package junior.global;

import java.util.Objects;

/** The global class for all 285b programs */
public class Commission implements Comparable {
	
	private int id, code;
	private double sales;
	
	public Commission(int id, int code, double sales) {
		this.id = id;
		this.code = code;
		this.sales = sales;
	}
	
	public int fetchId() { return id; }
	public int fetchCode() { return code; }
	public double fetchSales() { return sales; }
	
	public double fetchCommission() {
		if(code == 5 || code == 8 || code == 17) {
			if(code == 17) 
				return (sales >= 3500D ? ((3500D*0.095D) + (sales-3500D)*0.12D): (sales * 0.095D));
			else 
				return (sales >= 5000D ? ((5000D*0.075D) + (sales-5000D)*0.085D): (sales * 0.075D));
		}
		return -2;
	}

	@Override public int hashCode() { return Objects.hash(code, id, sales); }

	@Override public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Commission other = (Commission) obj;
		return code == other.code &&
				id == other.id &&
				Double.doubleToLongBits(sales) == Double.doubleToLongBits(other.sales);
	}
	
	@Override public String toString() {
		return "{Id:" + id + ", Code: " + code + ", Sales:" + sales + ", Com: " + fetchCommission() + "}";
	}

	@Override public int compareTo(Object obj) {
		if (getClass() != obj.getClass()) throw new RuntimeException("Class " + obj.getClass() + " is not a valid type for " + getClass());
		return Double.compare(fetchCommission(), ((Commission) obj).fetchCommission());
	}
}
