package br.ufrn.imd.itamir.dadosabertosandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.itamir.br.ufrn.imd.itamir.utils.ConexaoHttp;
import br.ufrn.imd.itamir.dominio.Fornecedor;


public class MainActivity extends ActionBarActivity {

    private EditText texto;
    private ProgressDialog progressBar;
    List<Fornecedor> resultado = new ArrayList<Fornecedor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (EditText) findViewById(R.id.ufText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Fornecedor> gerarFornecedoresFromJson(String dados) {
        List<Fornecedor> resultado = new ArrayList<Fornecedor>();
        try {

            JSONObject jsonObject = new JSONObject(dados);
            JSONObject emb = jsonObject.getJSONObject("_embedded");
            JSONArray ja = emb.getJSONArray("fornecedores");
            for(int i = 0; i<= ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                Fornecedor f = new Fornecedor();
                f.setNome(jo.getString("nome"));
                try {
                    f.setCnpj(jo.getString("cnpj"));
                }catch (JSONException e) {
                    f.setCnpj(jo.getString("cpf"));
                }

                resultado.add(f);
            }
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public void buscarFornecedores(View view) {
        progressBar = new ProgressDialog(view.getContext());
        progressBar.setCancelable(true);
        progressBar.setMessage("Buscando...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        new ListagemTask().execute();
    }

    private class ListagemTask extends
            AsyncTask<String,Integer, List<Fornecedor>> {

        @Override
        protected List<Fornecedor> doInBackground(String... urls) {
            String url = "http://compras.dados.gov.br/" +
                    "fornecedores/v1/fornecedores.json?" +
                    "uf="+texto.getText().toString();
            String resposta = ConexaoHttp.get(url);
            List<Fornecedor> fornecedores =
                    gerarFornecedoresFromJson(resposta);
            publishProgress(10);
            return fornecedores;
        }
        @Override
        protected void onPostExecute(List<Fornecedor> result) {
            super.onPostExecute(result);
            publishProgress(100);
            progressBar.dismiss();
            Intent i = new Intent(MainActivity.this,ListaFornecedores.class);
            i.putExtra("fornecedores", (Serializable) result);
            startActivity(i);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            publishProgress(10);
        }
    }
}
