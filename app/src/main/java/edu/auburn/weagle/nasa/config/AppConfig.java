package edu.auburn.weagle.nasa.config;

/**
 * Author: Gary
 * Time: 17/2/3
 */

public class AppConfig {
    public final static String[] types = {"FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM","PANCAM","MINITES"};

    public final static String SERVER = "https://api.nasa.gov";
    public final static String API_KEY = "&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
    public final static String sample = SERVER+"/mars-photos/api/v1/rovers/curiosity/photos?sol=1000"+API_KEY;

}
