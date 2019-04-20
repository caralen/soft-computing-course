package hr.fer.zemris.fuzzy.task1.domains;

import java.util.Arrays;

public class DomainElement {

	private int[] values;

	public DomainElement(int[] values) {
		this.values = values;
	}
	
	public int getNumberOfComponents() {
		return values.length;
	}
	
	public int getComponentValue(int index) {
		return values[index];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
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
		DomainElement other = (DomainElement) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(values.length == 1) {
			return String.valueOf(values[0]);
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for(int value : values) {
				sb.append(value);
				sb.append(", ");
			}
			return sb.toString().substring(0, sb.length() - 2) + ")";
		}
	}
	
	public static DomainElement of(int ...values) {
		return new DomainElement(values);
	}
}
