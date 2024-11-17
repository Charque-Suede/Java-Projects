/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.negocio.Mecanico;

/**
 *
 * @author User
 */
public interface LoginService extends Remote{
    public boolean find(String nomeTabela, String usuario, String senha) throws RemoteException;
    public Mecanico findMecanico(String usuario, String senha) throws RemoteException;
}
