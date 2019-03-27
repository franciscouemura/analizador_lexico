int linha = 0;
int coluna = 0;
int index = 0;
int lastLine = 0;
String texto;
String errorDescription;
Lexema lexema;
Boolean erro;
private ArrayList<String> palavrasReservadas = new ArrayList<>();

//<programa> ::= program id <corpo> •

public void programa(){
	if(comparaClasseLexema("cRes","program")){
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cId",lexema.getTexto())){
			lexema = analisadorLexico(texto);
			corpo();
			if(comparaClasseLexema("cPto",".")){
				lexema = analisadorLexico(texto);
			} else {
				
				imprimeErro("cPto", ".");
			}
		} else {
			imprimeErro("cId",lexema.getTexto());
		}
	} else {
		imprimeErro("cRes", "program");
	}
}

//<corpo> ::= <declara> <rotina> begin <sentencas> end

public void corpo(){
	declara();
	rotina();
	if(comparaClasseLexema("cRes","begin")){
		lexema = analisadorLexico(texto);
		sentencas();
		if(comparaClasseLexema("cRes","end")){
			lexema = analisadorLexico(texto);
		} else {
			imprimeErro("cRes","end")
		}
	} else {
		imprimeErro("cRes", "begin");
	}
}

//<declara> ::= var <dvar> <declara> | 

public void declara(){
	if(comparaClasseLexema("cRes","var")){
		lexema = analisadorLexico(texto);
		dvar();
		declara();
	}
}

//<dvar> ::= <variaveis> : <dvarL> 

public void dvar(){
	variaveis();
	if(comparaClasseLexema("cDPto",":")){
		lexema = analisadorLexico(texto);
		dvarL();
	} else {
		imprimeErro("cDPto", ":")
	}
}

//<dvarL> ::= <tipo_var> <dvarLL>

public void dvarL(){
	tipo_var();
	dvarLL();
}

//<dvarLL> ::= ; <dvar> | 

public void dvarLL(){
	if(comparaClasseLexema("cPVir",";"))
		dvar();
}

//<tipo_var> ::= integer | real

public void tipo_var(){
	if(comparaClasseLexema("cRes","integer") || comparaClasseLexema("cRes","real")){
		lexema = analisadorLexico(texto);
	} else {
		imprimeErro("cNum","Numero (inteiro ou real)");
	}
}

//<variaveis> ::= id <variaveisL>

public void variaveis(){
	if(comparaClasseLexema("cId",lexema.getTexto())){
		lexema = analisadorLexico(texto);
		variaveisL();
	} else {
		imprimeErro("cId", "identificador");
	}
}

//<variaveisL> ::= , id <variaveisL> | 

public void variaveisL(){
	if(comparaClasseLexema("cVir",",")){
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cId",lexema.getTexto())){
			lexema = analisadorLexico(texto);
			variaveisL();
		} else {
			imprimeErro("cId", "identificador");
		}
	}
}

//<rotina> ::= procedure <procedimento> | function <funcao> | 

public void rotina(){
	if(comparaClasseLexema("cRes","procedure")){
		lexema = analisadorLexico(texto);
		procedimento();
	} else if(comparaClasseLexema("cRes", "function")){
		lexema = analisadorLexico(texto);
		funcao();
	}
}

//<procedimento> ::= id <parametros> ; <corpo> ; <rotina>

public void procedimento(){
	if(comparaClasseLexema("cId",lexema.getTexto())){
		lexema = analisadorLexico(texto);
		parametros();
		if(comparaClasseLexema("cPVir",";")){
			lexema = analisadorLexico(texto);
			corpo();
			if(comparaClasseLexema("cPVir",";")){
				lexema = analisadorLexico(texto);
				rotina();
			} else {
				imprimeErro("cPVir", ";");
			}
		} else {
			imprimeErro("cPVir", ";");
		}
	} else {
		imprimeErro("cId", "identificador");
	}
}
//<funcao> ::= id <parametros> : <tipo_funcao> ; <corpo> ; <rotina>

