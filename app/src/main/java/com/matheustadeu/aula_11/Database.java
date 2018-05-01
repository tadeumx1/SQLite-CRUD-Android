package com.matheustadeu.aula_11;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Android on 23/01/2018.
 */

public class Database extends SQLiteOpenHelper {

    private static final String NOME = "aula11.db";
    private static final int    VERSAO = 1;

    public Database(Context context) {
        super(context, NOME, null, VERSAO);



    }

    // - Listagem de métodos necessários

    // - 1° Método Inserir Tarefa

    public void inserirTarefa(Date data_tarefa, String titulo, String descricao) {

        StringBuffer sql = new StringBuffer();

        sql.append("INSERT INTO aula11(data_tarefa,titulo,descricao,executada) ");
        sql.append("VALUES(");
        sql.append("'" + data_tarefa +  "',");
        sql.append("'" + titulo +       "',");
        sql.append("'" + descricao +    "',");
        sql.append(" "  + 0 +            ");");

        getWritableDatabase().execSQL(sql.toString());

    }

    // - 2° Método Listar Tarefa

    public ArrayList<Tarefa> listagemTarefa() {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT id,data_tarefa,titulo,descricao,executada FROM aula11");

        ArrayList<Tarefa> tarefas = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery(sql.toString(), null);

        while(cursor.moveToNext()) {

            Tarefa tarefa = new Tarefa();
            tarefa.setId(cursor.getInt(0));
            tarefa.setTitulo(cursor.getString(2));
            tarefas.add(tarefa);

        }

        cursor.close();

        return tarefas;

    }

    public Tarefa listagemdeTarefa(int id){

        // - Comando para trazer UM dado da tabela

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT id,data_tarefa,titulo,descricao,executada FROM aula11 WHERE id = " + id + ";");

        Cursor cursor = getReadableDatabase().rawQuery(sql.toString(),null);

        Tarefa tarefa = new Tarefa();

        while (cursor.moveToNext()){

            tarefa.setId(cursor.getInt(0));
            tarefa.setTitulo(cursor.getString(2));
            tarefa.setDescricao(cursor.getString(3));

        }

        cursor.close();

        return tarefa;
    }

    // - 3° Método Excluir Tarefa

    public void excluirTarefa(int id) {

        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM aula11 WHERE id = " + id + ";");

        getWritableDatabase().execSQL(sql.toString());

    }

    // - Criando o banco na 1ª vez do acesso

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE aula11 (");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append("data_tarefa DATE,");
        sql.append("titulo      TEXT,");
        sql.append("descricao   TEXT,");
        sql.append("executada   BIT);");

        sqLiteDatabase.execSQL(sql.toString());

    }

    // - Gerencia a mudança da versão

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {



    }

    public void updateTarefa(Date data, String titulo, String descricao, int id){

        // - Comando para atualizar um dado da tabela

        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE aula11 SET ");
        sql.append("data_tarefa = '" + data + "',");
        sql.append("titulo      = '"  + titulo + "',");
        sql.append("descricao   = '"  + descricao + "' ");
        sql.append("WHERE ID    =  "  + id + ";");

        getWritableDatabase().execSQL(sql.toString());

    }

}
