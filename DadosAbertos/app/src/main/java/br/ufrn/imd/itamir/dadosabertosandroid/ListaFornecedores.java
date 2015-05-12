package br.ufrn.imd.itamir.dadosabertosandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.ufrn.imd.itamir.adapter.FornecedorListAdapter;
import br.ufrn.imd.itamir.dominio.Fornecedor;


public class ListaFornecedores extends ActionBarActivity {

    ListView listaFornecedores;
    FornecedorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fornecedores);
        List<Fornecedor> fornecedores = (List<Fornecedor>) getIntent().getSerializableExtra("fornecedores");

        listaFornecedores = (ListView) findViewById(R.id.listFornecedores);
        adapter = new FornecedorListAdapter(this, R.layout.list_fornecedores,fornecedores);

        listaFornecedores.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_fornecedores, menu);
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
}
