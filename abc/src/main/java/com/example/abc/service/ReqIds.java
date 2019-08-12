package com.example.abc.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求ids
 *
 * @author yhualiang
 */
public class ReqIds extends Ids {


	private Map<String, String> attr = new HashMap<>();

	public void setAttr(final String k, final String v) {
		if (k != null && v != null) {
			attr.put(k, v);
		}
	}

	public Map<String, String> getAttr() {
		return attr;
	}

	public String getAttr(final String k) {
		return attr.get(k);
	}

	public Set<String> attrName() {
		return attr.keySet();
	}

}