public void funcao(){
	if(comparaClasseLexema("cId",lexema.getTexto())){
		lexema = analisadorLexico(texto);
		parametros();
		if(comparaClasseLexema("cDPto",":")){
			lexema = analisadorLexico(texto);
			tipo_funcao();
			if(comparaClasseLexema("cPVir",";")){
				lexema = analisadorLexico(texto);
				corpo();
				if(comparaClasseLexema("cPVir",";")){
					lexema = analisadorLexico(texto);
					rotina();
				} else {
					imprimeErro("cPVir", ";");
				}
			} else {
				imprimeErro("cPVir", ";");
			}
		} else {
			imprimeErro("cPVir", ";");
		}
	} else {
		imprimeErro("cId", "identificador");
	}
}

//<parametros> ::= ( <lista_parametros> ) | 

public void parametros(){
	if(comparaClasseLexema("cLPar","(")){
		lexema = analisadorLexico(texto);
		lista_parametros();
		if(!comparaClasseLexema("cRPar", ")")){
			lexema = analisadorLexico(texto);
		} else {
			imprimeErro("cRPar",")");
		}
	}
}

//<lista_parametros> ::= <lista_id> : <tipo_var> <lista_paremetrosL>

public void lista_parametros(){
	lista_id();
	if(comparaClasseLexema("cDPto",":")){
		lexema = analisadorLexico(texto);
		tipo_var();
		lista_parametrosL();
	} else {
		imprimeErro("cDPto", ":");
	}
}

//<lista_parametrosL> ::=  ; <lista_parametros> | 

public void lista_parametrosL(){
	if(comparaClasseLexema("cPVir", ";")){
		lexema = analisadorLexico(texto);
		lista_parametros();
	}
}

//<lista_id> ::= id <listaidL>

public void lista_id(){
	if(comparaClasseLexema("cId", lexema.getTexto())){
		lexema = analisadorLexico(texto);
		lista_idL();
	} else {
		imprimeErro("cId","identificador");
	}
}

//<lista_idL> = , id <lista_idL> | 

public void lista_idL(){
	if(comparaClasseLexema("cVir",",")){
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cId", lexema.getTexto())){
			lexema = analisadorLexico(texto);
			lista_idL();
		} else {
			imprimeErro("cId", "identificador");
		}
	}
}

//<tipo_funcao> ::= integer | real

public void tipo_funcao(){
	if(comparaClasseLexema("cRes", lexema.getTexto())){
		lexema = analisadorLexico(texto);
	} else {
		imprimeErro("cRes", "real ou integer");
	}
}

//<comando> ::= 		read ( <var_read> ) |

