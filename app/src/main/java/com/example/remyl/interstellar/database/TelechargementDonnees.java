package com.example.remyl.interstellar.database;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.remyl.interstellar.config.Constante;

public class TelechargementDonnees extends AsyncTask
{
    private Activity activity;
    private Context context;
    private ProgressDialog progressDialog;
    private DownloadManager.Request req;
    private Constante constante;
    private long enqueue = 0;

    public TelechargementDonnees(Activity activity, Context context)
    {
        this.activity = activity;
        this.context = context;
    }

    //Avant execution lancement d'une barre de progression
    protected void onPreExecute()
    {
        super.onPreExecute();
        Log.d("DEBUG", "TELECHARGEMENT STARTED: ");

        this.progressDialog = new ProgressDialog(this.activity);
        this.progressDialog.setMessage("Récupération des données...");
        this.progressDialog.setIndeterminate(true);
        this.progressDialog.setCancelable(true);
        this.progressDialog.show();
    }


    @Override
    protected Object doInBackground(Object[] objects)
    {
        Log.d("DEBUG", "DOINBACKGROUND");
        downloadPackage();
        return true;
    }





    //Lancement du téléchargement du dossier contenant
    private void downloadPackage()
    {
        DownloadManager dm = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        req = new DownloadManager.Request(Uri.parse(constante.url));
        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(true)
                .setTitle("CommunicoTool Package")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                        "tmp_pack_insterstellar.zip");
        enqueue = dm.enqueue(req);
    }

    BroadcastReceiver onComplete=new BroadcastReceiver()
    {
        public void onReceive(Context ctxt, Intent intent)
        {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                TacheAsynchrone tacheAsynchrone =
                        new TacheAsynchrone(root, String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                + "/tmp_pack.zip"));
                tacheAsynchrone.execute();
                progressDialog.dismiss();

            }
        }
    };



}
