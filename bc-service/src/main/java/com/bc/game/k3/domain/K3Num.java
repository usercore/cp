package com.bc.game.k3.domain;

public class K3Num {

	private Integer one;
	private Integer two;
	private Integer three;

	public K3Num(Integer one, Integer two, Integer three) {
		this.one = one;
		this.two = two;
		this.three = three;
	}

	public K3Num sort() {
		int temp = 0;
		if (one > two) {
			temp = one;
			one = two;
			two = temp;
		}

		if (two > three) {
			temp = two;
			two = three;
			three = temp;
			sort();
		}
		return this;
	}

	public Integer getOne() {
		return one;
	}

	public void setOne(Integer one) {
		this.one = one;
	}

	public Integer getTwo() {
		return two;
	}

	public void setTwo(Integer two) {
		this.two = two;
	}

	public Integer getThree() {
		return three;
	}

	public void setThree(Integer three) {
		this.three = three;
	}

	public String toString() {
		return "ome=" + one + " two=" + two + " three=" + three;
	}

	public int getSum() {
		return one + two + three;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + one;
		result = prime * result + ((two == null) ? 0 : two.hashCode());
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
		K3Num other = (K3Num) obj;
		if (one != other.one)
			return false;
		if (two != other.two)
			return false;
		if (three != other.three)
			return false;
		return true;
	}

}
