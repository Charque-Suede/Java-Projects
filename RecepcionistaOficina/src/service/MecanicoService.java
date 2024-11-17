/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.negocio.Mecanico;

/**
 *
 * @author User
 */
public interface MecanicoService extends Remote{
    public List<Mecanico> findAll() throws RemoteException;

    public List<Mecanico> findAll(String id) throws RemoteException;

    public boolean insert(Mecanico mecanico) throws RemoteException;

    public boolean actualizar(Mecanico mecanico) throws RemoteException;

    public boolean apagar(Mecanico mecanico) throws RemoteException;
}
