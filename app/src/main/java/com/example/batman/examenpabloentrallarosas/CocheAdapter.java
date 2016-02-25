package com.example.batman.examenpabloentrallarosas;


import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by batman on 04/02/16.
 */
public class CocheAdapter extends ArrayAdapter <Coche> {

    Context context;
    ArrayList<Coche> coches;

    // Constructor
    public CocheAdapter (Context context, ArrayList<Coche> coches) {
        //super (context, R.layout.list_view_team, R.id.lblTeam, teams);
        super (context, R.layout.row, coches);
        this.coches = coches;
        this.context = context;
    }

    public View getDropDownView (int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        // To alter colors
        int color = context.getResources().getColor(R.color.white);
        if (position % 2 == 0) color = context.getResources().getColor(R.color.grey);

        // THIS IS THE MAGIC
        if (item == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.row, parent, false);

            TextView lbl = (TextView) item.findViewById(R.id.marcaModelo);
            lbl.setText(coches.get(position).getMarca() + " - " + coches.get(position).getModelo());

            holder = new ViewHolder();
            holder.lbl = lbl;

            item.setTag(holder);
        } else {
            holder = (ViewHolder)item.getTag();
        }

        holder.lbl.setText(coches.get(position).getMarca() + " - " + coches.get(position).getModelo());

        item.setBackgroundColor(color);

        return (item);
    }
}

class ViewHolder {
    TextView lbl;
}