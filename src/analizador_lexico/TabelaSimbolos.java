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

    public void addRegistro(Registro registro) {
        if(this.registros == null)
            this.registros = new ArrayList<>();
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

    public Registro getEsseRegistro(Registro registro){
        for(Registro r : this.registros){
            if(r.getNome().equals(registro.getNome()))
                return r;
        }
        return this.tabelaPai.getEsseRegistro(registro);
    }
    
    public boolean temRegistro(Registro registro){
        for(Registro r : registros){
            if(registro.getNome().equals(r.getNome()))
                return true;
        }
        return false;
    }

    public boolean temRegistroTodasTabelas(Registro registro){
        if(this.tabelaPai == null){
            return this.temRegistro(registro);
        } else {
            return this.temRegistro(registro) || this.tabelaPai.temRegistroTodasTabelas(registro);
        }
    }

    public int getNumeroRegistroParametro(){
        int n = 0;
        for (Registro registro : this.registros) {
            if(registro.getCategoria()!=null && registro.getCategoria().equals("Parametro"))
                n++;
        }
        return n;
    }
    
}