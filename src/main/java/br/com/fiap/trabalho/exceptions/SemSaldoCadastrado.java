package br.com.fiap.trabalho.exceptions;

public class SemSaldoCadastrado extends Exception{
	private static final long serialVersionUID = 4704866414103465205L;

	private String msg;
    public SemSaldoCadastrado(String msg){
        super(msg);
        this.msg = msg;
      }
      public String getMessage(){
        return msg;
      }
}
