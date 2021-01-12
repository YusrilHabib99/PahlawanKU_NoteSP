package com.habib.pahlawanku_notesp;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.habib.pahlawanku_notesp.Model.Note;
public class FormNoteAdapter extends ArrayAdapter<Note> {
    Context context;

    public FormNoteAdapter(@NonNull Context context, @NonNull List<Note> objects) {
        super(context, R.layout.row_note, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txNama;
        TextView txAsal;
        TextView txProfil;
        TextView txLahir;
        TextView txWafat;
    }

    @NonNull
    @Nullable
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note nt = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_note, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txNama = convertView.findViewById(R.id.rowTx_Nama);
            viewHolder.txAsal = convertView.findViewById(R.id.rowTx_Asal);
            viewHolder.txProfil = convertView.findViewById(R.id.rowTx_profil);
            viewHolder.txLahir = convertView.findViewById(R.id.rowTx_lahir);
            viewHolder.txWafat = convertView.findViewById(R.id.rowTx_Wafat);
            convertView.setTag(viewHolder);
        } else  {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txNama.setText(nt.getNama());
        viewHolder.txProfil.setText(nt.getProfil());
        viewHolder.txAsal.setText(nt.getAsal());
        viewHolder.txLahir.setText(nt.getLahir());
        viewHolder.txWafat.setText(nt.getWafat());
        return convertView;
    }
}
