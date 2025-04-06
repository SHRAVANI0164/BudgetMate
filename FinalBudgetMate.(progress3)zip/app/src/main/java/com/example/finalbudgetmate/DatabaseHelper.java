package com.example.finalbudgetmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "budetmateee";
    private static final String TABLE_NAME = "user_table";
    private static final String TABLE_EXPENSE = "tbl_expense";
    private static final String TABLE_INCOME = "tbl_income";
    private static final String TABLE_BUDGET = "tbl_budget";

    private static final String COL1 = "ID";
    private static final String COL2 = "username";
    private static final String COL3 = "password";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT)";
        db.execSQL(createTable);

        String createTable2 = "create table " + TABLE_EXPENSE + " ( id integer primary key autoincrement," + " note text, amount text, category text)";
        db.execSQL(createTable2);

        String createTable3 = "create table " + TABLE_INCOME + " ( id integer primary key autoincrement," + " amount text, note text)";
        db.execSQL(createTable3);

        String createTable4 = "create table " + TABLE_BUDGET + " ( id integer primary key autoincrement," + " amount text)";
        db.execSQL(createTable4);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);

    }









        public String addrecord(String note, String amount, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("note", note);
        cv.put("amount", amount);
        cv.put("category", category);
        float res = db.insert(TABLE_EXPENSE, null, cv);

        if (res == -1)
            return "Failed";
        else
            return "Successfully inserted";

    }
    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from " + TABLE_EXPENSE + " order by id desc";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }


    public ArrayList<String> getList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        String sQuery = "select amount from " + TABLE_EXPENSE;
        Cursor cursor = db.rawQuery(sQuery,null);
        if (cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    public String getsum(){
        SQLiteDatabase db = getReadableDatabase();
        String amount;
        String sQuery = "select sum(amount) from " + TABLE_EXPENSE;
        Cursor cursor = db.rawQuery(sQuery,null);
        if (cursor.moveToFirst()){
            amount = String.valueOf(cursor.getInt(0));
        }else {
            amount = "0";
        }
        cursor.close();
        db.close();
        return amount;
    }

    public void deleteDataE() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_EXPENSE);
        db.close();
    }



    // FOR INCOME


    public String addincome(String p1, String p2)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("amount",p1);
        cv.put("note",p2);


        long res = db.insert(TABLE_INCOME,null,cv);

        if (res==-1)
            return "Failed";
        else
            return "Successfully inserted";


    }

    public void deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_INCOME);
        db.close();
    }










     //BUDGET

    public String addbudget(String a1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("amount",a1);




        long res = db.insert(TABLE_BUDGET,null,cv);

        if (res==-1)
            return "Failed";
        else
            return "Successfully inserted";


    }

    public void deleteDataB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_BUDGET);
        db.close();
    }








    // LOGIN AND REGISTER
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL1};
        String selection = COL2 + "=? AND " + COL3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

}



