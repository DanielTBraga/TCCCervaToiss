/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import tcc.cerva.toiss.bean.Cerveja;
import tcc.cerva.toiss.bean.Valor;
import tcc.cerva.toiss.dao.CervejaDAO;
import tcc.cerva.toiss.dao.ValorDAO;

/**
 *
 * @author Daniel
 */
public class AddValor extends Activity {

    private List<Cerveja> listaC;
    private List<String> cervejaNomes;
    private CervejaDAO cervejaDB;
    private Spinner categ;
    private ArrayAdapter<String> adapterCerveja;
    private EditText valor;
    private EditText data;

    /*
     * 
     * Metodo para Iniciar tela e popular Spinner de Cerveja
     * 
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addvalor);
        valor = (EditText) findViewById(R.id.inputValor);
        data = (EditText) findViewById(R.id.inputData);
        categ = (Spinner) findViewById(R.id.inputCerveja);
        cervejaDB = new CervejaDAO(this);
        listaC = cervejaDB.listaTodos();
        cervejaNomes = fillListaCerveja(listaC);
        adapterCerveja = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cervejaNomes);
        adapterCerveja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categ.setAdapter(adapterCerveja);
    }
    /*
     *
     * Metodo utilizado para Salvar uma Despesa
     *
     * */

    public void onClickBtSalvar(View v) {
        ValorDAO valDAO = new ValorDAO(this);
        Valor val = new Valor();
        val.setValor(valor.getText().toString());
        val.setData(data.getText().toString());
        val.setCodCerv(listaC.get(categ.getSelectedItemPosition()).getCod());
        valDAO.create(val);
        finish();
    }

    /*
     *
     * Metodo utilizado para Cancelar a Inclusao
     *
     * */
    public void onClickBtCancelar(View v) {
        finish();
    }

    /*
     *
     * Metodo utilizado para Popular Spinner de Categoria
     *
     * */
    private List<String> fillListaCerveja(List<Cerveja> l) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < l.size(); i++) {
            list.add(l.get(i).getDescricao());
        }
        return list;

    }

}
