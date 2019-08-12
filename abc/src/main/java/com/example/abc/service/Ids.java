package com.example.abc.service;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 基础IDS
 *
 * @author cly
 */
public class Ids implements Iterable<Entry<String, Object>>, Serializable {

	private Map<String, String> head = new HashMap<>();

	private Map<String, Object> body = new HashMap<>();

	public void setHead(final String k, final String v) {
		if (k != null && v != null) {
			head.put(k, v);
		}
	}

	public String getHead(final String k) {
		return head.get(k);
	}

	public void setBody(final String k, final Object v) {
		if (k != null && v != null) {
			body.put(k, v);
		}
	}

	public <T> T getBody(final String k, Class<T> clazz) {
		return (T) body.get(k);
	}

	public Map<String, String> getHead() {
		return head;
	}

	public void setHead(Map<String, String> head) {
		this.head = head;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	public Set<String> headNames() {
		return head.keySet();
	}

	public Set<String> bodyName() {
		return body.keySet();
	}

	/**
	 * 该方法请不要直接copy到其他系统使用，本系统做了特殊处理，body中会排除掉image-base64编码这个参数
	 * @return
	 */
	@Override
	public String toString() {
		Map<String, Object> printBody = new HashMap<>();
		for (Entry<String, Object> entry : body.entrySet()) {
			if (!"imgBase64".equals(entry.getKey())) {
				printBody.put(entry.getKey(), entry.getValue());
			}
		}
		return "{head=" + head + ", body=" + printBody + "}";
	}

	@Override
	public Iterator<Entry<String, Object>> iterator() {
		return this.body.entrySet().iterator();
	}


	@Override
	public Ids clone() {
		Ids ids = new Ids();
		head.forEach((k, v) -> {
			ids.setHead(k, v);
		});
		body.forEach((k, v) -> {
			ids.setBody(k, v);
		});
		return ids;
	}

	public ReqIds toResIds(){
		ReqIds req = new ReqIds();
		head.forEach((k, v)->{
			req.setHead(k, v);
		});
		return req;
	}

}
