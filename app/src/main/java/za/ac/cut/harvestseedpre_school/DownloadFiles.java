package za.ac.cut.harvestseedpre_school;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class DownloadFiles extends AsyncTask<URL, Void, Bitmap> {

    ImageView imageView;
    public DownloadFiles(ImageView view) {
        imageView = view;
    }
    @Override
    protected Bitmap doInBackground(URL... urls) {
        for (URL url : urls) {
            try {
                HttpURLConnection httpURLConn = (HttpURLConnection) url.openConnection();
                int responseCode = httpURLConn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = httpURLConn.getInputStream();
                    Bitmap imageBitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    imageBitmap = ReuseClass.getRoundedShape(imageBitmap);
                    return imageBitmap;


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);

        } else {
            imageView.setImageResource(R.drawable.ic_person);
        }

    }
}
