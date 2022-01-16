package com.example.navegationviewlogin.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;

public class User implements Parcelable {

    String user;
    String nombre;
    String clave;
    String imagen;
    JSONArray opciones;
    String rol;

    public User() {
    }

    public User(String user, String nombre, String clave, String imagen, JSONArray opciones, String rol) {
        this.user = user;
        this.nombre = nombre;
        this.clave = clave;
        this.imagen = imagen;
        this.opciones = opciones;
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public JSONArray getOpciones() {
        return opciones;
    }

    public void setOpciones(JSONArray opciones) {
        this.opciones = opciones;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    protected User(Parcel in) {
        user = in.readString();
        nombre = in.readString();
        clave = in.readString();
        imagen= in.readString();
        rol =in.readString();
        try {
            opciones = in.readByte() == 0x00 ? null : new JSONArray(in.readString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user);
        dest.writeString(nombre);
        dest.writeString(clave);
        dest.writeString(imagen);
        dest.writeString(rol);
        if (opciones == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeString(opciones.toString());
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

