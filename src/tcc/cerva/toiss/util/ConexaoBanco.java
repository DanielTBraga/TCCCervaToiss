/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Daniel
 */
public class ConexaoBanco extends SQLiteOpenHelper {
    private Context context;

    public ConexaoBanco(Context context) {
        super(context, "dependencia", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cerveja (cod INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT NOT NULL);");
        db.execSQL("CREATE TABLE valor (cod INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "valor REAL, data TEXT, codCerv INTEGER, FOREIGN KEY (codCerv) REFERENCES cerveja (cod));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS valor;");
        db.execSQL("DROP TABLE IF EXISTS cerveja;");
        this.onCreate(db);
    }
    
    
}