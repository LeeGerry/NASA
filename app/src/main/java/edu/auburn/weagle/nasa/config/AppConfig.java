package edu.auburn.weagle.nasa.config;

import edu.auburn.weagle.nasa.R;

/**
 * Author: Gary
 * Time: 17/2/3
 */

public class AppConfig {
    public static final String[] names = {"Curiosity", "Opportunity", "Spirit",
            "Customize","Setting","About"};

    public static final int[] icons = {R.drawable.fun_one_selector,
            R.drawable.fun_two_selector,R.drawable.fun_three_selector,
            R.drawable.fun_four_selector,
            R.drawable.fun_setting_selector,
            R.drawable.fun_desc_selector};
    public final static String[] ROVER_NAMES = {"Curiosity","Opportunity","Spirit"};

    public final static String[] types = {"FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM","PANCAM","MINITES"};
    public final static String[] types1 = {"FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","NAVCAM"};
    public final static String[] types2 = {"FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"};

    public final static String SERVER = "https://api.nasa.gov";
    public final static String API_KEY = "&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";

    public final static String sample = SERVER+"/mars-photos/api/v1/rovers/curiosity/photos?sol=1000"+API_KEY;
    public final static String URL_FUNC1 = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=none&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
}
