package com.habib.pahlawanku_notesp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import com.habib.pahlawanku_notesp.Model.Note;
;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnTambahNote;
    ImageButton btnChangeUserName;
    ListView lvDaftarNote;
    TextView txNoData, txUserName;
    FormNoteAdapter adapter;
    List<Note> daftarNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataNote();
        setupListView();
    }

    private void inisialisasiView() {
        btnTambahNote = findViewById(R.id.btn_add_note);
        btnTambahNote.setOnClickListener(view -> bukaFormTambahNote());
        btnChangeUserName = findViewById(R.id.btn_change_username);
        btnChangeUserName.setOnClickListener(view -> changeUserName());
        lvDaftarNote = findViewById(R.id.lvJumlahNotePahlawan);
        txNoData = findViewById(R.id.txNodata);
        txUserName = findViewById(R.id.txUserName);
        txUserName.setText(SharedPreferenceUtility.getUserName(this));
    }

    private void setupListView() {
        adapter = new FormNoteAdapter(this, daftarNote);
        lvDaftarNote.setAdapter(adapter);
    }


    private void loadDataNote() {
        daftarNote = SharedPreferenceUtility.getAllNote(this);
        if (daftarNote.size()>0) {
            txNoData.setVisibility(View.GONE);
        } else {
            txNoData.setVisibility(View.VISIBLE);
        }
    }

    private void refreshListView() {
        adapter.clear();
        loadDataNote();
        adapter.addAll(daftarNote);
    }

    private void bukaFormTambahNote() {
        Intent intent = new Intent(this, FormNoteActivity.class);
        startActivity(intent);
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferenceUtility.saveUserName(getApplicationContext(),input.getText().toString());
                Toast.makeText(getApplicationContext(), "Nama user berhasil disimpan", Toast.LENGTH_SHORT).show();
                txUserName.setText(SharedPreferenceUtility.getUserName(getApplicationContext()));
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }


}