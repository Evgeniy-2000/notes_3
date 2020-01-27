package com.example.notes_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public class note{
        public String title, info, date, tags;
        note(String title, String info, String date){
            this.title = title;
            this.info = info;
            this.date = date;
            this.tags = "";
        }
    }
    private Button addNote;
    private ListView list;
    private String titleStr, infoStr, dateStr, tagsStr;
    private ArrayList<note> noteObjects = null;
    private String currentElement = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListeners();
        getExtraDataFromNoteActivity();
        initializeNoteObjectsArray();
        createList();
    }

    public void readDataFromFile(){

    }

    public void writeDataInFile(){

    }

    public void initializeNoteObjectsArray(){
        noteObjects = new ArrayList<>();
        noteObjects.add(new note("first", "buy milk", "05.06.2019"));
        noteObjects.add(new note("second", "call decanat", "07.08.2019"));
        noteObjects.add(new note("third", "write responce", "14.11.2019"));
        if(currentElement != null){
            if(currentElement.equals("-1")){
                noteObjects.add(0 , new note(titleStr, infoStr, dateStr));
            }
            else{
                noteObjects.set(Integer.parseInt(currentElement), new note(titleStr, infoStr, dateStr));
            }
        }
    }

    public void getExtraDataFromNoteActivity(){
        titleStr = getIntent().getStringExtra("title");
        infoStr = getIntent().getStringExtra("info");
        dateStr = getIntent().getStringExtra("date");
        currentElement = getIntent().getStringExtra("position");
    }

    public void createList(){
        ArrayList<String> notesArrayForAdapter = new ArrayList<String>();
        for(note n : noteObjects){
            notesArrayForAdapter.add(n.title);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesArrayForAdapter);;
        //adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
    }

    public void addListeners(){
        addNote = (Button)findViewById(R.id.add);
        addNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("pos", "-1");
                startActivity(intent);
            }
        });
        list = findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("pos", "" + position);
                startActivity(intent);
            }
        });
    }
}
