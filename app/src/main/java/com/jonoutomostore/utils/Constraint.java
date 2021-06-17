package com.jonoutomostore.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Constraint {
    public static final String BASE_URL="http://192.168.0.12/tokojono/";
    public static final String API=BASE_URL+"index.php/API/";
    public static final String PATH_IMG_BANNER=BASE_URL+"assets/themes/sepatu/images/";
    public static final String PATH_IMG_PRODUK=BASE_URL+"assets/uploads/products/";

    //ROOM DATABASE
    public static final String DATABASE="cart_db";
    public static final String TABLE_DATABASE="tb_cart";

    //RUPIAH
    public static final String RUPIAH(Double rupiah){
        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        return "Rp "+numberFormat.format(rupiah).replace("Rp", "");
    }
}
