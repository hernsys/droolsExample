package com.plugtree.dslExample.Params;

public class ParamSet {

	private int positionOrder;
	private String productCode;
	private String inclusionOperator;
	private int rangeValue;
	private int rangeMaximum;

	public ParamSet() {

	}

	public ParamSet(int positionOrder, String productCode,
			String inclusionOperator, int rangeValue, int rangeMaximum) {
		this.positionOrder = positionOrder;
		this.productCode = productCode;
		this.inclusionOperator = inclusionOperator;
		this.rangeValue = rangeValue;
		this.rangeMaximum = rangeMaximum;

	}

	public int getRangeMaximum() {
		return rangeMaximum;
	}

	public void setRangeMaximum(int rangeMaximum) {
		this.rangeMaximum = rangeMaximum;
	}

	public int getPositionOrder() {
		return positionOrder;
	}

	public void setPositionOrder(int positionOrder) {
		this.positionOrder = positionOrder;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getInclusionOperator() {
		return inclusionOperator;
	}

	public void setInclusionOperator(String inclusionOperator) {
		this.inclusionOperator = inclusionOperator;
	}

	public int getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(int rangeValue) {
		this.rangeValue = rangeValue;
	}

}