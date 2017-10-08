package com.rrt.adp.yb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Content {
	
	private Map<String, Object> footer;
	private List<Map<String, Object>> center;
	private Map<String, Object> header;
	
	private String eDate;
	private String sDate;
	private String start;
	private String end;
	private String weekDay;
	private String runType;
	
	private String layoutType;
	private List<String> move;
	
	public Content() {
		this(new ArrayList<>());
	}
	
	public Content(List<String> contentUrls){
		this.footer = new HashMap<>();
		this.center = new ArrayList<>();
		this.header = new HashMap<>();
		
		footer.put("enabled", false);
		header.put("enabled", false);
		
		Map<String, Object> contentItem = new HashMap<>();
		contentItem.put("id", "row1_col1");
		contentItem.put("content", contentUrls);
		Map<String, Object> imageConfig = new HashMap<>();
		imageConfig.put("playTime", 5);
		imageConfig.put("isAutoPlay", true);
		imageConfig.put("imagePlayType", 0);
		contentItem.put("imageDetail", imageConfig);
		Map<String, Object> containerConfig = new HashMap<>();
		containerConfig.put("height", "100%");
		containerConfig.put("width", "100%");
		containerConfig.put("left", "0%");
		containerConfig.put("top", "0%");
		contentItem.put("container", containerConfig);
		contentItem.put("type", 1);
		center.add(contentItem);
		
		this.eDate = "";
		this.sDate = "";
		this.start = "00:00";
		this.end = "00:00";
		this.weekDay = "1,2,3,4,5,6,7";
		this.runType = "1";
		
		this.layoutType = "0";
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

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

	public String getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}

	public List<String> getMove() {
		return move;
	}

	public void setMove(List<String> move) {
		this.move = move;
	}
	

}
