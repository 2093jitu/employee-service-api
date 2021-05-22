package com.dynamicform.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum DateRangCriteria {

	TODAY("TODAY", "Today"), 
	THIS_WEEK("THIS_WEEK", "This Week"), 
	THIS_MONTH("THIS_MONTH", "This Month"),
	THIS_YEAR("THIS_YEAR", "This Year"), 
	DATE_BETWEEN("DATE_BETWEEN", "Date Between");

	private final String value;
	private final String dateRange;

	DateRangCriteria(String value, String dateRange) {
		this.value = value;
		this.dateRange = dateRange;
	}

	public String getValue() {
		return value;
	}

	public String getDateRange() {
		return dateRange;
	}

	public static List<Map<String, Object>> getDateCriteriaList() {

		List<Map<String, Object>> getDateCriteriaList = new ArrayList<Map<String, Object>>();

		for (DateRangCriteria r : DateRangCriteria.values()) {

			Map<String, Object> getDateCriteriaMap = new HashMap<String, Object>();
			getDateCriteriaMap.put("id", r.getValue());
			getDateCriteriaMap.put("name", r.getDateRange());

			getDateCriteriaList.add(getDateCriteriaMap);
		}
		return getDateCriteriaList;
	}

}
