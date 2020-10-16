package database_layer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TagsTBDao {

    @Query("SELECT * FROM "+ DBConfig.TAGS_TB + " order by name DESC")
    List<TagsTB> getTags();

    @Query("SELECT * FROM "+ DBConfig.TAGS_TB + " where idn = :idn")
    TagsTB getTagByIdn(String idn);

    @Query("SELECT idn FROM "+ DBConfig.TAGS_TB)
    List<String> getAllIdns();

    @Insert
    void insertAll(ArrayList<TagsTB> tags);

    @Delete
    void delete(TagsTB tag);

    @Query("DELETE FROM " + DBConfig.TAGS_TB)
    public void deleteAll();
}

