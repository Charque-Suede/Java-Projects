package modeloTabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.negocio.Mecanico;

public class TabelaMecanico extends AbstractTableModel {

    private List <Mecanico> lista;
    private String[] colunas = new String[]{"NOME","CELULAR"};

    public TabelaMecanico() {
    }

    public TabelaMecanico(List adotantes) {
        lista = adotantes;
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
        Mecanico fun =lista.get(linha);

        switch (coluna) {
            case 0:
                return fun.getNome();
            case 1:
                return fun.getCelular();
            default:
                return "";
        }
    }
      
    public void actualizar(Mecanico fun){
        lista.add(fun);
        fireTableRowsInserted(lista.size()-1, lista.size()-1);
    }
    
    public Mecanico retornarAdotante(int indice){
        return lista.get(indice);
    }
     
}
