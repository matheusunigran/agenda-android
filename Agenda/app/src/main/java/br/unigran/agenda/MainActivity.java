package br.unigran.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import br.unigran.bancoDados.DBHelper;
import br.unigran.bancoDados.ContatoDB;

public class MainActivity extends AppCompatActivity {

    EditText  nome;
    EditText  telefone;
    ListView  listagem;
    List<Contato>dados;
    DBHelper db;
    ContatoDB contatoDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //banco de dados
        db = new DBHelper(this);
        //mapeia campos da tela
        Integer atualiza;
        nome = findViewById(R.id.nameID);
        telefone = findViewById(R.id.phoneID);
        listagem = findViewById(R.id.listID);
        dados = new ArrayList(); //aloca lista
        //vincula adapter
        ArrayAdapter adapter = new ArrayAdapter(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(adapter);
        contatoDB = new ContatoDB(db);
        contatoDB.lista(dados);//lista incial
        acoes();
    }

    public boolean atualizando(){

    }

    private void acoes() {
        listagem.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView,
                                                   View view, int i, long l) {
                        new AlertDialog.Builder(view.getContext())
                                .setMessage("Deseja realmente remover")
                                .setPositiveButton("Confirmar",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface,
                                                                int k) {
                                                contatoDB.remover(dados.get(i).getId());
                                                contatoDB.lista(dados);
                                            }
                                        })
                                .setNegativeButton("cancelar",null)
                                .create().show();
                        return false;
                    }
                });
        listagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                atualiza = dados.get(i).getId();
                nome.setText(dados.get(i).getNome());
                telefone.setText(dados.get(i).getTelefone().toString());
            }
        });
    }

    public void salvar(View view){
        Contato contato =new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
       // dados.add(contato);
        contatoDB.inserir(contato);
        contatoDB.lista(dados);
        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();


    }
}