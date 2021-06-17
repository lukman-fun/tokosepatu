package com.jonoutomostore.cart;

import com.google.gson.annotations.SerializedName;

public class Provinsi {
    @SerializedName("province_id")
    String province_id;
    @SerializedName("province")
    String province;

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
