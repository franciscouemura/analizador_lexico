/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_lexico;

/**
 *
 * @author Murilo Marcineiro
 */
public class TabelaSimbolos {
    private int memoria;
    private ArrayList<Registro> registros;
    private TabelaSimbolos tabelaPai;

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public void setRegistro(Registro registro) {
        this.registros.add(registro);
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public TabelaSimbolos getTabelaPai() {
        return tabelaPai;
    }

    public void setTabelaPai(TabelaSimbolos tabelaPai) {
        this.tabelaPai = tabelaPai;
    }
    
    
}