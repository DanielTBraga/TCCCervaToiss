/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import tcc.cerva.toiss.bean.Cerveja;
import tcc.cerva.toiss.dao.CervejaDAO;

/**
 *
 * @author Daniel
 */
public class AddCerveja extends Activity {

    /*
     *
     * Iniciar a tela
     * 
     * */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.addcerveja);
    }

    /*
     *
     * Metodo utilizado para Salvar uma Cerveja
     *
     * */
    public void onClickBtSalvarCerv(View v) {
        EditText desc = (EditText) findViewById(R.id.txtDescCerv);
        CervejaDAO cervDAO = new CervejaDAO(this);
        Cerveja cerv = new Cerveja();
        cerv.setDescricao(desc.getText().toString());
        cervDAO.create(cerv);
        finish();
    }
}
