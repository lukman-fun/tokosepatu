package com.jonoutomostore.home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Produk {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Data> data=new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("id")
        String id;
        @SerializedName("name")
        String name;
        @SerializedName("picture_name")
        String picture_name;
        @SerializedName("price")
        String price;
        @SerializedName("stock")
        String stok;
        @SerializedName("product_unit")
        String unit;
        @SerializedName("description")
        String deskripsi;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture_name() {
            return picture_name;
        }

        public void setPicture_name(String picture_name) {
            this.picture_name = picture_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStok() {
            return stok;
        }

        public void setStok(String stok) {
            this.stok = stok;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }
    }

}
