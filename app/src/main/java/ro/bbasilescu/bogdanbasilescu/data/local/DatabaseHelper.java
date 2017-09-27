package ro.bbasilescu.bogdanbasilescu.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import timber.log.Timber;

public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * Database name and version.
     */
    private static final String DATABASE_NAME = "bogdanbasilescu.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor.
     *
     * @param context The context for the database
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Method is called during the creating of the SQLite database.
     *
     * @param sqLiteDatabase The SQLiteDatabase the table is being created into
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Adding tables
        addUserTable(sqLiteDatabase);
        addProjectTable(sqLiteDatabase);
        // Populating tables
        insertUser(sqLiteDatabase);
        insertProjects(sqLiteDatabase);
    }

    /**
     * Method is called during an upgrade of the database.
     *
     * @param sqLiteDatabase The SQLiteDatabase the table is being created into
     * @param oldVersion     The old version of the database
     * @param newVersion     The new version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        dropTables(sqLiteDatabase, oldVersion, newVersion);
    }

    /**
     * Creates the User table into the database.
     *
     * @param db The SQLiteDatabase the table is being created into
     */
    private void addUserTable(SQLiteDatabase db) {
        db.execSQL("create table "
                + DatabaseContract.UserEntry.TABLE_NAME
                + " ("
                + DatabaseContract.UserEntry._ID + " integer primary key autoincrement, "
                + DatabaseContract.UserEntry.COLUMN_NAME + " text not null, "
                + DatabaseContract.UserEntry.COLUMN_DESCRIPTION + " text not null, "
                + DatabaseContract.UserEntry.COLUMN_OBJECTIVE + " text not null "
                + ");");
    }

    /**
     * Inserts User into the User table of the database.
     * @param db The SQLiteDatabase
     */
    private void insertUser(SQLiteDatabase db) {
        db.execSQL("Insert into " + DatabaseContract.UserEntry.TABLE_NAME + " ("
                + DatabaseContract.UserEntry.COLUMN_NAME + ", "
                + DatabaseContract.UserEntry.COLUMN_DESCRIPTION + ", "
                + DatabaseContract.UserEntry.COLUMN_OBJECTIVE + ") values "
                + "('Bogdan Basilescu', " +
                "'Knowledge harvester, public speaker addicted to success " +
                "& driven to overcome anything.', " +
                "'I am eager to inspire, coordinate and " +
                "motivate people in different projects in " +
                "order to achieve success through hard " +
                "work and dedication, therefore my " +
                "professional goal for the next 3 years is " +
                "to become a Product Manager.'" +
                ");");
    }

    /**
     * Creates the Project table into the database.
     *
     * @param db The SQLiteDatabase the table is being created into
     */
    private void addProjectTable(SQLiteDatabase db) {
        db.execSQL("create table "
                + DatabaseContract.ProjectEntry.TABLE_NAME
                + " ("
                + DatabaseContract.ProjectEntry._ID + " integer primary key autoincrement, "
                + DatabaseContract.ProjectEntry.COLUMN_NAME + " text unique not null, "
                + DatabaseContract.ProjectEntry.COLUMN_DESCRIPTION + " text not null, "
                + DatabaseContract.ProjectEntry.COLUMN_LOCATION + " text not null "
                + ");");
    }

    /**
     * Inserts User into the User table of the database.
     * @param db The SQLiteDatabase
     */
    private void insertProjects(SQLiteDatabase db) {
        db.execSQL("Insert into " + DatabaseContract.ProjectEntry.TABLE_NAME + " ("
                + DatabaseContract.ProjectEntry.COLUMN_NAME + ", "
                + DatabaseContract.ProjectEntry.COLUMN_DESCRIPTION + ", "
                + DatabaseContract.ProjectEntry.COLUMN_LOCATION + ") values "
                + "('Beyond the impossible', 'The project involved coordinating the Romanian team during the " +
                "training course and attending strategic and feedback meetings while " +
                "developng soft skills such as leadership, communication, conflict-" +
                "management and problem-solving skills by active involvement.', 'Estonia'" +
                ");");
    }

    /**
     * Drops tables.
     *
     * @param db         The SQLiteDatabase the table is being created into
     * @param oldVersion The old version of the SQLiteDatabase
     * @param newVersion The new version of the SQLiteDatabase
     */
    private void dropTables(SQLiteDatabase db, int oldVersion, int newVersion) {
        Timber.w("Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        // Dropping tables and creating new ones
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.UserEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.ProjectEntry.TABLE_NAME);
        onCreate(db);
    }

}
