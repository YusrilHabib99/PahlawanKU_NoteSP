package com.habib.pahlawanku_notesp.Model;

import android.widget.TableRow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Note {
    private String nama;
    private String asal;
    private String profil;
    private String lahir;
    private String wafat;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public String getWafat() {
        return wafat;
    }

    public void setWafat(String wafat) {
        this.wafat = wafat;
    }

    public static Note fromJSONObject(JSONObject obj) {
        Note nt = new Note();
        try {
            nt.setAsal(obj.getString("asal"));
            nt.setLahir(obj.getString("lahir"));
            nt.setNama(obj.getString("nama"));
            nt.setProfil(obj.getString("profil"));
            nt.setWafat(obj.getString("wafat"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nt;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("nama",this.nama);
            jsonObj.put("asal",this.asal);
            jsonObj.put("lahir",this.lahir);
            jsonObj.put("profil",this.profil);
            jsonObj.put("wafat",this.wafat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
