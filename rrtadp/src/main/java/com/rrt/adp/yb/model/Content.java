package com.rrt.adp.yb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Content {
	
	
	private List<Map<String, Object>> center;
	private Map<String, Object> footer;
	private Map<String, Object> header;
	private List<String> move;
	
	public Content() {
		this(new ArrayList<>());
	}
	
	public Content(List<String> contentUrls){
		this.center = new ArrayList<>();
		this.footer = new HashMap<>();
		this.header = new HashMap<>();
		
		footer.put("enabled", false);
		header.put("enabled", false);
		
		Map<String, Object> contentItem = new HashMap<>();
		contentItem.put("id", "row1_col1");
		contentItem.put("content", contentUrls);
		Map<String, Object> imageConfig = new HashMap<>();
		imageConfig.put("playTime", "5");
		imageConfig.put("isAutoPlay", "true");
		imageConfig.put("imagePlayType", "0");
		contentItem.put("imageDetail", imageConfig);
		Map<String, Object> containerConfig = new HashMap<>();
		containerConfig.put("height", "100%");
		containerConfig.put("width", "100%");
		containerConfig.put("left", "0%");
		containerConfig.put("top", "0%");
		contentItem.put("container", containerConfig);
		contentItem.put("type", "1");
		center.add(contentItem);
		
		this.move = new ArrayList<>();
		
	}

	public Map<String, Object> getFooter() {
		return footer;
	}

	public void setFooter(Map<String, Object> footer) {
		this.footer = footer;
	}

	public List<Map<String, Object>> getCenter() {
		return center;
	}

	public void setCenter(List<Map<String, Object>> center) {
		this.center = center;
	}

	public Map<String, Object> getHeader() {
		return header;
	}

	public void setHeader(Map<String, Object> header) {
		this.header = header;
	}

	public List<String> getMove() {
		return move;
	}

	public void setMove(List<String> move) {
		this.move = move;
	}

}
