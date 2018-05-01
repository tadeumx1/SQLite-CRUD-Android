package com.matheustadeu.aula_11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText edtTarefas;
    private EditText edtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTarefas = (EditText) findViewById(R.id.edtTarefas);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);

    }

    public void gravarTarefa(Date data, String titulo, String descricao) {

        Database database = new Database(this);
        database.inserirTarefa(data, titulo, descricao);

        Toast.makeText(this,"Dados gravados ! ", Toast.LENGTH_LONG).show();

        edtTarefas.setText("");
        edtDescricao.setText("");
        edtTarefas.requestFocus();

    }

    public void onSalvar(View view) {

        Date date = new Date();

        // edtTitulo

        String titulo = edtTarefas.getEditableText().toString();
        String descricao = edtDescricao.getEditableText().toString();

        gravarTarefa(date, titulo, descricao);

    }

    public void onListar (View view) {

        Intent intent = new Intent(this, TarefasActivity.class);
        startActivity(intent);


    }

}
