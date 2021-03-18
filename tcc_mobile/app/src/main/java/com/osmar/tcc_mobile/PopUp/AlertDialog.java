package com.osmar.tcc_mobile.PopUp;

import android.content.Context;

public class AlertDialog{
    protected android.app.AlertDialog.Builder alertDialog;
    public AlertDialog(Context context, String titulo, String mensagem){
        this.alertDialog = new android.app.AlertDialog.Builder(context);
        this.alertDialog.setTitle(titulo);
        this.alertDialog.setMessage(mensagem);
    }

    public android.app.AlertDialog.Builder getAlertDialog() {
        return alertDialog;
    }

    public void setAlertDialog(android.app.AlertDialog.Builder alertDialog) {
        this.alertDialog = alertDialog;
    }
}
