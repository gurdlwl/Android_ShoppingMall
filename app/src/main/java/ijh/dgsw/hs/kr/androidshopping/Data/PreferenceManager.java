package ijh.dgsw.hs.kr.androidshopping.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String PREFERENCES_NAME = "user_info";
    private static final String DEFAULT_VALUE_STRING = "";

    private SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setString(Context context, String key, String value){
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(Context context, String key){
        SharedPreferences preferences = getPreferences(context);
        String value = preferences.getString(key, DEFAULT_VALUE_STRING);

        return value;
    }

    public void clear(Context context){
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
