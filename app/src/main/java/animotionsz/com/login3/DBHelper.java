package animotionsz.com.login3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.attr.password;

/**
 * Created by Shrikant on 19-01-2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";
    public static final String TABLE_NAME = "login";
    public static final String ID = "id";
    public static final String USER_NAME = "username";
    public static final String PASSWORD = "pass";
    public static final String MOBILE = "mob";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id integer primary key autoincrement ,username text,pass text,mob integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name, String pass, String mob) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, name);
        cv.put(PASSWORD, pass);
        cv.put(MOBILE, mob);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean getSingleEntry(String username, String password) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username=? AND pass=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
    public List<LoginDetails> getData(){
        // DataModel dataModel = new DataModel();
        List<LoginDetails> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        LoginDetails dataModel = null;
        while (cursor.moveToNext()) {
            dataModel=new LoginDetails();
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("pass"));
            String mobile = cursor.getString(cursor.getColumnIndexOrThrow("mob"));
            dataModel.setUsername(username);
            dataModel.setPassword(password);
            dataModel.setMobile(mobile);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (LoginDetails mo:data ) {

            Log.i("Hellomo",""+mo.getMobile());
        }

        //

        return data;
    }

    public boolean updateData(String username,String password,String mobile){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(USER_NAME,username);
        cv.put(PASSWORD,password);
        cv.put(MOBILE,mobile);
        db.update(TABLE_NAME,cv,"username=?",new String[]{username});
        return true;
    }
    public boolean deleteData(String username){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,"username=?",new String[]{username});
        return true;
    }


}