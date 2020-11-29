package lixo;

import br.com.pi.model.EnderecoModel;
import br.com.pi.util.ICRUD_GENERIC;
import java.util.Iterator;

public class EnderecoBll implements ICRUD_GENERIC {

    ICRUD_GENERIC icrud;

    public EnderecoBll() throws Exception {
        icrud = (ICRUD_GENERIC) new EnderecoDal();
    }

    @Override
    public void add(Object objeto) throws Exception {
        icrud.add(objeto);
    }

    @Override
    public void delete(int n) throws Exception {
        icrud.delete(n);
    }

    @Override
    public void update(Object objeto) throws Exception {
        icrud.update(objeto);
    }

    @Override
    public Iterator getAll() throws Exception {
        return icrud.getAll();
    }

    @Override
    public Object getById(int n) throws Exception {
        try {
            return icrud.getById(n);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Object getByNome(String nome) throws Exception {
        return icrud.getByNome(nome);
    }

    /**
     * Validação do CEP
     *
     * @param objeto
     * @throws Exception
     */
    private void validaEndereco(EnderecoModel objeto) throws Exception {
        String nome = objeto.getEndereco_cep().trim().toLowerCase();
        String invalidos = "abcdefghijklmnopqrstuvxwyz'\"!@#$%¨&*()_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Cep inválido");
            }
        }
    }
    
    public static boolean validarNumeros(String dados){
        return dados.matches("[0-9]*");
    }
    
    public static boolean validarNumerosCEP(String dados){
        return dados.matches("[0-9]*(8,8)");
    }
    
    public static boolean validarNomes(String dados){
        return dados.matches("[a-zA-Z]*");
    }

}
