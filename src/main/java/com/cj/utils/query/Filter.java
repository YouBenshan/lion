package com.cj.utils.query;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Filter {

	private final Map<String, String> EQ = new HashMap<String, String>();
	private final Map<String, String> LIKE = new HashMap<String, String>();
	private final Map<String, String> GT = new HashMap<String, String>();
	private final Map<String, String> LT = new HashMap<String, String>();
	private final Map<String, String> GTE = new HashMap<String, String>();
	private final Map<String, String> LTE = new HashMap<String, String>();

	private final Map<String, String[]> IN = new HashMap<String, String[]>();

	public EnumMap<Operation, Map<String, String[]>> getPolybasics() {
		EnumMap<Operation, Map<String, String[]>> operations = new EnumMap<Operation, Map<String, String[]>>(
				Operation.class);
		operations.put(Operation.IN, this.IN);
		return operations;

	}

	public EnumMap<Operation, Map<String, String>> getUnitaries() {
		EnumMap<Operation, Map<String, String>> operations = new EnumMap<Operation, Map<String, String>>(
				Operation.class);
		operations.put(Operation.EQ, this.EQ);
		operations.put(Operation.LIKE, this.LIKE);
		operations.put(Operation.GT, this.GT);
		operations.put(Operation.LT, this.LT);
		operations.put(Operation.GTE, this.GTE);
		operations.put(Operation.LTE, this.LTE);

		return operations;

	}

}
