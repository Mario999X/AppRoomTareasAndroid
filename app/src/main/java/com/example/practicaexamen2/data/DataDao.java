package com.example.practicaexamen2.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    long insert(TareaEntity t);

    @Query("DELETE FROM datos_tabla WHERE id = :mId")
    void deleteTarea(Integer mId);

    @Query("SELECT * FROM datos_tabla")
    List<TareaEntity> selectTareas();

}
