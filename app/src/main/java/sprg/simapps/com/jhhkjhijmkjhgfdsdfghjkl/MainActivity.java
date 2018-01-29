package sprg.simapps.com.jhhkjhijmkjhgfdsdfghjkl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Loader loader = new Loader();
        loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }
    class MyTask extends AsyncTask<Void, Integer,Void>

    { TextView tv;


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(""+ values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tv.setText("End.");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv = (TextView) findViewById(R.id.textView);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i=0;i<10;i++){
                publishProgress(i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


   class Loader extends  AsyncTask<String,Void,Bitmap>{

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           super.onPostExecute(bitmap);
           ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bitmap);
       }

       @Override
       protected Bitmap doInBackground(String... strings) {
           Bitmap res = null;
           try {
               URL url = new URL(strings[0]);
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               res = BitmapFactory.decodeResourceStream((getResources() connection.getInputStream());
           }catch (IOException e){
               e.printStackTrace();
           }
               return res;

       }


   }



}
