package com.osmar.tcc_mobile.PopUp;

import android.content.Context;

public class AlertDialogAdComponente extends AlertDialog{
    public AlertDialogAdComponente(Context context, String titulo, String mensagem) {
        super(context, titulo, mensagem);
        this.alertDialog.setCancelable(false);
    }
}
