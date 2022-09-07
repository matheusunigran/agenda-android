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

    EditText nome;
    EditText telefone;
    ListView listagem;
    List<Contato> dados;
    DBHelper db;
    ContatoDB contatoDB;

    Integer atualiza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //banco de dados
        db = new DBHelper(this);
        //mapeia campos da tela
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

    private void acoes() {
        listagem.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView,
                                                   View view, int i, long l) {
                        AlertDialog.Builder mensagem = new AlertDialog.Builder(view.getContext());
                        mensagem.setTitle("Opções");
                        mensagem.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                contatoDB.remover(dados.get(i).getId());
                                contatoDB.lista(dados);
                            }
                        });
                        mensagem.setNegativeButton("Atualizar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                atualiza = dados.get(i).getId();
                                nome.setText(dados.get(i).getNome());
                                telefone.setText(dados.get(i).getTelefone().toString());

                                contatoDB.atualizar(dados.get(i));

                                contatoDB.lista(dados);
                            }
                        });
                        mensagem.setNeutralButton("Cancelar", null);
                        mensagem.show();
                        return false;
                    }
                });
    }

    public void salvar(View view) {
        Contato contato = new Contato();
        if (atualiza != null) {
            contato.setId(atualiza);

            contatoDB.lista(dados);
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
        }
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());

        if (atualiza != null)
            contatoDB.atualizar(contato);
        else {
            contatoDB.inserir(contato);
            contatoDB.lista(dados);
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
        }
        contatoDB.lista(dados);
        atualiza = null;
    }
}