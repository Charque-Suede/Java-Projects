/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.negocio.Peca;

/**
 *
 * @author User
 */
public interface PecaService extends Remote{
    public boolean insertPeca(Peca peca) throws RemoteException;
}
