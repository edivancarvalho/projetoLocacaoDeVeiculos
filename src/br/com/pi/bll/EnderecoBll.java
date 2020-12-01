package br.com.pi.bll;

import br.com.pi.dal.EnderecoDal;
import br.com.pi.model.EnderecoModel;
import interfaces.EnderecoInterface;
import java.util.ArrayList;

public class EnderecoBll implements EnderecoInterface {

    EnderecoInterface end;

    public EnderecoBll() throws Exception {
        end = (EnderecoInterface) new EnderecoDal();
    }

    @Override
    public void adicionarEndereco(EnderecoModel Endereco) {
        end.adicionarEndereco(Endereco);
    }

    @Override
    public void deleteEndereco(int endereco) {
        end.deleteEndereco(endereco);
    }

    @Override
    public void updateEndereco(EnderecoModel endereco) {
        end.updateEndereco(endereco);
    }

    @Override
    public ArrayList<EnderecoModel> getAllEndereco() {
        return end.getAllEndereco();
    }

    @Override
    public EnderecoModel getEnderecoById(int id) {
        return end.getEnderecoById(id);
    }

    @Override
    public EnderecoModel findEnderecoName(String cep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean validarNumeros(String dados){
        return dados.matches("[0-9]*(1,5)");
    }

    public static boolean validarNumerosCEP(String dados){
        return dados.matches("[0-9]*(8)");
    }

    public static boolean validarNomes(String dados){
        return dados.matches("[a-zA-Z]*");
    }
    
    public static boolean validarNumerosInt(String dados){
        return dados.matches("^-[0-9]+?");
    }
}