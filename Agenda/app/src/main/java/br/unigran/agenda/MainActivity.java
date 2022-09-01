package br.unigran.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
        //banco de dados
        db = new DBHelper(this);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.nameID);
        telefone = findViewById(R.id.phoneID);
        listagem = findViewById(R.id.listID);
        dados = new ArrayList();
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(adapter);
        contatoDB = new ContatoDB(db);
        contatoDB.lista(dados);

        listagem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alert =
                        new AlertDialog.Builder(getApplicationContext());
                alert.setMessage("Confirmar");
                alert.setPositiveButton("remover",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                alert.setMessage("a");
                return false;
            }
        }


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