//VARIAVEIS GLOBAIS
Lexema lexema;
int nivel;
TabelaSimbolos tabelaSimbolos;
ArrayList<int> offsetVariavel = new ArrayList<>();


public void A01(){
	tabelaSimbolos = new TabelaSimbolos();
	tabelaSimbolos.setTabelaPai(null);
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	nivel = 0;
	registro.setNivel(nivel);
	registro.setCategoria("Programa principal");
	registro.setRotulo("_main");
	offsetVariavel.add(0);
	registro.setOffset(offsetVariavel.get(nivel));
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
		offsetVariavel.get(nivel) += 4; // SIZEOF_INT
		registro.setNivel(nivel);
		registro.setOffset(offsetVariavel.get(nivel)*(-1));
	} else {
		//Erro: identificador já declarado anteriormente
	}
}

public void A05(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(!tabelaSimbolos.temRegistroTodasTabelas(registro)){
		nivel += 1;
		registro.setNivel(nivel);
		registro.setCategoria("Funcao");
		registro.setRotulo(lexema.getTexto());
		tabelaSimbolos.addRegistro(registro);
		TabelaSimbolos novaTs = new TabelaSimbolos();
		novaTs.setTabelaPai(tabelaSimbolos);
		tabelaSimbolos = novaTs;						// Por este motivo, add na acao 56:  tabelaSimbolos = tabelaSimbolos.getTabelaPai();

		//Inserir o identificador da função, com todas as suas informações, na nova tabela, para que ela possa ser localizada pela atrubuição em <comando>.-----??

		offsetVariavel.add(0);
	} else {
		//Erro: identificador já declarado anteriormente
	}
}


public void A07(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(!tabelaSimbolos.temRegistro(registro)){
		registro.setCategoria("Parametro");
		registro.setNivel(nivel);
		tabelaSimbolos.addRegistro(registro);
	} else {
		//Erro: identificador já declarado anteriormente
	}
}

public void A09(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(tabelaSimbolos.temRegistroTodasTabelas(registro)){
		registro = tabelaSimbolos.getEsseRegistro(registro);
		if(registro.getCategoria().equals("Variavel") || registro.getCategoria().equals("Parametro")){
			if(registro.getNivel != nivel)
				insereLinhaArquivo(String.format("	mov edx, dword [@DSP + %d]", 4*registro.getNivel()));
			insereLinhaArquivo(String.format("	push dword [edx + (%d)]", registro.getOffset()));
			insereLinhaArquivo("	call _printf");
			insereLinhaArquivo(String.format("	add esp, 8"));
		} else {
			//Erro: identificador não é uma Variável
		}
	} else {
		//Erro: identificador não foi declarado anteriormente
	}
}

public void A11(){
	insereLinhaArquivo(String.format("	mov ebx, [@DSP + (%d)]", nivel*4));

	insereLinhaArquivo(String.format("	pop dword [ebx + (%d)]", offsetVariavel*(-4))); // Como saber qual a variavel? (-4, -8, -12???)
	insereLinhaArquivo(String.format("rotuloFor:"));
}

public void A13(){
	insereLinhaArquivo(String.format(" add dword [ebx + (%d)], 1", offsetVariavel*(-4))); // Como saber qual a variavel? (-4, -8, -12???)
	insereLinhaArquivo(String.format("	jmp rotuloFor"));
	insereLinhaArquivo(String.format("rotuloFimFor:"));
}

public void A15(){
	insereLinhaArquivo(String.format(" pop eax"));
	insereLinhaArquivo(String.format(" cmp eax, 1"));
	insereLinhaArquivo(String.format(" jne rotuloRepeat"));
}

public void A17(){
	insereLinhaArquivo(String.format(" pop eax"));
	insereLinhaArquivo(String.format(" cmp eax, 1"));
	insereLinhaArquivo(String.format(" jne rotuloFimWhile"));
}

public void A19(){
	// ???????????
}

public void A21() {
	insereLinhaArquivo(String.format("rotuloFimIf:"));
}

public void A23(){
	// ??????
}

public void A25(){
	insereLinhaArquivo(String.format("rotuloElse:"));
}

public void A27(){
	
}

public void insereLinhaArquivo(String trecho){

}