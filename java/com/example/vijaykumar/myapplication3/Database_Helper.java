package com.example.vijaykumar.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by vijay.kumar on 9/26/2017.
 */

public class Database_Helper extends SQLiteOpenHelper {
    public static final String DataBase_Name="student.db";
    public static final String Table_Name="student_info";
    public static final String col_1="ID";
    public static final String col_2="NAME";
    public static final String col_3="MARKS";

    public   Database_Helper(Context context) {
        super(context, DataBase_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT ,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
   public boolean insertData(String named,String marks)
   {
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues values=new ContentValues();

       values.put(col_2,named);
       values.put(col_3,marks);
      long resl= db.insert(Table_Name,null,values);
       if(resl==-1)
           return false;
       else
           return true;
   }
  public Cursor  getData()
  {
      SQLiteDatabase db=this.getWritableDatabase();
     Cursor res= db.rawQuery("select * from " + Table_Name,null);
      return res;
  }
  public Integer deletData(String id)
  {
      SQLiteDatabase db=this.getWritableDatabase();
      return db.delete(Table_Name,"ID=?",new String[] {id});

  }
  public boolean updateData(String id,String name,String marks)
  {
      SQLiteDatabase db=this.getWritableDatabase();
      ContentValues values=new ContentValues();
      values.put(col_1,id);
      values.put(col_2,name);
      values.put(col_3,marks);
      db.update(Table_Name,values,"ID=?",new String[] {id});
      return true;
  }
}
