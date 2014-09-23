package tcc.cerva.toiss.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CervaToiss extends Activity {

    /*
     *
     * Inicia Sistema
     *
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telaprincipal);
    }
    
    /*
     *
     * Metodo utilizado para Abrir a tela de Cadastrar Despesa
     *
     * */    
    public void onClickBtAddValor(View v) {
        Intent minhaAcao = new Intent(this, AddValor.class);
        startActivity(minhaAcao);
    }
    
    /*
     *
     * Metodo utilizado para Abrir a tela de Cadastrar Categoria
     *
     * */
    public void onClickBtAddCerveja(View v) {
        Intent minhaAcao = new Intent(this, AddCerveja.class);
        startActivity(minhaAcao);
    }
    
    /*
     *
     * Metodo utilizado para Abrir a tela de Listagem de Despesa
     *
     * */
    public void onClickBtListaValor(View v) {
        Intent minhaAcao = new Intent(this, ListaCerveja.class);
        startActivity(minhaAcao);
    }
    
    /*
     *
     * Metodo utilizado para Abrir a tela de Listagem de Categoria
     *
     * */
    public void onClickBtListaCerveja(View v) {
        Intent minhaAcao = new Intent(this, ListaCerveja.class);
        startActivity(minhaAcao);
    }
    
    /*
     *
     * Metodo utilizado para Abrir a tela de Transferencia para Servidor
     *
     * */
    public void onClickBtServidor(View v) {
        Intent minhaAcao = new Intent(this, SincronizarServidor.class);
        startActivity(minhaAcao);
    }
    
}
