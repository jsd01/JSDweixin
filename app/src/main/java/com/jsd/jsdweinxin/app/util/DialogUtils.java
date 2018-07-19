package com.jsd.jsdweinxin.app.util;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jsd.jsdweinxin.R;
import com.jsd.jsdweinxin.mvp.ui.widget.CustomDialog;

/**
 * 项目名称：JSDweixin
 * 类描述：
 * 创建人：贾少东
 * 创建时间：2018-07-19 11:24
 * 修改人：jsd
 * 修改时间：2018-07-19 11:24
 * 修改备注：
 */
public class DialogUtils {

    private static MaterialDialog mMaterialDialog;
    private static CustomDialog mDialogWaiting;

    /**
     * 显示等待提示框
     */
    public static Dialog showWaitingDialog(Context context, String tip) {
        hideWaitingDialog();
        View view = View.inflate(context, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomDialog(context, view, R.style.MyDialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    public static void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }

    /**
     * 显示MaterialDialog
     */
    public static MaterialDialog showMaterialDialog(Context context, String title, String message, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback positiveButtonClickListener, MaterialDialog.SingleButtonCallback negativeButtonClickListener) {
        hideMaterialDialog();
        MaterialDialog.Builder mBuilder = new MaterialDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            mBuilder.title(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mBuilder.content(message);
        }
        if (!TextUtils.isEmpty(positiveText)) {
            mBuilder.positiveText(positiveText);
            mBuilder.onPositive(positiveButtonClickListener);
        }
        if (!TextUtils.isEmpty(negativeText)) {
            mBuilder.negativeText(negativeText);
            mBuilder.onNegative(negativeButtonClickListener);
        }
        mMaterialDialog = mBuilder.build();
        return mMaterialDialog;
    }

    /**
     * 隐藏MaterialDialog
     */
    public static void hideMaterialDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }
}
