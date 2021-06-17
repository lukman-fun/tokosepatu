package com.jonoutomostore.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.jonoutomostore.utils.Constraint;

import java.io.Serializable;

@Entity(tableName = Constraint.TABLE_DATABASE)
public class MyCart implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public String ID;

    @ColumnInfo(name = "nama")
    public String nama;

    @NonNull
    @ColumnInfo(name = "qty")
    public int qty;

    @NonNull
    @ColumnInfo(name = "price")
    public String price;

    @NonNull
    @ColumnInfo(name = "image")
    public String image;

    @NonNull
    public String getID() {
        return ID;
    }

    public void setID(@NonNull String ID) {
        this.ID = ID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @NonNull
    public String getPrice() {
        return price.replace(".00", "");
    }

    public void setPrice(@NonNull String price) {
        this.price = price;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }
}
