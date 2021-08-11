package za.ac.cut.harvestseedpre_school;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;


public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    EditText etName, etSurname, etEmail, etPassword, etRePassword, etPhone;
    ImageView ivProfileImage;
    LinearLayout registerLayout;
    BackendlessUser backendlessUser;
    SpotsDialog dialog;
    Date dateTimeNote;
    private static final int IMAGE_CAPTURE = 123;
    private static final int IMAGE_PICK = 223;
    private static final int TYPE = 1;
    private static final String DIRECTORY = "Profile User Images";
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        ReuseClass.setSupportActionBarView(this, "Register").setIcon(R.mipmap.ic_launcher);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText) findViewById(R.id.etRePassword);
        etPhone = (EditText) findViewById(R.id.etCellPhone);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        registerLayout = (LinearLayout) findViewById(R.id.registerLayout);
        backendlessUser = new BackendlessUser();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:

                if (etName.getText().toString().trim().isEmpty() && etSurname.getText().toString().trim().isEmpty()
                        && etEmail.getText().toString().trim().isEmpty() && etPassword.getText().toString().trim().isEmpty()
                        && etRePassword.getText().toString().trim().isEmpty() && etPhone.getText().toString().trim().isEmpty()) {
                    ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Please enter all fields!", Snackbar.LENGTH_LONG).show();
                } else if (!(etPassword.getText().toString().trim().equals(etRePassword.getText().toString()))) {
                    ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Make certain that passwords match", Snackbar.LENGTH_LONG).show();
                } else {

                    if (ReuseClass.isConnectionAvailable(getApplicationContext())) {
                        backendlessUser.setProperty("name", etName.getText().toString().trim());
                        backendlessUser.setProperty("Surname", etSurname.getText().toString().trim());
                        backendlessUser.setProperty("email", etEmail.getText().toString().trim());
                        backendlessUser.setProperty("phone", etPhone.getText().toString().trim());
                        backendlessUser.setProperty("password", etPassword.getText().toString().trim());
                        backendlessUser.setProperty("role", "None");

                        dialog = new SpotsDialog(RegisterPage.this, R.style.SpotsDialogCustom);
                        dialog.show();

                        Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser backendlessUser) {


                                ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Please confirm email before logging in!", Snackbar.LENGTH_LONG).show();
                                if (backendlessUser != null) {
                                    if (ImageUri != null) {

                                        try {

                                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUri);
                                            String filename = backendlessUser.getProperty("email") + "_.png";
                                            String filepathname = "ImageProfiles";
                                            Backendless.Files.Android.upload(bitmap,
                                                    Bitmap.CompressFormat.JPEG, 100, filename, filepathname,
                                                    new AsyncCallback<BackendlessFile>() {
                                                        @Override
                                                        public void handleResponse(BackendlessFile backendlessFile) {

                                                        }

                                                        @Override
                                                        public void handleFault(BackendlessFault backendlessFault) {
                                                            ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, backendlessFault.getMessage(), Snackbar.LENGTH_LONG).show();


                                                        }
                                                    });
                                        } catch (IOException e) {
                                            e.printStackTrace();

                                        }
                                    }
                                    dateTimeNote = new Date();
                                    List<String> channels = new ArrayList<String>();
                                    channels.add("Master");

                                    Backendless.Messaging.registerDevice("758153085062", channels, dateTimeNote, new AsyncCallback<Void>() {

                                        @Override
                                        public void handleResponse(Void response) {
                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                            Notifications();
                                            //showCustomToast("Error " + fault.getMessage());

                                        }
                                    });
                                }
                                startActivity(new Intent(RegisterPage.this, LoginPage.class));
                                dialog.dismiss();
                                RegisterPage.this.finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault backendlessFault) {
                                ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, backendlessFault.getMessage(), Snackbar.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                        Notifications();

                    } else {
                        ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Internet Connection is not available!", Snackbar.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IMAGE_CAPTURE: {
                if (resultCode == RESULT_OK) {

                    ivProfileImage.setImageURI(ImageUri);

                } else {
                    ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Image Capture cancelled", Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case IMAGE_PICK: {

                if (resultCode == RESULT_OK) {
                    ImageUri = data.getData();
                    ivProfileImage.setImageURI(ImageUri);
                } else {
                    ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Image pick cancelled", Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            default: {
                ReuseClass.setSupportSnackBar(getApplicationContext(), registerLayout, "Image not captured", Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
    public void Notifications()
    {
        String message = "New User Role" +
                "Request Role" +
                "#2. Master's Permission only" +
                "#3. Email :"+backendlessUser.getEmail();


        PublishOptions options = new PublishOptions();
        options.putHeader("android-ticker-text", "Roles");
        options.putHeader("android-content-title","New User Role" );
        options.putHeader("android-content-text", "Role Request");

        Backendless.Messaging.publish("Master", message, options, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {


            }

            @Override
            public void handleFault(BackendlessFault fault) {
               // showCustomToast("Error " + fault.getMessage());


            }
        });
    }
    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), DIRECTORY);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(DIRECTORY, "Failed to create the \""
                        + DIRECTORY + "\" directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());

        File mediaFile;


        if (type == TYPE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ImageUri = getOutputMediaFileUri(TYPE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUri);
        startActivityForResult(intent, IMAGE_CAPTURE);

    }

    public void photoClickAdd(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        popup.inflate(R.menu.menu_camera_items);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mnu_capture:
                        takePhoto();
                        return true;
                    case R.id.mnu_pick:
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();

    }
    private boolean cameraAvailable() {
        return (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA));
    }

}
