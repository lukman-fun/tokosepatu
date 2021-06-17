package com.jonoutomostore.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailProduk {
    @SerializedName("produk")
    Product produk;
    @SerializedName("reviews")
    Reviews reviews;
    @SerializedName("terjual")
    Terjual terjual;

    public Product getProduk() {
        return produk;
    }

    public void setProduk(Product produk) {
        this.produk = produk;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public Terjual getTerjual() {
        return terjual;
    }

    public void setTerjual(Terjual terjual) {
        this.terjual = terjual;
    }

    //PRODUCT
    public class Product{
        @SerializedName("status")
        String status;
        @SerializedName("data")
        Produk.Data data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Produk.Data getData() {
            return data;
        }

        public void setData(Produk.Data data) {
            this.data = data;
        }
    }

    //REVIEWS
    public class Reviews{
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
            @SerializedName("name")
            String name;
            @SerializedName("angka")
            String rating;
            @SerializedName("review_date")
            String review_date;
            @SerializedName("review_text")
            String review_text;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getReview_date() {
                return review_date;
            }

            public void setReview_date(String review_date) {
                this.review_date = review_date;
            }

            public String getReview_text() {
                return review_text;
            }

            public void setReview_text(String review_text) {
                this.review_text = review_text;
            }
        }
    }

    //TERJUAL
    public class Terjual{
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
            @SerializedName("terjual")
            String terjual;

            public String getTerjual() {
                return terjual;
            }

            public void setTerjual(String terjual) {
                this.terjual = terjual;
            }
        }
    }

    //RELATE
//    public class Relate{
//        @SerializedName("status")
//        String status;
//        @SerializedName("")
//    }

}
