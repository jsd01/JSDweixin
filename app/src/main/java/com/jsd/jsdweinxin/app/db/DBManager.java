package com.jsd.jsdweinxin.app.db;

import android.content.ContentValues;
import android.net.Uri;
import android.text.TextUtils;

import com.jsd.jsdweinxin.app.db.model.Friend;
import com.jsd.jsdweinxin.app.db.model.GroupMember;
import com.jsd.jsdweinxin.app.db.model.Groups;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

/**
 * @创建者 CSDN_LQR
 * @描述 数据库管理器
 */
public class DBManager {

    private static DBManager mInstance;

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }
}
