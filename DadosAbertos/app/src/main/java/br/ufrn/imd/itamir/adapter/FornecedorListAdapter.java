package br.ufrn.imd.itamir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.ufrn.imd.itamir.dadosabertosandroid.R;
import br.ufrn.imd.itamir.dominio.Fornecedor;

/**
 * Created by Will Xavier on 12/05/2015.
 */
public class FornecedorListAdapter extends ArrayAdapter<Fornecedor> {

    public FornecedorListAdapter(Context context, int textViewResourceId, List<Fornecedor> objects){
        super(context, textViewResourceId, objects);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_fornecedores, null);
        }

        Fornecedor item = getItem(position);
        if(item != null){
            TextView titleText = (TextView) view.findViewById(R.id.txtnome);
            titleText.setText(item.getNome());
            TextView cnpjText =  (TextView) view.findViewById(R.id.txtcpnj);
            cnpjText.setText(item.getCnpj());
            TextView count = (TextView) view.findViewById(R.id.countTxt);
            int pos = position + 1;
            count.setText("Fornecedor numero: " + pos);
        }

        return view;
    }
}
