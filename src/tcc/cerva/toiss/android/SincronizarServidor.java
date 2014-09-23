/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.android;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import tcc.cerva.toiss.bean.Valor;
import tcc.cerva.toiss.dao.ValorDAO;

/**
 *
 * @author Daniel
 */
public class SincronizarServidor extends ListActivity {

    private List lista = new ArrayList();
    private Handler manipulador = new Handler() {

        /**
         *
         * mensagem de confirmação
         */
        @Override
        public void handleMessage(Message msg) {
            atualiza();
            Toast.makeText(getApplicationContext(), "Enviado", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     *
     * Inicia tela
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.atualiza();
    }

    /**
     *
     * Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menufazconexaoweb, menu);
        return true;
    }
    
    /**
     *
     * Opções Menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnFazConexao:
                Thread t = new Thread(new ConexaoWWW(this));
                t.start();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    
    private void atualiza() {
        setListAdapter(new ArrayAdapter(this, R.layout.fazconexaoweb, this.lista));
    }
    
    /**
     *
     * Classe de Conexao
     */
    private class ConexaoWWW implements Runnable {

        private Context con;

        public ConexaoWWW(Context con) {
            this.con = con;
        }

        public void run() {
            try {
                URL urlObj = new URL("http://192.168.0.107:8080/DEP-Servlet/Servidor");
                HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setUseCaches(false);
                httpConn.setRequestMethod("POST");
                httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpConn.setRequestProperty("Content-Language", "pt-BR");
                httpConn.setRequestProperty("Accept", "application/octet-stream");
                httpConn.setRequestProperty("Connection", "close");
                DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());

                ValorDAO valDAO = new ValorDAO(con);
                List<Valor> listaValor = valDAO.listaTodos();
                for (Valor val : listaValor) {
                    dos.writeUTF(val.getValor());
                    dos.writeUTF(val.getData());
                    dos.writeInt(val.getCodCerv());
                    valDAO.deletar(val.getCod());
                }
                dos.writeUTF("#Encerra leitura#"); // FIM PORQUE ENVIOU "#ENCERRA LEITURA#";
                dos.close();
                String msg = httpConn.getResponseMessage();
                int code = httpConn.getResponseCode();
                if (code == 200) {
                    DataInputStream dis = new DataInputStream(httpConn.getInputStream());
                    String leitura = null;
                    do {
                        leitura = dis.readUTF();
                        if (!"#Encerra leitura#".equals(leitura)) {
                            Log.i(SincronizarServidor.class.getName(), String.format("Leitura: %s", leitura));
                            lista.add(leitura);
                        }
                    } while (!"#Encerra leitura#".equals(leitura) && leitura != null);
                    manipulador.sendEmptyMessage(0);
                    dis.close();
                } else {
                    Log.i(SincronizarServidor.class.getName(), String.format("Código invalido:: %s", msg));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
