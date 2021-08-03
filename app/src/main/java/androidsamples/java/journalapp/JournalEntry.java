package androidsamples.java.journalapp;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private UUID mUid;

    @ColumnInfo(name = "title")
    @NonNull
    private String mTitle;

    @ColumnInfo(name = "date")
    @NonNull
    private String mDate;

    @ColumnInfo(name = "start")
    @NonNull
    private String mStart;

    @ColumnInfo(name = "end")
    @NonNull
    private String mEnd;

    public JournalEntry(@NonNull String title,@NonNull String date,@NonNull String start,@NonNull String end) {
        mUid = UUID.randomUUID();
        mTitle = title;
        mDate = date;
        mStart = start;
        mEnd = end;
    }

    @NonNull
    public UUID getUid() {
        return mUid;
    }

    public void setUid(@NonNull UUID id) {
        mUid = id;
    }

    @NonNull
    public String title() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @NonNull
    public String getDate() { return mDate;}

    public void setDate(String date) {mDate = date;}

    @NonNull
    public String getStart() { return mStart;}

    public void setStart(String start) {mStart = start;}

    @NonNull
    public String getEnd() { return mEnd;}

    public void setEnd(String end) {mEnd = end;}
}
