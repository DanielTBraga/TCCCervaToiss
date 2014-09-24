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
import tcc.cerva.toiss.bean.Cerveja;
import tcc.cerva.toiss.dao.CervejaDAO;

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
        inflater.inflate(R.menu.menulistacerv, menu);
        return true;
    }

     /*
     *
     * Opções do Menu
     *
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCerveja:
                Intent i1 = new Intent(this, AddCerveja.class);
                startActivityForResult(i1, 0);
                return true;
            case R.id.editCerveja:
                return true;
            case R.id.delCerveja:
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
     * Metodo utilizado para Listar todas as Categorias
     * 
     * */
    private void atualiza() {
        CervejaDAO cervDAO = new CervejaDAO(this);
        List<Cerveja> lista = cervDAO.listaTodos();
        setListAdapter(new ArrayAdapter(this, R.layout.listacerveja, lista));
    }
}