public void comando(){
	if(comparaClasseLexema("cRes", "read")){
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cLPar", "(")){
			lexema = analisadorLexico(texto);
			var_read();
			if(comparaClasseLexema("cRPar", ")")){
				lexema = analisadorLexico(texto);
			} else {
				imprimeErro("cRPar", ")");
			}
		} else {
			imprimeErro("cLPar", "(")
		}
	} else if(comparaClasseLexema("cRes", "write")) {	//					 	write ( <var_write> ) |
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cLPar", "(")){
			lexema = analisadorLexico(texto);
			var_write();
			if(comparaClasseLexema("cRPar", ")")){
				lexema = analisadorLexico(texto);
			} else {
				imprimeErro("cRPar", ")");
			}
		} else {
			imprimeErro("cLPar", "(")
		}
	} else if(comparaClasseLexema("cRes", "for")) {	//				 	 	for id := <expressao> to <expressao> do begin <sentencas> end |
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cId", lexema.getTexto())){
			lexema = analisadorLexico(texto);
			if(comparaClasseLexema("cAtr",":=")){
				lexema = analisadorLexico(texto);
				expressao();
				if(comparaClasseLexema("cRes", "to")){
					lexema = analisadorLexico(texto);
					expressao();
					if(comparaClasseLexema("cRes", "do")){
						lexema = analisadorLexico(texto);
						if(comparaClasseLexema("cRes", "begin")){
							lexema = analisadorLexico(texto);
							sentencas();
							if(comparaClasseLexema("cRes", "end")){
								lexema = analisadorLexico(texto);
							} else {
								imprimeErro("cRes", "end");
							}
						} else {
							imprimeErro("cRes", "begin");
						}
					} else {
						imprimeErro("cRes", "do");
					}
				} else {
					imprimeErro("cRes", "to");
				}
			} else {
				imprimeErro("cAtr", ":=");
			}
		} else {
			imprimeErro("cId","identificador");
		}
	} else if(comparaClasseLexema("cRes", "repeat")) {	//				 	 	repeat <sentencas> until ( <condicao> ) |
		lexema = analisadorLexico(texto);
		sentencas();
		if(comparaClasseLexema("cRes", "until")){
			lexema = analisadorLexico(texto);
			if(comparaClasseLexema("cLPar", "(")){
				lexema = analisadorLexico(texto);
				condicao();
				if(comparaClasseLexema("cRPar", ")")){
					lexema = analisadorLexico(texto);
				} else {
					imprimeErro("cRPar", ")");
				}
			} else {
				imprimeErro("cLPar", "(");
			}
		} else {
			imprimeErro("cRes", "until");
		}
	} else if(comparaClasseLexema("cRes", "while")){	//				while ( <condicao> ) do begin <sentencas> end |
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cLPar", "(")){
			lexema = analisadorLexico(texto);
			condicao();
			if(comparaClasseLexema("cRPar", ")")){
				lexema = analisadorLexico(texto);
				if(comparaClasseLexema("cRes", "do")){
					lexema = analisadorLexico(texto);
					if(comparaClasseLexema("cRes", "begin")){
						lexema = analisadorLexico(texto);
						sentencas();
						if(comparaClasseLexema("cRes", "end")){
							lexema = analisadorLexico(texto);
						} else {
							imprimeErro("cRes", "end");
						}
					} else {
						imprimeErro("cRes", "begin");
					}
				} else {
					imprimeErro("cRes", "do");
				}
			} else {
				imprimeErro("cRPar", ")");
			}
		} else {
			imprimeErro("cLPar", "(");
		}
	} else if(comparaClasseLexema("cRes", "if")){	//				if ( <condicao> ) then begin <sentencas> end <pfalsa> |
		lexema = analisadorLexico(texto);
		if(comparaClasseLexema("cLPar", "(")){
			lexema = analisadorLexico(texto);
			condicao();
			if(comparaClasseLexema("cRPar", ")")){
				lexema = analisadorLexico(texto);
				if(comparaClasseLexema("cRes", "then")){
					lexema = analisadorLexico(texto);
					if(comparaClasseLexema("cRes", "begin")){
						lexema = analisadorLexico(texto);
						sentencas();
						if(comparaClasseLexema("cRes", "end")){
							lexema = analisadorLexico(texto);
							pfalsa();
						} else {
							imprimeErro("cRes", "end");
						}
					} else {
						imprimeErro("cRes", "begin");
					}
				} else {
					imprimeErro("cRes", "then");
				}
			} else {
				imprimeErro("cRPar", ")");
			}
		} else {
			imprimeErro("cLPar", "(");
		}
	} else if(comparaClasseLexema("cId", lexema.getTexto())){	//	id <idL>
		lexema = analisadorLexico(texto);
		idL();
	} else {
		imprimeErro("com",lexema.getTexto());
	}
}

//	<idL> ::= <argumentos> | := <expressao>

public void idL(){
	if(comparaClasseLexema("cAtr", ":=")){
		lexema = analisadorLexico(texto);
		expressao();
	} else {
		argumentos();
	}
}

public boolean comparaClasseLexema(String class, String texto){
	boolean v = false;
	if(lexema.getClasse().equals(class) && lexema.getTexto().equals(texto))
		v = true;
	return v;
}

public void imprimeErro(String class, String description){
    erro = true;
    System.out.println("Na linha %d:\n", lexema.getLinha()+1);
    if(class.equals("com")){
    	errorDescription = String.format("Comando '%s' não reconhecido", description);
    } else 	if(class.matches("cRes\\w*")){
        errorDescription = String.format("Palavra reservada %s esperado.\n", description);
    } else {
        errorDescription = String.format("'%s' esperado.\n", description);
    }
    }
    System.out.println(errorDescription);
}