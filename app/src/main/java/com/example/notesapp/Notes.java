package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notesapp.data.DBHelper;
import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {
    ListView notesListView;
    ArrayList<String> noteArrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        notesListView = findViewById(R.id.notesListView);
        noteArrayList = new ArrayList<>();
        DBHelper db = new DBHelper(Notes.this);

        // Fetches all notes in the database and adds each note's content to the list.
        List<Note> noteList = db.fetchAllNotes();
        for (Note note : noteList) {
            noteArrayList.add(note.getContent());
        }

        // Display our list.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteArrayList);
        notesListView.setAdapter(adapter);

        // Starts EditNote activity and passes with it the notes id and content.
        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Notes.this, EditNote.class);
                intent.putExtra("content", noteArrayList.get(position));
                intent.putExtra("id", noteList.get(position).getNote_id());
                startActivityForResult(intent, 1);
            }
        });
    }

    // Overrides back button behaviour to return to MainActivity page and removes everything else
    // from the stack.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Notes.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}