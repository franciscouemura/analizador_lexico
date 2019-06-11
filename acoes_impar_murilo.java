//VARIAVEIS GLOBAIS
Lexema lexema;
int nivel;
TabelaSimbolos tabelaSimbolos;
ArrayList<int> offsetVariavel = new ArrayList<>();
ArrayList<String> rotulosData = new ArrayList<>();


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
		registro.setOffset(offsetVariavel.get(nivel));
	} else {
		//Erro: identificador já declarado anteriormente
	}
}

public Registro A05(){
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
		return null;
	}
	return registro;
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
			String basePilha = "ebp";
			if(registro.getNivel != nivel){
				insereLinhaArquivo(String.format("	mov ebx, dword [@DSP + %d]", 4*registro.getNivel()));
				basePilha = "ebx";
			}
			insereLinhaArquivo(String.format("	push dword [%s - %d]", basePilha, registro.getOffset()));
			insereLinhaArquivo(String.format("	push dword @INTEGER"));
			insereLinhaArquivo("	call _printf");
			insereLinhaArquivo(String.format("	add esp, 8"));
		} else {
			//Erro: identificador não é uma Variável
		}
	} else {
		//Erro: identificador não foi declarado anteriormente
	}
}

public void A11(Registro ultimoId){
	if(ultimoId != null){
		String basePilha = "ebp";
		if(ultimoId.getNivel()!=nivel){
			insereLinhaArquivo(String.format("	mov ebx, dword [@DSP + %d]", ultimoId.getNivel()*4));
			basePilha = "ebx";
		}
		insereLinhaArquivo(String.format("	pop dword [%s - %d]", basePilha, ultimoId.getOffset()));

		// gerar rotulo _for
	}
}

public void A13(Registro ultimoId){
	if(ultimoId!=null){
		String basePilha = "ebp";
		if(ultimoId.getNivel()!=nivel){
			insereLinhaArquivo(String.format("	mov ebx, dword [@DSP + %d]", ultimoId.getNivel()*4));
			basePilha = "ebx";
		}
		insereLinhaArquivo(String.format(" add dword [%s - %d], 1", basePilha, ultimoId.getOffset()));
		// jmp rotulo _for

		// gerar rotulo _fim_for
	}
}

public void A15(){
	insereLinhaArquivo(String.format(" pop eax"));
	insereLinhaArquivo(String.format(" cmp eax, 1"));
	
	// jne rotulo _repeat
}

public void A17(){
	insereLinhaArquivo(String.format(" pop eax"));
	insereLinhaArquivo(String.format(" cmp eax, 1"));
	
	// jne rotulo _fim_while
}

public void A19(){
	// ???????????
}

public void A21() {
	// gerar rotulo _fim_if
}

public void A23(Registro ultimoId){
	if(/*Verificar se o número de argumentos fornecido em <argumentos> 
		é igual ao número a numeroElementos, do id reconhecido.*/){
		insereLinhaArquivo(String.format("	call %s", ultimoId.getRotulo()));
		insereLinhaArquivo(String.format("	add esp, %d", ultimoId.getNumeroParametros()*4));
	} else {
		// Erro numero argumentos
	}
}

public void A25(){
	// gerar rotulo _else
}

public void A27(){
	String rotuloFalse = ""; // Criar rotulo _false
	String rotuloFim = ""; //Criar rotulo _fim
	insereLinhaArquivo(String.format("	cmp dword [esp + 4], 0"));
	insereLinhaArquivo(String.format("	je %s", rotuloFalse));	// je rotulo _false
	insereLinhaArquivo(String.format("	cmp dword [esp], 0"));
	insereLinhaArquivo(String.format("	je %s", rotuloFalse));	//	je rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp + 4], 1"));
	insereLinhaArquivo(String.format("	jmp %s", rotuloFim));	// jmp rotulo _fim
	insereLinhaArquivo(String.format("%s", rotuloFalse));	// gerar rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp + 4], 0"));
	insereLinhaArquivo(String.format("%s", rotuloFim));		// gerar rotulo _fim
	insereLinhaArquivo(String.format("	add esp, 4"));
}

public void A29(){
	insereLinhaArquivo(String.format("	push 1"));
}

public void A31(){
	String rotuloFim = ""; // criar rotulo _fim
	String rotuloFalse = ""; // criar rotulo _false
	insereLinhaArquivo(String.format("	pop eax"));
	insereLinhaArquivo(String.format("	cmp dword [esp], eax"));
	insereLinhaArquivo(String.format("	jne %s", rotuloFalse));
	insereLinhaArquivo(String.format("	mov dword [esp], 1"));
	insereLinhaArquivo(String.format("%s", rotuloFalse));	// gerar rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp], 0"));
	insereLinhaArquivo(String.format("%s", rotuloFim));	// gerar rotulo _fim
}

public void A32(){
	String rotuloFim = ""; // criar rotulo _fim
	String rotuloFalse = ""; // criar rotulo _false
	insereLinhaArquivo(String.format("	pop eax"));
	insereLinhaArquivo(String.format("	cmp dword [esp], eax"));
	insereLinhaArquivo(String.format("	jle %s", rotuloFalse));
	insereLinhaArquivo(String.format("	mov dword [esp], 1"));
	insereLinhaArquivo(String.format("%s", rotuloFalse));	// gerar rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp], 0"));
	insereLinhaArquivo(String.format("%s", rotuloFim));	// gerar rotulo _fim
}

