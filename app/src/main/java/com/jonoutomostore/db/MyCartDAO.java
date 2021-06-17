package com.jonoutomostore.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jonoutomostore.utils.Constraint;

import java.util.List;

@Dao
public interface MyCartDAO {

    @Insert
    long addCart(MyCart myCart);

    @Query("SELECT EXISTS(SELECT * FROM "+Constraint.TABLE_DATABASE+" WHERE id=:id)")
    boolean existsCart(String id);

    @Query("SELECT * FROM "+Constraint.TABLE_DATABASE+" ORDER BY id DESC")
    List<MyCart> getAllCart();

    @Update
    int updateCart(MyCart myCart);

    @Delete
    void rmvCArt(MyCart myCart);

    @Query("DELETE FROM "+Constraint.TABLE_DATABASE)
    void rmvAll();

}
