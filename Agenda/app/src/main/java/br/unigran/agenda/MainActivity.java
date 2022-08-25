package br.unigran.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText  nome;
    EditText  telefone;
    ListView  listagem;
    List<Contato>dados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.nameID);
        telefone = findViewById(R.id.phoneID);
        listagem = findViewById(R.id.listID);
        dados = new ArrayList();
        ArrayAdapter adapter =
                new ArrayAdapter

    }

    public void salvar(View view){
        Contato contato =new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(telefone.getText().toString());
        dados.add(contato);
        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();


    }
}