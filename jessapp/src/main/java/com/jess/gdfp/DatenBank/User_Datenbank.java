package com.jess.gdfp.DatenBank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class User_Datenbank extends SQLiteOpenHelper {
    private static final String TABLE_USERS="users";
    private static final String ID= "id";
    private static final String NAME="name";
    private static final String PREVELEING ="preveling";
    private static final String COUNT ="count";
    private static final String [] COLUMNS ={ID,NAME,PREVELEING,COUNT};
    private static final int DATA_VERSION =5;
    private static final  String DATA_NAME ="UsersDB";


    public User_Datenbank(Context context){
        super(context,DATA_NAME,null,DATA_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE= "CREATE TABLE " + TABLE_USERS + "("  + ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME +" TEXT, " + PREVELEING +" TEXT ,"+ COUNT +" INTEGER " +");";

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String dropIfEx = "DROP TABLE IF EXISTS " + TABLE_USERS;
        sqLiteDatabase.execSQL(dropIfEx);
        this.onCreate(sqLiteDatabase);

    }

    public void addUser(Users user) {

        List<Users> num =getAllusers();
        int i = num.size();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,user.getName());
        values.put(PREVELEING,user.getPreveling());
        values.put(COUNT,i);
        db.insert(TABLE_USERS,null,values);
        db.close();
        System.out.println(i);
    }

    public Users getUser(int id ){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_USERS,COLUMNS," id = ?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();

        Users users = new Users();
        users.setId(Integer.parseInt(cursor.getString(0)));
        users.setPreveling(cursor.getString(2));
        users.setName(cursor.getString(1));


        return  users;
    }

    public List<Users> getAllusers(){
        List<Users> users = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        Users user;
        if(cursor.moveToNext())
            do {
                user=new Users();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setPreveling(cursor.getString(2));
                users.add(user);

            }while (cursor.moveToNext());
        return users;
    }

    public void deletUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USERS,COUNT + " = ?",new String[]{String.valueOf(users.getId())});
        List<Users> allusers = getAllusers();
        for (int i=0;i<allusers.size();i++){
            update(allusers.get(i),i);
        }
        db.close();

    }
    public int update (Users user,  int count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COUNT, count);
        int i=  db.update(TABLE_USERS, cv, ID +"= ?", new String[]{String.valueOf((user.getId()))});
        return i;

    }
}