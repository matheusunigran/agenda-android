package br.unigran.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.unigran.bancoDados.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText  nome;
    EditText  telefone;
    ListView  listagem;
    List<Contato>dados;
    SQLiteDatabase conexao;
    DBHelper db;
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


    }

    public void salvar(View view){
        Contato contato =new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        dados.add(contato);
        db.inserir(contato, db);
        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();


    }
}