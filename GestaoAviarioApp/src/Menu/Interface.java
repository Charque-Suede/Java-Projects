/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 *
 * @author Charque-Suede
 */
public interface Interface {
    
    public void criarCliente();
    public void criarFuncionario();
    public void criarProduto();
    public void criarVenda(String r);
    
    public void actualizarCliente();
    public void actualizarFuncionario();
    public void actualizarProduto();
    
    public boolean removerCliente();
    public void removerFuncionario();
    public void removerProduto();
    
    public void todoRelatorioVendas();    
    
}
