/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import tcc.cerva.toiss.bean.Valor;
import tcc.cerva.toiss.dao.ValorDAO;

/**
 *
 * @author Daniel
 */
public class ListaCerveja extends ListActivity {

    /*
     *
     * Inicia tela
     *
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.atualiza();
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     *
     * Menu
     *
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulistaval, menu);
        return true;
    }

    /*
     *
     * Opções do menu
     *
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addValor:
                Intent i1 = new Intent(this, AddCerveja.class);
                startActivityForResult(i1, 0);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.atualiza();
    }
    
    /*
     *
     * Preenche lista com todas as Despesas
     *
     * */
    private void atualiza() {
        ValorDAO valDAO = new ValorDAO(this);
        List<Valor> lista = valDAO.listaTodos();
        setListAdapter(new ArrayAdapter(this, R.layout.listavalor, lista));
    }
}
