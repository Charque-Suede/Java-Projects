package modeloTabelas;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.negocio.Carro;
import model.negocio.Cliente;
import model.negocio.Ficha;
import model.negocio.Mecanico;
import service.FichaService;

import view.Grupo;

public class ModeloFecharFicha extends AbstractTableModel {

    private List<Ficha> lista;
    static FichaService fich_serv;
    private String[] colunas = new String[]{"NOME DO CLIENTE", "MECANICO RESPONSALVEL", "MATRICULA", "ESTADO"};

    public ModeloFecharFicha() throws RemoteException {
        
        lista = fich_serv.findAll();
    }

    public ModeloFecharFicha(List reg) {
        inicializarObjs();
        lista = reg;
    }

    public void actualizarLista(List adotantes) {
        lista = adotantes;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    public String getColumnName(int indice) {
        return colunas[indice];
    }

    @Override
    public Object getValueAt(int linha, int coluna) {

        Ficha ficha = lista.get(linha);
        Cliente cliente = null;
        try {
            cliente = fich_serv.getCliente(ficha.getIdCliente());
        } catch (RemoteException ex) {
            Logger.getLogger(ModeloFecharFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        Carro carro = null;
        try {
            carro = fich_serv.getCarro(ficha.getIdCliente());
        } catch (RemoteException ex) {
            Logger.getLogger(ModeloFecharFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        Mecanico mecanico = null;
        try {
            mecanico = fich_serv.getMecanico(ficha.getIdMecanico());
        } catch (RemoteException ex) {
            Logger.getLogger(ModeloFecharFicha.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (coluna) {
            case 0:
                return cliente.getNome();
            case 1:
                return mecanico.getNome();
            case 2:
                return carro.getMatricula();
            case 3:
                return ficha.getEstado();
            default:
                return "";
        }
    }

    public void actualizar(Ficha fun) {
        lista.add(fun);
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
    }

    public Ficha retornarRegisReg(int indice) {
        return lista.get(indice);
    }

    public void inicializarObjs() {
        try {
            Registry regist = LocateRegistry.getRegistry(Grupo.ip, 6001);
            fich_serv = (FichaService) regist.lookup("Ficha");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}