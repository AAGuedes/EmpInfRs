/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.emprinfrs_arteagaairam.dao;

import es.cifpcm.emprinfrs_arteagaairam.model.Disco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Airam
 */
public class DiscoDaoImpl implements DiscoDao {

    DaoFactory daoFactory;
    ArrayList<Disco> listaTiendas = new ArrayList<>();

    // RECIBE DESDE DAOFACTORY
    // HACE DAOFACTORY ES ACCESIBLE DESDE TIENDADAOIMPL
    public DiscoDaoImpl(DaoFactory aThis) {
        this.daoFactory = aThis;
    }

    @Override
    public ArrayList<Disco> getDiscos() {
        try (Connection connection = daoFactory.getConnection()) {
            
            // CONECTAMOS Y RECUPERAMOS TODOS LOS DISCOS
            String query = "SELECT * FROM disco;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // RECORREMOS LOS DATOS RECUPERADOS
            while (resultSet.next()) {
                
                // ASIGNAMOS A CADA DISCO SU DATOS
                Disco disco = new Disco();
                disco.setIdDisco(resultSet.getInt("idDisco"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setAgno(resultSet.getFloat("agno"));
                
                // RECUPERA EL INTERPRETE ID DEL INTERPRETE Y
                // EXTRAE SU NOMBRE EN LA TABLA INTERPRETE
                query = "SELECT interprete FROM interprete WHERE idInterprete = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, resultSet.getInt("IdInterprete"));
                ResultSet newResultSet = preparedStatement.executeQuery();
                
                // ASIGNA EL INTERPRETE
                while (newResultSet.next()) {
                    disco.setInterprete(newResultSet.getString("interprete"));
                }
                
                listaTiendas.add(disco);
            }
            return listaTiendas;
        } catch (Exception e) {
            Logger.getLogger(DiscoDaoImpl.class.getName()).log(Level.SEVERE, "Error en getDiscos: {0}", e);
        }
        return null;
    }

}
