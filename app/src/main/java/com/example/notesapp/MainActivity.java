package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button create;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create = findViewById(R.id.createNoteButton);
        show = findViewById(R.id.showNotesButton);

        // Starts the NewNote activity, enabling input of a new note for the database.
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainActivity.this, NewNote.class);
                startActivity(createIntent);
            }
        });

        // Starts the Notes activity, displaying all notes from the database.
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showIntent = new Intent(MainActivity.this, Notes.class);
                startActivity(showIntent);
            }
        });
    }
}