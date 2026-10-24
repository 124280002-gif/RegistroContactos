package com.example.registrocontactos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {

    private List<Contacto> listaContactos;

    public ContactoAdapter(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = listaContactos.get(position);
        holder.tvNombre.setText(contacto.getNombre());
        String detalle = "Correo: " + contacto.getCorreo() + " | Tel: " + contacto.getTelefono() + " | Tipo: " + contacto.getTipo();
        holder.tvDetalle.setText(detalle);
    }

    @Override
    public int getItemCount() {
        return listaContactos != null ? listaContactos.size() : 0;
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDetalle;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDetalle = itemView.findViewById(R.id.tvDetalle);
        }
    }
}
