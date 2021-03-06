package com.hexicode.zend;
/**
 * Created by Brian on 6/9/2016.
 *
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class AddNewImageDialogFragment extends DialogFragment {

    public interface DialogClickListener{
        void takeImagePositiveClick(DialogFragment dialogFragment);
        void uploadImageNegativeClick(DialogFragment dialogFragment);
    }

    // Use this instance of the interface to deliver action events
    DialogClickListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogClickListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DialogClickListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.set_image)
                .setPositiveButton(R.string.take_photo_action, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.takeImagePositiveClick(AddNewImageDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.upload_photo_action, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        mListener.uploadImageNegativeClick(AddNewImageDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}