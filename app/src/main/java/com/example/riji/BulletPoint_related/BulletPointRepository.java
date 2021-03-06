package com.example.riji.BulletPoint_related;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.riji.Database;

import java.util.List;

/*
    this is the repository of the bulletpoints. In fancier programs it is responsible for collecting data from different sources.
    It abstracts the database methods from the UI, and is also  resposible for creating a separate thread using an AsyncTask class
    to do database operations.
 */
public class BulletPointRepository {
    //data
    private BulletPointDAO mBulletPointDao;
    private LiveData<List<BulletPoint>> mAllBulletPoints;

    //instantiating method
    public BulletPointRepository(Application application) {
        Database db = Database.getDatabase(application);
        mBulletPointDao = db.getBulletPointDAO();
        mAllBulletPoints = mBulletPointDao.getAllBulletPoints();
    }

    LiveData<List<BulletPoint>> getAllBulletPoints() {
        return mAllBulletPoints;
    }



    List<BulletPoint> searchBulletPoints(String term){
        return mBulletPointDao.search(term);
    }

    void insertBulletPoint(BulletPoint bullet) {
        new insertAsyncTask(mBulletPointDao).execute(bullet);
    }

    public void deleteBulletPoint(BulletPoint bullet) {
        new deleteAsyncTask(mBulletPointDao).execute(bullet);
    }


    //insert bulletpoint using Asynctask
    private static class insertAsyncTask extends AsyncTask<BulletPoint, Void, Void> {

        private BulletPointDAO mAsyncTaskDao;

        insertAsyncTask(BulletPointDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BulletPoint... params) {
            mAsyncTaskDao.insertBulletPoint(params[0]);
            return null;
        }
    }

    //delete bulletpoint using Asynctask
    private static class deleteAsyncTask extends AsyncTask<BulletPoint, Void, Void> {

        private BulletPointDAO mAsyncTaskDao;

        deleteAsyncTask(BulletPointDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BulletPoint... params) {
            mAsyncTaskDao.deleteBulletPoint(params[0]);
            return null;
        }
    }
}