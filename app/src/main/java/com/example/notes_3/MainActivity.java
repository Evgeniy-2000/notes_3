package com.example.notes_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button addNote;
    private ListView list;
    private String titleStr, infoStr, dateStr;
    private ArrayList<String> notes = null;
    ArrayAdapter<String> adapter = null;
    private int currentElement = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListeners();

        titleStr = getIntent().getStringExtra("title");
        infoStr = getIntent().getStringExtra("info");
        dateStr = getIntent().getStringExtra("date");

        if(notes == null) {
            notes = new ArrayList<>();
            notes.add(0, "first");
            notes.add(0, "second");
        }

        if(titleStr != null){
            if(currentElement == -1){
                notes.add(0, titleStr);
            }
            else{
                notes.set(currentElement, titleStr);
            }
        }

        if(adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        }
        else{
            adapter.notifyDataSetChanged();
        }
        list.setAdapter(adapter);
    }

    public void addListeners(){
        addNote = (Button)findViewById(R.id.add);
        addNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                currentElement = -1;
                startActivity(intent);
            }
        });
        list = findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                /*Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                currentElement = position;
                startActivity(intent);
            }
        });
    }


}
