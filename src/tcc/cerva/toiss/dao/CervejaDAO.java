/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import tcc.cerva.toiss.bean.Cerveja;
import tcc.cerva.toiss.util.ConexaoBanco;

/**
 *
 * @author Daniel
 */
public class CervejaDAO {
    
    private Context context;

    public CervejaDAO(Context context) {
        this.context = context;
    }
    
    public List<Cerveja> listaTodos() {
        List<Cerveja> listaCat = new ArrayList<Cerveja>();
        ConexaoBanco bd = new ConexaoBanco(this.context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT * FROM CERVEJA order by descricao", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cerveja cat = new Cerveja();
            cat.setCod(cursor.getInt(0));
            cat.setDescricao(cursor.getString(1));
            listaCat.add(cat);
            cursor.moveToNext();
        }
        conn.close();
        return listaCat;
    }
    public void create(Cerveja cerv) {
        ConexaoBanco bd = new ConexaoBanco(this.context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues(1);
        valores.put("valor", cerv.getDescricao());
        conn.insert("cerveja", null, valores);
        conn.close();
    }
    public void delete(long x) {
        ConexaoBanco bd = new ConexaoBanco(this.context);
        SQLiteDatabase conn = bd.getReadableDatabase();
        long id = x;
        String _id = String.valueOf(id);
        conn.delete("cerveja", "cod=?", new String[]{_id});
        conn.close();
    }
    
}
