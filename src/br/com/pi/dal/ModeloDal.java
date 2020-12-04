/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.dal;

import br.com.pi.bll.MarcaBll;
import br.com.pi.model.ModeloModel;
import br.com.pi.util.Conexao;
import br.com.pi.interfaces.ICRUD_GENERIC;
import br.com.pi.model.MarcaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Anthonny Max
 */
public class ModeloDal implements ICRUD_GENERIC<ModeloModel> {

    private Connection conexao;
    ModeloModel modeloModel = new ModeloModel();
    MarcaBll marcaBll = new MarcaBll();
    
    public ModeloDal() throws Exception {
        this.conexao = Conexao.getInstance().getConnection();
    }


    @Override
    public void add(ModeloModel objeto) throws Exception {
        String sql = "INSERT INTO modelo(modelo_descricao, modelo_marca)" +
                "VALUES (?, ?)";

        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setObject(1, objeto.getModelo_descricao());
        ps.setInt(2, objeto.getModelo_marca().getMarca_idem());
        ps.executeUpdate();

    }

    @Override
    public void delete(int n) throws Exception {
        String sql = "DELETE FROM modelo WHERE modelo_idem =?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setObject(1,n);
        ps.executeUpdate();

    }

    @Override
    public void update(ModeloModel objeto) throws Exception {
        String sql ="UPDATE modelo SET modelo_descricao = ?" +
                " WHERE modelo_idem =?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setObject(1, objeto.getModelo_descricao());
        ps.setInt(2, objeto.getModelo_marca().getMarca_idem());
        ps.setObject(3, objeto.getModelo_idem());
        ps.executeUpdate();

    }

    @Override
    public Iterator getAll() throws Exception {
        String sql = "SELECT * FROM modelo";
        List<ModeloModel> pessoaModelList = new ArrayList<>();

        Statement st = conexao.createStatement();;
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            modeloModel = new ModeloModel();
            modeloModel.setModelo_idem(rs.getInt("modelo_idem"));
            modeloModel.setModelo_descricao(rs.getString("modelo_descricao"));
            modeloModel.setModelo_marca((MarcaModel) marcaBll.getById(rs.getInt("modelo_marca")));

            pessoaModelList.add(modeloModel);
        }
        return pessoaModelList.iterator();
    }

    @Override
    public ModeloModel getById(int n) throws Exception {
        String sql = "SELECT * FROM modelo WHERE modelo_idem = ?, modelo_marca = ?";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setObject(1,n);
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()){
            modeloModel = new ModeloModel();
            modeloModel.setModelo_idem(rs.getInt("modelo_idem"));
            modeloModel.setModelo_descricao(rs.getString("modelo_descricao"));
            modeloModel.setModelo_marca((MarcaModel) marcaBll.getById(rs.getInt("modelo_marca")));
        }

        return modeloModel;
    }

    @Override
    public ModeloModel getByNome(String nome) throws Exception {
        String sql = "SELECT * FROM modelo WHERE modelo_descricao = ?, modelo_marca = ?";

        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setObject(1,nome);
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()){
            modeloModel = new ModeloModel();
            modeloModel.setModelo_idem(rs.getInt("modelo_idem"));
            modeloModel.setModelo_descricao(rs.getString("modelo_descricao"));
            modeloModel.setModelo_marca((MarcaModel) marcaBll.getById(rs.getInt("modelo_marca")));
        }

        return modeloModel;
    }

    @Override
    public int addReturn(ModeloModel objeto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
