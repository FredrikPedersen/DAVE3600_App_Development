package com.fredrikpedersen.eatingwithfriends_gradedassignment.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fredrikpedersen.eatingwithfriends_gradedassignment.database.BookingDatabase;
import com.fredrikpedersen.eatingwithfriends_gradedassignment.database.daos.FriendDao;
import com.fredrikpedersen.eatingwithfriends_gradedassignment.database.models.Friend;

import java.util.List;

public class FriendRepository {

    private FriendDao friendDao;
    private LiveData<List<Friend>> allFriends;

    public FriendRepository(Application application) {
        BookingDatabase database = BookingDatabase.getInstance(application);
        friendDao = database.friendDao();
        allFriends = friendDao.getAllFriends();
    }

    public void insert(Friend friend) {
        new InsertFriendAsyncTask(friendDao).execute(friend);
    }

    public void update(Friend friend) {
        new UpdateFriendAsyncTask(friendDao).execute(friend);
    }

    public void delete(Friend friend) {
        new DeleteFriendAsyncTask(friendDao).execute(friend);
    }

    public void deleteAllFriends() {
        new DeleteAllFriendAsyncTask(friendDao).execute();
    }

    public LiveData<List<Friend>> getAllFriends() {
        return allFriends;
    }

    private static class InsertFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;

        private InsertFriendAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(Friend... friends) {
            friendDao.insert(friends[0]);
            return null;
        }
    }
    private static class UpdateFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;

        private UpdateFriendAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(Friend... friends) {
            friendDao.update(friends[0]);
            return null;
        }
    }
    private static class DeleteFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;

        private DeleteFriendAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(Friend... friends) {
            friendDao.delete(friends[0]);
            return null;
        }
    }
    private static class DeleteAllFriendAsyncTask extends AsyncTask<Friend, Void, Void> {
        private FriendDao friendDao;

        private DeleteAllFriendAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(Friend... friends) {
            friendDao.deleteAllFriends();
            return null;
        }
    }
}