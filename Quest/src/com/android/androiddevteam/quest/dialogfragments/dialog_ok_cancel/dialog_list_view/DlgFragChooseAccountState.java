//package com.android.androiddevteam.quest.dialogfragments.dialog_ok_cancel.dialog_list_view;
//
//import android.app.Activity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.text.TextUtils;
//import android.widget.AdapterView;
//import com.zeus.android.ols.app.adapters.adapters_internal.crm.account.ListAdapterAccountStateItem;
//import com.zeus.android.ols.app.fragments.fragments_internal.crm.account.FragCRMAccountCreate;
//import com.zeus.android.ols.app.items.state_items.AccountStatesItem;
//
//import java.util.ArrayList;
//
//public class DlgFragChooseAccountState extends DlgFragAbstractListView
//        implements AdapterView.OnItemClickListener{
//
//
//    private static final String DIALOG_TITLE = "";
//    private static final String DIALOG_TAG = "DlgFragChooseAccountState";
//
//    @Override
//    protected String getTitle() {
//        return DIALOG_TITLE;
//    }
//
//    @Override
//    public String getDialogTag() {
//        return DIALOG_TAG;
//    }
//
//    @Override
//    protected ListAdapterAccountStateItem getAdapter() {
//        ArrayList<AccountStatesItem> accountStatesItems = new ArrayList<AccountStatesItem>();
//        AccountStatesItem accountStatesItem = new AccountStatesItem();
//        for (int i = 0; i < accountStatesItem.getStatesCount(); ++i) {
//            accountStatesItems.add(i, accountStatesItem);
//        }
//
//        return new ListAdapterAccountStateItem(accountStatesItems, getActivity());
//    }
//
//    @Override
//    protected void performOK(DialogInterface dialog, int id) {
//        if (getTargetFragment() != null && stateImageId > 0 && !TextUtils.isEmpty(stateName)){
//            Intent intent = new Intent();
//            intent.putExtra(FragCRMAccountCreate.BUNDLE_EXTRA_STRING_STATE_NAME, stateName);
//            intent.putExtra(FragCRMAccountCreate.BUNDLE_EXTRA_INT_STATE_IMAGE, stateImageId);
//
//            getTargetFragment().onActivityResult(getTargetRequestCode(),
//                    Activity.RESULT_OK,
//                    intent);
//            dismiss();
//        }
//    }
//
//    @Override
//    protected void performCancel(DialogInterface dialog, int id) {
//        if (getTargetFragment() != null){
//
//            getTargetFragment().onActivityResult(getTargetRequestCode(),
//                    Activity.RESULT_CANCELED,
//                    null);
//
//            dismiss();
//        }
//    }
//}
