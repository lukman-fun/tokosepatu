package com.jonoutomostore.history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailHistory {
    @SerializedName("orders")
    Orders orders;
    @SerializedName("items")
    Items items;
    @SerializedName("penerima")
    Penerima penerima;
    @SerializedName("banks")
    Banks banks;
    @SerializedName("tindakan")
    Tindakan tindakan;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Penerima getPenerima() {
        return penerima;
    }

    public void setPenerima(Penerima penerima) {
        this.penerima = penerima;
    }

    public Banks getBanks() {
        return banks;
    }

    public void setBanks(Banks banks) {
        this.banks = banks;
    }

    public Tindakan getTindakan() {
        return tindakan;
    }

    public void setTindakan(Tindakan tindakan) {
        this.tindakan = tindakan;
    }

    public class Orders{
        @SerializedName("status")
        String status;
        @SerializedName("data")
        Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data{
            @SerializedName("id")
            String id;
            @SerializedName("order_number")
            String order_number;
            @SerializedName("order_status")
            String order_status;
            @SerializedName("order_date")
            String order_date;
            @SerializedName("total_price")
            String total_price;
            @SerializedName("total_items")
            String total_items;
            @SerializedName("payment_method")
            String payment_method;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getOrder_date() {
                return order_date;
            }

            public void setOrder_date(String order_date) {
                this.order_date = order_date;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getTotal_items() {
                return total_items;
            }

            public void setTotal_items(String total_items) {
                this.total_items = total_items;
            }

            public String getPayment_method() {
                return payment_method;
            }

            public void setPayment_method(String payment_method) {
                this.payment_method = payment_method;
            }
        }
    }

    public class Items{
        @SerializedName("status")
        String status;
        @SerializedName("data")
        List<Data> data;

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
            @SerializedName("product_id")
            String product_id;
            @SerializedName("order_qty")
            String order_qty;
            @SerializedName("order_price")
            String order_price;
            @SerializedName("name")
            String name;
            @SerializedName("picture_name")
            String picture_name;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getOrder_qty() {
                return order_qty;
            }

            public void setOrder_qty(String order_qty) {
                this.order_qty = order_qty;
            }

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
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
        }
    }

    public class Penerima{
        @SerializedName("status")
        String status;
        @SerializedName("data")
        Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data{
            @SerializedName("name")
            String name;
            @SerializedName("phone_number")
            String phone_number;
            @SerializedName("address")
            String address;
            @SerializedName("province")
            String province;
            @SerializedName("kabupaten")
            String kabupaten;
            @SerializedName("kode_pos")
            String kode_pos;
            @SerializedName("nama_kurir")
            String nama_kurir;
            @SerializedName("ongkir")
            String ongkir;
            @SerializedName("note")
            String note;

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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getKabupaten() {
                return kabupaten;
            }

            public void setKabupaten(String kabupaten) {
                this.kabupaten = kabupaten;
            }

            public String getKode_pos() {
                return kode_pos;
            }

            public void setKode_pos(String kode_pos) {
                this.kode_pos = kode_pos;
            }

            public String getNama_kurir() {
                return nama_kurir;
            }

            public void setNama_kurir(String nama_kurir) {
                this.nama_kurir = nama_kurir;
            }

            public String getOngkir() {
                return ongkir;
            }

            public void setOngkir(String ongkir) {
                this.ongkir = ongkir;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }

    public class Banks{
        @SerializedName("status")
        String status;
        @SerializedName("data")
        Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data{
            @SerializedName("payment_price")
            String payment_price;
            @SerializedName("payment_date")
            String payment_date;
            @SerializedName("payment_status")
            String payment_status;
            @SerializedName("to")
            String to;
            @SerializedName("from")
            String from;

            public String getPayment_price() {
                return payment_price;
            }

            public void setPayment_price(String payment_price) {
                this.payment_price = payment_price;
            }

            public String getPayment_date() {
                return payment_date;
            }

            public void setPayment_date(String payment_date) {
                this.payment_date = payment_date;
            }

            public String getPayment_status() {
                return payment_status;
            }

            public void setPayment_status(String payment_status) {
                this.payment_status = payment_status;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }
        }
    }

    public class Tindakan{
        @SerializedName("status")
        String status;
        @SerializedName("data")
        Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public class Data{
            @SerializedName("ket")
            String ket;
            @SerializedName("act")
            String act;

            public String getKet() {
                return ket;
            }

            public void setKet(String ket) {
                this.ket = ket;
            }

            public String getAct() {
                return act;
            }

            public void setAct(String act) {
                this.act = act;
            }
        }
    }

}
