/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador_lexico;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco
 */
public class ItemTabela {
    private int hash;
    private Lexema item;
    private ItemTabela proximo;
    private ItemTabela anterior;

    public ItemTabela(Lexema item, ItemTabela proximo, ItemTabela anterior, int hash) {
        this.hash = hash;
        this.item = item;
        this.proximo = proximo;
        this.anterior = anterior;
    }
    
    public int getHash(){
        return hash;
    }

    public Lexema getItem() {
        return item;
    }


    public ItemTabela getProximo() {
        return proximo;
    }

    public void setProximo(ItemTabela proximo) {
        this.proximo = proximo;
    }

    public ItemTabela getAnterior() {
        return anterior;
    }

    public void setAnterior(ItemTabela anterior) {
        this.anterior = anterior;
    }

    private ItemTabela ultimo(ItemTabela itemtabela){
        ItemTabela resultado=itemtabela;
        if(itemtabela.getProximo()!= null){
            resultado = ultimo(itemtabela.getProximo());
        }
        return resultado;
    }
    public void insereProximo(ItemTabela pai, ItemTabela filho){
        pai = ultimo(pai);
        pai.proximo= filho;
        filho.anterior =pai;
    }
    
    public boolean existe(ItemTabela pai,String lexema, String categoria){
        boolean resultado=true;
        if(pai==null){
            resultado=false;
        }else{
            if(!pai.getItem().getTexto().equals(lexema) || !pai.getItem().getCategoria().equals(categoria)){
                resultado = existe(pai.proximo,lexema, categoria);
            }
        }
     return resultado;
    }
    
    public void imprimeItensTabela(ItemTabela item, DefaultTableModel model){
        if(item!=null){
            model.addRow(new Object[]{item.getItem().getTexto(), item.getItem().getCategoria(), item.getItem().getTipo()});
            imprimeItensTabela(item.proximo, model);
        }
    }
}
