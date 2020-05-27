package com.nasa.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {

    private long id;
    
	@JsonProperty("earth_date")
    private String earthDate;

	@JsonProperty("img_src")
    private String imgSrc;
//    private Camera camera;
    private Rover rover;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

//    public Camera getCamera() {
//        return camera;
//    }
//
//    public void setCamera(Camera camera) {
//        this.camera = camera;
//    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }
}
