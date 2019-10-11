package ijh.dgsw.hs.kr.androidshopping.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "user";

    public static final String COL_0 = "serialNumber";
    public static final String COL_1 = "name";
    public static final String COL_2 = "email";
    public static final String COL_3 = "id";
    public static final String COL_4 = "password";
    public static final String COL_5 = "gender";
    public static final String COL_6 = "years";

    public DBHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ("
                + COL_0 + " integer primary key autoincrement, "
                + COL_1 + " text not null,"
                + COL_2 + " text not null check (email like '%@%'),"
                + COL_3 + " text not null unique,"
                + COL_4 + " text not null,"
                + COL_5 + " text not null,"
                + COL_6 + " text not null "
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(UserBean user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(COL_1, user.getName());
        value.put(COL_2, user.getEmail());
        value.put(COL_3, user.getId());
        value.put(COL_4, user.getPassword());
        value.put(COL_5, user.getGender());
        value.put(COL_6, user.getYears());

        return db.insert(TABLE_NAME, null, value);
    }

    public ArrayList<UserBean> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<UserBean> result = new ArrayList<>();

        while(cursor.moveToNext()){
            UserBean user = new UserBean();
            user.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
            user.setName(cursor.getString(cursor.getColumnIndex(COL_1)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COL_2)));
            user.setId(cursor.getString(cursor.getColumnIndex(COL_3)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COL_4)));
            user.setGender(cursor.getString(cursor.getColumnIndex(COL_5)));
            user.setYears(cursor.getString(cursor.getColumnIndex(COL_6)));
            result.add(user);
        }

        return result;
    }

    public ArrayList<UserBean> getUserById(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        ArrayList<UserBean> result = new ArrayList<>();

        while(cursor.moveToNext()){
            UserBean user = new UserBean();
            user.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
            user.setName(cursor.getString(cursor.getColumnIndex(COL_1)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COL_2)));
            user.setId(cursor.getString(cursor.getColumnIndex(COL_3)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COL_4)));
            user.setGender(cursor.getString(cursor.getColumnIndex(COL_5)));
            user.setYears(cursor.getString(cursor.getColumnIndex(COL_6)));
            result.add(user);
        }

        return result;
    }

    public String getUserId(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_3));
        }

        return result;
    }

    public String getUserPw(String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_3 + "=?", new String[] {id}, null, null, null);
        String result = "";

        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex(COL_4));
        }

        return result;
    }

    public long delete(UserBean bean){
        SQLiteDatabase db = getWritableDatabase();
        String serial = String.valueOf(bean.getSerialNumber());

        return db.delete(TABLE_NAME, COL_0 + "=?", new String[] {serial});
    }

    public long delete(){
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_NAME, null, null);
    }
}
