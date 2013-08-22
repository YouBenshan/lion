package com.cj.utils.query;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.Specification;

@Setter
@Getter
public class Filter<T> {

	private final Map<String, String> EQ = new HashMap<String, String>();
	private final Map<String, String> LIKE = new HashMap<String, String>();
	private final Map<String, String> GT = new HashMap<String, String>();
	private final Map<String, String> LT = new HashMap<String, String>();
	private final Map<String, String> GTE = new HashMap<String, String>();
	private final Map<String, String> LTE = new HashMap<String, String>();

	private final Map<String, String[]> IN = new HashMap<String, String[]>();

	private Specification<T> specification;

	public EnumMap<Operation, Map<String, String>> getUnitaries() {
		EnumMap<Operation, Map<String, String>> operations = new EnumMap<Operation, Map<String, String>>(
				Operation.class);
		operations.put(Operation.EQ, EQ);
		operations.put(Operation.LIKE, LIKE);
		operations.put(Operation.GT, GT);
		operations.put(Operation.LT, LT);
		operations.put(Operation.GTE, GTE);
		operations.put(Operation.LTE, LTE);

		return operations;

	}

	public EnumMap<Operation, Map<String, String[]>> getPolybasics() {
		EnumMap<Operation, Map<String, String[]>> operations = new EnumMap<Operation, Map<String, String[]>>(
				Operation.class);
		operations.put(Operation.IN, IN);
		return operations;

	}

}
