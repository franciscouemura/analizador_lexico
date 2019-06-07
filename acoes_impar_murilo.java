//VARIAVEIS GLOBAIS
Lexema lexema;
int nivel, offsetVariavel;
TabelaSimbolos tabelaSimbolos;


public void A01(){
	tabelaSimbolos = new TabelaSimbolos();
	tabelaSimbolos.setTabelaPai(null);
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	nivel = 0;
	registro.setNivel(nivel);
	registro.setCategoria("Programa principal");
	registro.setRotulo("_main");
	offsetVariavel = 0;
	registro.setOffset(offsetVariavel);
	tabelaSimbolos.addRegistro(registro);
	insereLinhaArquivo("global _main");
	insereLinhaArquivo("extern _printf");
	insereLinhaArquivo("extern _putchar");
	insereLinhaArquivo("extern _scanf");
	insereLinhaArquivo("section .text");
	insereLinhaArquivo("_main:");
}

public void A03(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(!tabelaSimbolos.temRegistro(registro)){
		registro.setCategoria("Variavel");
		offsetVariavel += 4; // SIZEOF_INT
		registro.setNivel(nivel);
		registro.setOffset(offsetVariavel*(-1));
	} else {
		//Erro: identificador já declarado anteriormente
	}
}

public void insereLinhaArquivo(String trecho){

}