package com.matheustadeu.aula_11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AlterarTarefa extends AppCompatActivity {

    private EditText edtTarefas;
    private EditText edtDescricao;

    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_tarefa);

        edtTarefas = (EditText) findViewById(R.id.edtTarefas);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");

        Tarefa tarefa = new Tarefa();

        Database database = new Database(this);
        tarefa = database.listagemdeTarefa(id);

        edtTarefas.setText(tarefa.getTitulo());
        edtDescricao.setText(tarefa.getDescricao());

    }

    public void atualizarTarefa(Date data, String titulo, String descricao, int id) {

        Database database = new Database(this);
        database.updateTarefa(data, titulo, descricao, id );

        Toast.makeText(this,"Dados atualizados ! ", Toast.LENGTH_LONG).show();

        edtTarefas.setText("");
        edtDescricao.setText("");
        edtTarefas.requestFocus();

    }

    public void onSalvar(View view) {

        Date date = new Date();

        // edtTitulo

        String titulo = edtTarefas.getEditableText().toString();
        String descricao = edtDescricao.getEditableText().toString();

        atualizarTarefa(date, titulo, descricao, id);

    }

    public void onListar (View view) {

        Intent intent = new Intent(this, TarefasActivity.class);
        startActivity(intent);


    }

}

