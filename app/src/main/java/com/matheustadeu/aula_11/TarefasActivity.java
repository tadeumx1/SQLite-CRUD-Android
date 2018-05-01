package com.matheustadeu.aula_11;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TarefasActivity extends AppCompatActivity {

    private ListView lsvTarefas;
    private Database database;
    private ArrayList<Tarefa> tarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        lsvTarefas = (ListView) findViewById(R.id.lsvTarefas);
        database = new Database(this);
        tarefas = database.listagemTarefa();

        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);

        lsvTarefas.setAdapter(adapter);

        lsvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(TarefasActivity.this)
                        .setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button

                                alterar(position);

                            }
                        })
                        .setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog

                                excluir(position);

                            }
                        });

                builder.setMessage("O que deseja fazer ?")
                        .setTitle("Tarefa");

                builder.show();

                return false;

            }
        });

    }

    public void excluir(int i) {

        Tarefa tarefa = tarefas.get(i);

        database.excluirTarefa(tarefa.getId());

        Intent intent = new Intent(TarefasActivity.this, TarefasActivity.class);
        startActivity(intent);

        Toast.makeText(TarefasActivity.this, tarefa.getTitulo() + " Foi excluído ! ", Toast.LENGTH_LONG).show();

    }

    public void alterar(int i) {

        Tarefa tarefa = tarefas.get(i);
        database.listagemdeTarefa(tarefa.getId());

        Intent intent = new Intent(TarefasActivity.this, AlterarTarefa.class);
        intent.putExtra("id",tarefa.getId());
        startActivity(intent);

    }

    public void onVoltar(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
