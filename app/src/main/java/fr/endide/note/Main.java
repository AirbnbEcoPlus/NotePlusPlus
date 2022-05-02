package fr.endide.note;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class Main extends Activity {
    List<note> notesSaves = new ArrayList<>();
    EditText nameNote;
    EditText contentNote;
    Button addButton;
    Button saveButton;
    Button delButton;
    ListView listNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesSaves = saveManager.loadConfig(getApplicationContext());
        loadScene1();
    }
    public void loadScene1(){
        setContentView(R.layout.scene1);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadScene2WithEmptyNote();
            }
        });
        loadListView();
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadScene2WithNote(notesSaves.get(i).name, notesSaves.get(i).content, i);
            }
        });
    }
    public void loadScene2WithNote(String name, String content, int i){
        setContentView(R.layout.scene2);
        delButton = findViewById(R.id.delButton);
        saveButton = findViewById(R.id.saveButton);
        nameNote = findViewById(R.id.nameNoteField);
        contentNote = findViewById(R.id.contentNoteField);
        nameNote.setText(name);
        contentNote.setText(content);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                note note = new note(nameNote.getText().toString(), contentNote.getText().toString());
                notesSaves.set(i, note);
                saveManager.saveConfig(getApplicationContext(), notesSaves);
                loadScene1();
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesSaves.remove(i);
                saveManager.saveConfig(getApplicationContext(), notesSaves);
                loadScene1();
            }
        });
    }
    public void loadScene2WithEmptyNote(){
        setContentView(R.layout.scene2);
        delButton = findViewById(R.id.delButton);
        saveButton = findViewById(R.id.saveButton);
        nameNote = findViewById(R.id.nameNoteField);
        contentNote = findViewById(R.id.contentNoteField);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note note = new note(nameNote.getText().toString(), contentNote.getText().toString());
                notesSaves.add(note);
                saveManager.saveConfig(getApplicationContext(), notesSaves);
                loadScene1();
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveManager.saveConfig(getApplicationContext(), notesSaves);
                loadScene1();
            }
        });


    }
    public void loadListView() {
        listNotes = findViewById(R.id.listNotes);
        ArrayAdapter<note> arrayAdapter = new ArrayAdapter<note>(this, android.R.layout.simple_list_item_1 , notesSaves);

        if(arrayAdapter != null){
            listNotes.setAdapter(arrayAdapter);
        }
    }
}
