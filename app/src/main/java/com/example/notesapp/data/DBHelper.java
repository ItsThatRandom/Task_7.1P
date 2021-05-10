package com.example.notesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.speech.tts.UtteranceProgressListener;
import android.view.autofill.AutofillId;

import androidx.annotation.Nullable;

import com.example.notesapp.model.Note;
import com.example.notesapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_NOTE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.NOTE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(DROP_NOTE_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);
    }

    // function to insert new notes into the table, returning the new row ID of inserted.
    public long insertNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE, note.getContent());
        long newRowID = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowID;
    }

    // Creates and returns a list of all notes from the database.
    public List<Note> fetchAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setNote_id(cursor.getInt(0));
                note.setContent(cursor.getString(1));

                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

    // Updates the given notes String/'Content' value in the database with provided notes content
    // and returns the rows affected.
    public long updateNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NOTE, note.getContent());

        return db.update(Util.TABLE_NAME, contentValues, Util.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
    }

    // Removes the provided note from the database, returning the rows affected.
    public long deleteNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Util.TABLE_NAME, Util.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
    }
}
