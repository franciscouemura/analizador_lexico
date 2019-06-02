//VARIAVEIS GLOBAIS
Lexema lexema;
int nivel;
TabelaSimbolos tabelaSimbolos;


public void A01(){
	tabelaSimbolos = new TabelaSimbolos();
	tabelaSimbolos.setTabelaPai(null);
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	//registro.setCategoria(?);
	nivel = 0;
	registro.setNivel(nivel);
	registro.setCategoria("Programa principal");
	registro.setRotulo("_main");
	int offsetVariavel = 0;
	registro.setOffset(offsetVariavel);
	insereLinhaArquivo("global _main");
	insereLinhaArquivo("section .text")
	insereLinhaArquivo("_main:");
}

public void A02(){

}


public void A03(){
	
}
public void insereLinhaArquivo(String trecho){

}