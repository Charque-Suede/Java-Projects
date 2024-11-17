package recibos;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ImprimirRecibo {
    
    private static ImprimirRecibo instance;
    private JasperReport recibo;
    public static ImprimirRecibo getInstance(){
        if (instance == null)
            instance = new ImprimirRecibo();
        
        return instance;
    }
    
    private ImprimirRecibo(){
        
    }
    
    public void compilarRecibo() throws JRException{
        recibo = JasperCompileManager.compileReport(getClass().getResourceAsStream("recibo.jrxml"));
    }
    
    public void imprimirRecibo(ParametrosRecibo dados)throws JRException{
        Map parametros = new HashMap();
        parametros.put("nomeCliente", dados.getNomeCliente());
        parametros.put("telefone", dados.getTelefone());
        parametros.put("marcaCarro", dados.getMarcaCarro());
        parametros.put("modeloCarro", dados.getModeloCarro());
        parametros.put("mecanico", dados.getMecanico());
        parametros.put("servico", dados.getServico());
        parametros.put("dataEntrada", dados.getDataEntrega());
        parametros.put("dataSaida", dados.getDataSaida());
        parametros.put("total", dados.getTotal());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados.getList());
        compilarRecibo();
        JasperPrint print = JasperFillManager.fillReport(recibo, parametros, dataSource);
        view(print);
    }
    
    private void view(JasperPrint print)throws JRException{
        JasperViewer.viewReport(print, false);
    }
}