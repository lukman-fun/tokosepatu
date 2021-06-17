package com.jonoutomostore.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MyCart.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyCartDAO myCartDAO();
}
