package com.example.riji.BulletPoint_related;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.riji.Day_related.Day;

@Entity(tableName = "bulletpoints", foreignKeys = {@ForeignKey(entity = Day.class, parentColumns = "id", childColumns = "day_id")},
        indices = {@Index(value = "day_id")})
//
/*
This is the Room entity class that stores the data related to one bulletpoint.
It contains an autogenerated id, the bulletType stored in int, and a string containing
the note of the bulletpoint.
 */

public class BulletPoint {
    //data
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "bulletType")
    public int bulletType;

    @ColumnInfo(name = "note")
    public String note;

    @ColumnInfo(name = "day_id")
    public long day_id;

    //instantiating method
    public BulletPoint(int bulletType, String note, final long day_id) {

        this.bulletType = bulletType;
        this.note = note;
        this.day_id = day_id;
    }

    //getter methods
    public long getId() {
        return id;
    }

    public int getBulletType() {
        return bulletType;
    }

    //setter methods
    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSymbol() {
        String symbol = "";
        if (bulletType == 0) {
            symbol = "○";
        } else if (bulletType == 1) {
            symbol = "-";
        } else if (bulletType == 2) {
            symbol = "•";
        } else if (bulletType == 3) {
            symbol = ">";
        } else if (bulletType == 4) {
            symbol = "<";
        } else if (bulletType == 5) {
            symbol = "✗";
        }
        return symbol;
    }

}