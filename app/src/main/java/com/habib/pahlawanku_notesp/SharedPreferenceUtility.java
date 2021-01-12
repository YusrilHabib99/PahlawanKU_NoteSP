package com.habib.pahlawanku_notesp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.habib.pahlawanku_notesp.Model.Note;

public class SharedPreferenceUtility {

    private static final String PREF_FILE ="com.habib.PahlawanKU_NoteSP.DATA";
    /*
        PREF_FILE -> Nama file untuk penyimpanan,
        biasanya menyertakan app.id agar tidak bentrok dengan app lain
     */

    private static final String TRANS_KEY = "TRANS"; // KEY untuk daftar note
    private static final String USER_NAME_KEY = "USERNAME"; // KEY untuk nama user

    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE
        );
        return sharedPref;
    }
    /*
        Ambil data username dari Shared Preference
     */
    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY, "NO NAME");
    }
    /*
        Simpan data ke Shared Preference
     */
    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF", "Change user name to"+userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY,userName).apply();
    }
    /*
         Ambil data transaksi dari Shared Preference
         Perhatikan bahwa data disimpan dalam format JSON String
     */
    public static List<Note> getAllNote(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY,null);
        List<Note> nt = new ArrayList<Note>();
        if (jsonString != null) {
            Log.d("SH PREF", "json string is:"+jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i=0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    nt.add(Note.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(nt, (note, n1) -> {
            return note.getNama().compareTo(n1.getNama());
        }); // Urutkan data note berdasarkan nama
        return nt;
    }
    /*
        Simpan data note ke Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */

    private static void saveAllNote(Context ctx, List<Note> nts) {
        List<JSONObject> jsonNt = new ArrayList<JSONObject>();
        JSONArray jsonArray = new JSONArray();
        for (Note nt : nts) {
            jsonArray.put(nt.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY, jsonArray.toString()).apply();
    }
    /*
        Tambah data note baru ke Shared Preference
     */
    public static void addNote(Context ctx, Note nt) {
        List<Note> nts = getAllNote(ctx);
        nts.add(nt);
        saveAllNote(ctx, nts);
    }

    public static void editNote(Context ctx, Note nt) {
        List<Note> nts = getAllNote(ctx);
        for (Note note:nts) {
            if (nt.getNama().equals(note.getNama())) {
                nts.remove(note);
                nts.add(nt);
            }
        }
        saveAllNote(ctx,nts);
    }

    public static void deleteNote(Context ctx, String nama) {
        List<Note> notes = getAllNote(ctx);
        for(Note nt : notes) {
            if(nt.getNama().equals(nama)) {
                notes.remove(nt);
            }
        }
        saveAllNote(ctx, notes);
    }
}
