package com.jonoutomostore.cart;

import com.google.gson.annotations.SerializedName;
import com.jonoutomostore.db.MyCart;

import java.util.List;

public class Cart {
    @SerializedName("id_users")
    String id_users;
    @SerializedName("name")
    String name;
    @SerializedName("phone_number")
    String phone_number;
    @SerializedName("address")
    String address;
    @SerializedName("payment")
    String payment;
    @SerializedName("note")
    String note;
    @SerializedName("tujuan")
    String tujuan;
    @SerializedName("kurir")
    String kurir;
    @SerializedName("total_price")
    String total_price;
    @SerializedName("produk")
    List<MyCart> produk;

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<MyCart> getProduk() {
        return produk;
    }

    public void setProduk(List<MyCart> produk) {
        this.produk = produk;
    }
}
