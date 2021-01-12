package com.habib.pahlawanku_notesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import com.habib.pahlawanku_notesp.Model.Note;

public class FormNoteActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout
            tilNama,
            tilAsal,
            tilProfil,
            tilLahir,
            tilWafat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_note);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(view -> simpan());
        tilNama = findViewById(R.id.tilNama);
        tilAsal = findViewById(R.id.tilAsal);
        tilProfil = findViewById(R.id.tilProfil);
        tilLahir = findViewById(R.id.tilLahir);
        tilWafat = findViewById(R.id.tilWafat);
    }

    private void simpan() {
        if (isDataValid()) {
            Note nt = new Note();
            nt.setNama(tilNama.getEditText().getText().toString());
            nt.setAsal(tilAsal.getEditText().getText().toString());
            nt.setProfil(tilProfil.getEditText().getText().toString());
            nt.setLahir(tilLahir.getEditText().getText().toString());
            nt.setWafat(tilWafat.getEditText().getText().toString());
            SharedPreferenceUtility.addNote(this,nt);
            Toast.makeText(this,"Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        }
    }

    private boolean isDataValid() {
        if (tilNama.getEditText().getText().toString().isEmpty()
                || tilAsal.getEditText().getText().toString().isEmpty()
                || tilProfil.getEditText().getText().toString().isEmpty()
                || tilLahir.getEditText().getText().toString().isEmpty()
                || tilWafat.getEditText().getText().toString().isEmpty()
        ) {
            Toast.makeText(this,"Lengkapi semua isian!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}