public void A33(){
	String rotuloFim = ""; // criar rotulo _fim
	String rotuloFalse = ""; // criar rotulo _false
	insereLinhaArquivo(String.format("	pop eax"));
	insereLinhaArquivo(String.format("	cmp dword [esp], eax"));
	insereLinhaArquivo(String.format("	jl %s", rotuloFalse));
	insereLinhaArquivo(String.format("	mov dword [esp], 1"));
	insereLinhaArquivo(String.format("%s", rotuloFalse));	// gerar rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp], 0"));
	insereLinhaArquivo(String.format("%s", rotuloFim));	// gerar rotulo _fim
}

public void A34(){
	String rotuloFim = ""; // criar rotulo _fim
	String rotuloFalse = ""; // criar rotulo _false
	insereLinhaArquivo(String.format("	pop eax"));
	insereLinhaArquivo(String.format("	cmp dword [esp], eax"));
	insereLinhaArquivo(String.format("	jge %s", rotuloFalse));
	insereLinhaArquivo(String.format("	mov dword [esp], 1"));
	insereLinhaArquivo(String.format("%s", rotuloFalse));	// gerar rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp], 0"));
	insereLinhaArquivo(String.format("%s", rotuloFim));	// gerar rotulo _fim
}

public void A35(){
	String rotuloFim = ""; // criar rotulo _fim
	String rotuloFalse = ""; // criar rotulo _false
	insereLinhaArquivo(String.format("	pop eax"));
	insereLinhaArquivo(String.format("	cmp dword [esp], eax"));
	insereLinhaArquivo(String.format("	jg %s", rotuloFalse));
	insereLinhaArquivo(String.format("	mov dword [esp], 1"));
	insereLinhaArquivo(String.format("%s", rotuloFalse));	// gerar rotulo _false
	insereLinhaArquivo(String.format("	mov dword [esp], 0"));
	insereLinhaArquivo(String.format("%s", rotuloFim));	// gerar rotulo _fim
}

public void A37(){
	insereLinhaArquivo("	pop eax");
	insereLinhaArquivo("	add dword [esp], eax");
}

public void A39(){
	insereLinhaArquivo("	pop eax");
	insereLinhaArquivo("	imul eax, dword [esp]");
	insereLinhaArquivo("	mov dword [esp], eax");
}

public void A41(){
	insereLinhaArquivo(String.format("	push %d", Integer.parseInt(lexema.getTexto())));
}

public void A45(){
	insereLinhaArquivo("section .data");
	insereLinhaArquivo(String.format("@DSP times %d db 0", (nivel+1)*4));
}

public void A47(Registro ultimoId){
	ultimoId.setNumeroParametros(tabelaSimbolos.getNumeroParametros());
}

public void A49(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(tabelaSimbolos.temRegistroTodasTabelas(registro)){
		registro = tabelaSimbolos.getEsseRegistro(registro);
		String categoria = registro.getCategoria;
		if(categoria==null || (!categoria.equals("Variavel") && !categoria.equals("Parametro"))){
			//Erro id nao e variavel/ parametro
		}
	} else {
		//Erro variavel ainda nao declarada
	}
}


public void A55(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(tabelaSimbolos.temRegistroTodasTabelas(registro)){
		registro = getEsseRegistro(registro);
		String categoria = registro.getCategoria();
		if(categoria!=null && (categoria.equals("Variavel") || categoria.equals("Parametro"))){
			String basePilha = "ebp";
			if(registro.getNivel()!=nivel){
				insereLinhaArquivo(String.format("	mov ebx, dword [@DSP + %d]", registro.getNivel()*4));
				basePilha = "ebx";
			}
			insereLinhaArquivo(String.format("	push dword [%s - %d]", basePilha, registro.getOffset()));
		}
	} else {
		//Erro variavel nao declarada
	}
}

public Registro A57(){
	Registro registro = new Registro();
	registro.setNome(lexema.getTexto());
	if(tabelaSimbolos.temRegistroTodasTabelas(registro)){
		registro = tabelaSimbolos.getEsseRegistro();
		if(!registro.getCategoria().equals("Variavel") && !registro.getCategoria().equals("Parametro") && !registro.getCategoria().equals("Funcao")){
			// Erro identificador não é uma variavel
			return null;
		}
	} else {
		//Erro variavel nao declarada
		return null;
	}
	return registro;
}

public void A59(boolean wln){
	String rotuloString = "";	//criar rotulo _string
	String endString = ", 0";
	if(wln)
		endString = ", 10, 0";
	rotulosData.add(String.format("%s db %s %s", rotuloString, lexema.getTexto(), endString));
	insereLinhaArquivo(String.format("	push %s", rotuloString));
	insereLinhaArquivo("	call _printf");
	insereLinhaArquivo("	add esp, 4");
}

public void insereLinhaArquivo(String linha){

}