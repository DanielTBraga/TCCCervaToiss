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
import tcc.cerva.toiss.bean.Valor;
import tcc.cerva.toiss.util.ConexaoBanco;

/**
 *
 * @author Daniel
 */
public class ValorDAO {

    private Context context;

    public ValorDAO(Context context) {
        this.context = context;
    }
   
    public List<Valor> listaTodos() {
        List<Valor> listaVal = new ArrayList<Valor>();
        ConexaoBanco bd = new ConexaoBanco(this.context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        Cursor cursor = conn.rawQuery("SELECT valor.cod, valor.valor, valor.data, valor.codCerv, "
                + "cerveja.cod, cerveja.descricao FROM valor JOIN cerveja ON "
                + "valor.codCerv = cerveja.cod ORDER BY valor.data, cerveja.descricao ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Valor val = new Valor();
            val.setCod(cursor.getInt(0));
            val.setValor(cursor.getString(1));
            val.setData(cursor.getString(2));
            val.setCodCerv(cursor.getInt(3));
            val.setCodCatCerveja(cursor.getInt(4));
            val.setDescricaoCerveja(cursor.getString(5));
            listaVal.add(val);
            cursor.moveToNext();
        }
        conn.close();
        return listaVal;
    }

    public void create(Valor val) {
        ConexaoBanco bd = new ConexaoBanco(this.context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        ContentValues valores = new ContentValues(1);
        valores.put("valor", val.getValor());
        valores.put("data", val.getData());
        valores.put("codCerv", val.getCodCerv());
        conn.insert("valor", null, valores);
        conn.close();
    }

    public void deletar(int cod) {
        ConexaoBanco bd = new ConexaoBanco(this.context);
        SQLiteDatabase conn = bd.getWritableDatabase();
        String sql = "DELETE FROM valor WHERE cod = '" + cod + "'";
        conn.execSQL(sql);
    }
}
