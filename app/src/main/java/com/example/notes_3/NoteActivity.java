package com.example.notes_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoteActivity extends Activity {
    private Button save, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        addListeners();
    }
    public void addListeners(){
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);

                intent.putExtra("title", ((TextView)findViewById(R.id.title)).getText().toString());
                intent.putExtra("info", ((TextView)findViewById(R.id.info)).getText().toString());
                intent.putExtra("date", ((TextView)findViewById(R.id.date)).getText().toString());
                intent.putExtra("position", getIntent().getStringExtra("pos"));
                startActivity(intent);
            }
        });
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
