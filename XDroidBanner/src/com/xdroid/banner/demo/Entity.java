package com.xdroid.banner.demo;

/**
 * The Entity for demo
 * @author Robin
 * @since 2015-04-26 22:23:07
 * @Date_Last_Updated 2015-04-26 22:25:53
 */
public class Entity {
	private String id;
	private int imageUrl;

	public Entity() {
		super();
	}

	public Entity(String id, int imageUrl) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(int imageUrl) {
		this.imageUrl = imageUrl;
	}


	
}
