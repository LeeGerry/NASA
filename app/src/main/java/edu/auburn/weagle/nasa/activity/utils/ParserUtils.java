package edu.auburn.weagle.nasa.activity.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.weagle.nasa.model.Camera;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class ParserUtils {
    public static List<Photo> purser(String json){
        List<Photo> result = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray photos = jo.getJSONArray("photos");
            for(int i = 0;i<10;i++){
                Photo photo = new Photo();
                JSONObject model = (JSONObject) photos.get(i);
                photo.setImg_src(model.getString("img_src"));
                photo.setId(model.getInt("id"));
                photo.setSol(model.getInt("sol"));
                photo.setEarth_date(model.getString("earth_date"));
                Camera c = new Camera();
                JSONObject camera = model.getJSONObject("camera");
                c.setId(camera.getInt("id"));
                c.setName(camera.getString("name"));
                c.setRover_id(camera.getInt("rover_id"));
                c.setFull_name(camera.getString("full_name"));
                photo.setCamera(c);
                result.add(photo);
                System.out.println(photo.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
