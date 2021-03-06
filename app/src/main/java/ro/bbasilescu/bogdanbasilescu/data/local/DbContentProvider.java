package ro.bbasilescu.bogdanbasilescu.data.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DbContentProvider extends ContentProvider {

    /**
     * The constants used to determine the type of queried URI.
     */
    private static final int USER = 100;
    private static final int USER_ID = 101;
    private static final int PROJECT = 200;
    private static final int PROJECT_ID = 201;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DatabaseHelper mDatabaseHelper;

    /**
     * Builds a UriMatcher.
     *
     * @return The URIMatcher that that is used to determine witch database request is being made
     */
    public static UriMatcher buildUriMatcher() {
        String content = DatabaseContract.CONTENT_AUTHORITY;

        // All paths to the UriMatcher have a corresponding code to return
        // when a match is found (the ints above).
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, DatabaseContract.PATH_USER, USER);
        matcher.addURI(content, DatabaseContract.PATH_USER + "/#", USER_ID);
        matcher.addURI(content, DatabaseContract.PATH_PROJECT, PROJECT);
        matcher.addURI(content, DatabaseContract.PATH_PROJECT + "/#", PROJECT_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    /**
     * Method used to find the MIME type of the results,
     * either a directory of multiple results or an individual item.
     *
     * @param uri The queried URI
     * @return The type of the requested URI
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case USER:
                return DatabaseContract.UserEntry.CONTENT_TYPE;
            case USER_ID:
                return DatabaseContract.UserEntry.CONTENT_ITEM_TYPE;
            case PROJECT:
                return DatabaseContract.ProjectEntry.CONTENT_TYPE;
            case PROJECT_ID:
                return DatabaseContract.ProjectEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }

    /**
     * Method is called during the construction of queries for the queried data.
     *
     * @param uri           The queried URI
     * @param projection    A string array of columns that will be returned in the result set
     * @param selection     A string defining the criteria for results to be returned
     * @param selectionArgs Arguments to the above criteria that rows will be checked against
     * @param sortOrder     A string of the column(s) and order to sort the result set by
     * @return The Cursor that returns from the database the queried data
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case USER:
                retCursor = db.query(
                        DatabaseContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case USER_ID:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        DatabaseContract.UserEntry.TABLE_NAME,
                        projection,
                        DatabaseContract.UserEntry._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            case PROJECT:
                retCursor = db.query(
                        DatabaseContract.ProjectEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PROJECT_ID:
                _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        DatabaseContract.ProjectEntry.TABLE_NAME,
                        projection,
                        DatabaseContract.ProjectEntry._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Set the notification URI for the cursor to the one passed into the function. This
        // causes the cursor to register a content observer to watch for changes that happen to
        // this URI and any of it's descendants. By descendants, we mean any URI that begins
        // with this path.
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;

    }

    /**
     * Method is called during the insertion of a new row in the database
     *
     * @param uri           The queried URI
     * @param contentValues The values inserted in the new row
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case USER:
                _id = db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = DatabaseContract.UserEntry.buildTodoUri(_id);
                } else {
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                }
                break;
            case PROJECT:
                _id = db.insert(DatabaseContract.ProjectEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = DatabaseContract.ProjectEntry.buildPriorityUri(_id);
                } else {
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Use this on the URI passed into the function to notify any observers that the uri has
        // changed.
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;

    }

    /**
     * Method is called to delete the specified parameter/s
     *
     * @param uri           The queried URI
     * @param selection     A string defining the criteria for results to be returned
     * @param selectionArgs Arguments to the above criteria that rows will be checked against
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int rowsDeleted; // Number of rows effected

        switch (sUriMatcher.match(uri)) {
            case USER:
                rowsDeleted = db.delete(DatabaseContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PROJECT:
                rowsDeleted = db.delete(DatabaseContract.ProjectEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Because null could delete all rows:
        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    /**
     * Method is called to update the specified parameter/s
     *
     * @param uri           The queried URI
     * @param selection     A string defining the criteria for results to be returned
     * @param selectionArgs Arguments to the above criteria that rows will be checked against
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int rows;

        switch (sUriMatcher.match(uri)) {
            case USER:
                rows = db.update(DatabaseContract.UserEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case PROJECT:
                rows = db.update(DatabaseContract.ProjectEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }
}
