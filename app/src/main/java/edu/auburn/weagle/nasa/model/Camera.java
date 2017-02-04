package edu.auburn.weagle.nasa.model;

/**
 * Camera model
 * Author: Gary
 * Time: 17/2/4
 */

public class Camera {
    private int id;
    private CameraType type;
    private int rover_id;
    private String full_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CameraType getType() {
        return type;
    }

    public void setType(CameraType type) {
        this.type = type;
    }

    public int getRover_id() {
        return rover_id;
    }

    public void setRover_id(int rover_id) {
        this.rover_id = rover_id;
    }

    public String getFull_name() {
        return full_name;
    }

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
