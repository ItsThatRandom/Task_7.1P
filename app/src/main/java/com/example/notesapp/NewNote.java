package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.data.DBHelper;
import com.example.notesapp.model.Note;

public class NewNote extends AppCompatActivity {
    Button save;
    EditText note;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        note = findViewById(R.id.editTextNote);
        save = findViewById(R.id.saveNote);
        db = new DBHelper(this);

        // Inserts the String as a new note into the database and if successful, end activity and
        // go to MainActivity.
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteContent = note.getText().toString();
                Intent mainIntent = new Intent(NewNote.this, MainActivity.class);

                long result = db.insertNote(new Note(noteContent));
                if (result > 0){
                    Toast.makeText(NewNote.this, "Note saved successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(mainIntent);
                }
                else {
                    Toast.makeText(NewNote.this, "Note failed to save!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}