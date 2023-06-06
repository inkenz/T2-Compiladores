package br.ufscar.dc.compiladores.t2;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class LinguagemLa 
{
    public static void main( String[] args )
    {
        try (PrintWriter pw = new PrintWriter(new FileWriter(args[1]))){
            
            //Recebendo argumentos com arquivos de entrada e saída
            CharStream cs = CharStreams.fromFileName(args[0]);

            LA lex = new LALexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
	    LA parser = new AlgumaParser(tokens);
	    
	    MyCustomErrorListener mcel = new MyCustomErrorListener(pw);
            parser.addErrorListener(mcel);
            Token t = null;
            
            parcer.programa();
            //Montagem do vocabulário
            while ((t = lex.nextToken()).getType() != Token.EOF) {
                String nomeToken = LA.VOCABULARY.getDisplayName(t.getType());

                if(nomeToken.equals("ERRO")) {
                    pw.println("Linha "+t.getLine()+": "+t.getText()+" - simbolo nao identificado");
                    break;
                } else if(nomeToken.equals("CADEIA_NAO_FECHADA")) {
                    pw.println("Linha "+t.getLine()+": cadeia literal nao fechada");
                    break;
                } else if(nomeToken.equals("COMENTARIO_NAO_FECHADO")) {
                    pw.println("Linha "+t.getLine()+": comentario nao fechado");
                    break;
                } else {
                    pw.println("<'" + t.getText() + "'," + nomeToken + ">");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
