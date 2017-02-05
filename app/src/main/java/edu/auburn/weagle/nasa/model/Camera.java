package edu.auburn.weagle.nasa.model;

import java.io.Serializable;

/**
 * Camera model
 * Author: Gary
 * Time: 17/2/4
 */

public class Camera implements Serializable{
    private int id;
    private CameraType type;
    private int rover_id;
    private String full_name;
    private String name;

    /**
     * To get the short name of camera
     * @return short_name of the camera
     */
    public String getName() {
        return name;
    }

    /**
     * To set the short name of camera
     * @param name of the camera
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * To get the ID of camera
     * @return camera's ID
     */
    public int getId() {
        return id;
    }

    /**
     * To set the ID of camera
     * @param id of the camera
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * To get the type of the camera
     * @return type of the camera
     */
    public CameraType getType() {
        return type;
    }

    /**
     * To set the type of the camera
     * @param type of the camera
     */
    public void setType(CameraType type) {
        this.type = type;
    }

    /**
     * To get the rover ID
     * @return rover_id
     */
    public int getRover_id() {
        return rover_id;
    }

    /**
     * To set the rover ID
     * @param rover_id
     */
    public void setRover_id(int rover_id) {
        this.rover_id = rover_id;
    }

    /**
     * To get full name of the camera
     * @return full_name of the camera
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     * To set full name of the camera
     * @param full_name of the camera
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", type=" + type +
                ", rover_id=" + rover_id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
