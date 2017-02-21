package com.tommy.web.model.appdata.vo;

/**
 * @author Tommy Lau
 */
public class AppVo {
    private Integer id;
    private String appName;
    private String appKey;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
