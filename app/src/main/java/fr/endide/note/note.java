package fr.endide.note;

import android.text.Editable;

public class note {
    String name;
    String content;

    public note(String text, String s) {
        this.name = text;
        this.content = s;
    }
    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
}
