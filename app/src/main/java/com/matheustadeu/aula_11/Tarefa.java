package com.matheustadeu.aula_11;

/**
 * Created by Android on 24/01/2018.
 */

public class Tarefa {

        private int id;
        private String titulo;
        private String descricao;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDescricao() { return descricao; }

        public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        // return super.toString();

        String idTitulo;

        idTitulo = " ID : " +  getId() + " ||  Título " + getTitulo();

        return idTitulo;

        // return getTitulo();

    }
}
