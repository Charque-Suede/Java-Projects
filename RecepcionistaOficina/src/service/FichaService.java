/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import model.negocio.Carro;
import model.negocio.Cliente;
import model.negocio.Ficha;
import model.negocio.Mecanico;
import model.negocio.Peca;

/**
 *
 * @author User
 */
public interface FichaService extends Remote {

    public boolean insert(String id, Date dataEmissao, Date dataEntrega,
            String estado, String tipoServico, Cliente cliente, Carro carro, String idMecanico) throws RemoteException;

    public boolean actualizar(String id, String tipoServico, Cliente cliente, Carro carro, String idMecanico) throws RemoteException;

    public boolean insertCliente(Cliente cliente) throws RemoteException;
    public boolean actualizarCliente(Cliente cliente) throws RemoteException;
    public boolean insertCarro(Carro carro) throws RemoteException;
    public boolean actualizarCarro(Carro carro) throws RemoteException;
    public Cliente getCliente(String id) throws RemoteException;
    public Mecanico getMecanico(String id) throws RemoteException;
    public List<Ficha> findAll() throws RemoteException;
    public List<Ficha> findAllByMecanico(String idMecanico)throws RemoteException;
    public Carro getCarro(String matricula) throws RemoteException;
    public List<Peca> pecasByFichID(Ficha ficha) throws RemoteException;
    public boolean actualizarEstado(Ficha ficha) throws RemoteException;
    public boolean actualizarDataEntrega(Ficha ficha) throws RemoteException;
}
