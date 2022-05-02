package fr.endide.note;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class saveManager {
    private static final String LIST_KEY = "list_key";
    private static final String LIST_KEY2 = "list_key2";

    public static List<note> loadConfig(Context context){
        Gson gson = new Gson();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonToString = pref.getString(LIST_KEY, "");
        Type type = new TypeToken<ArrayList<note>>() {}.getType();
        List<note> list = gson.fromJson(jsonToString, type);
        return list;
    }
    public static void saveConfig(Context context, List<note> notes){
        Gson gson = new Gson();
        String jsonString = gson.toJson(notes);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }
}
