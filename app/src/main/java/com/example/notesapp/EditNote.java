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

public class EditNote extends AppCompatActivity {
    EditText editTextNote;
    Button updateButton, deleteButton;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editTextNote = findViewById(R.id.editTextNote2);
        updateButton = findViewById(R.id.updateNote);
        deleteButton = findViewById(R.id.deleteNote);
        db = new DBHelper(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String content = intent.getStringExtra("content");
        editTextNote.setText(content);

        // Update the note in the database and if successful, end current activity and go to Notes.
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editTextNote.getText().toString();
                long updateRow = db.updateNote(new Note (id ,content));
                Intent showIntent = new Intent(EditNote.this, Notes.class);
                if (updateRow > 0) {
                    finish();
                    startActivity(showIntent);
                    Toast.makeText(EditNote.this, "Update Successful.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditNote.this, "Update Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Deletes the note from the database and if successful, end current activity and go to Notes.
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showIntent = new Intent(EditNote.this, Notes.class);
                long deletedRows = db.deleteNote(new Note (id, content));
                if (deletedRows > 0) {
                    finish();
                    startActivity(showIntent);
                    Toast.makeText(EditNote.this, "Deletion Successful.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditNote.this, "Deletion Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}