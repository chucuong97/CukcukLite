package com.example.cukcuklite.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cukcuklite.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop thuc hien tao, mo dong database
 */
public class MyDatabaseOpenHelper extends SQLiteOpenHelper {

    private String DB_PATH = "/data/data/com.example.cukcuklite/";
    private static String DB_NAME = "database_cukcuklite.db";
    public SQLiteDatabase database;
    public Context context;

    /**
     * created by: cvcuong
     * date: 5/20/2019
     * target : Hamm khoi tao database controller
     * @param context
     */
    public MyDatabaseOpenHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context = context;
    }

    /**
     * created by: cvcuong
     * date: 5/20/2019
     * target : Ham khoi tao databse
     * @throws IOException
     */
    public void createDatabase() throws IOException {
        try {
            // Nếu chưa tồn tại DB thì copy từ Assets vào Data
            if (!checkExistDataBase()) {
                this.getReadableDatabase();
                try {
                    copyDataBase();
                } catch (Exception e) {
                    throw new Error(Utils.ERROR_COPY_DATABASE);
                }
            }
        } catch (Error error) {
            error.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/20/2019
     * target : Ham kiem tra database da duoc tao hay chua
     * @return true: database da ton tai
     *         false: chua co database
     */
    private boolean checkExistDataBase() {
        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * created by: cvcuong
     * date: 5/20/2019
     * target : Ham copy databse
     * @throws IOException
     */
    private void copyDataBase() throws IOException {
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
            OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/20/2019
     * target : Ham mo database
     * @throws SQLException
     */
    public void openDataBase() throws SQLException {
        try {
            createDatabase();
            database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/20/2019
     * target : Ham dong database
     */
    @Override
    public synchronized void close() {
        try {
            if (database != null)
                database.close();
            super.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
