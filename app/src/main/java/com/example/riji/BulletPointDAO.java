package com.example.riji;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BulletPointDAO {

    @Insert
    void insertBulletPoint (BulletPoint bp);

    @Update
    void updateBulletPoint(BulletPoint bp);
    @Delete
    void deleteBulletPoint (BulletPoint bp);

    @Query("DELETE FROM bulletpoints")
    void deleteAll();

    @Query("SELECT * FROM bulletpoints ORDER BY id")
    LiveData<List<BulletPoint>> getAllBulletPoints();

    @Query("SELECT * FROM bulletpoints WHERE day_id=:day_id ORDER BY id")
    LiveData<List<BulletPoint>> findBulletPointsForDay(final int day_id);

   // @Query("SELECT * FROM bulletpoints WHERE day_id=:day_id ORDER BY id")
   // List<BulletPoint> findBulletPointsForDay(final int day_id,String word);

}
