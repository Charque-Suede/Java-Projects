package recibos;

import java.util.Date;
import java.util.List;


public class ParametrosRecibo {
    private String nomeCliente, telefone, marcaCarro, modeloCarro, mecanico, servico;
    private Date dataEntrega, dataSaida;
    private float total;
    private List<CamposRecibo> list;

    public ParametrosRecibo() {
    }

    public ParametrosRecibo(String nomeCliente, String telefone, String marcaCarro, String modeloCarro, String mecanico, String servico, Date dataEntrega, Date dataSaida, float total, List<CamposRecibo> list) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.marcaCarro = marcaCarro;
        this.modeloCarro = modeloCarro;
        this.mecanico = mecanico;
        this.servico = servico;
        this.dataEntrega = dataEntrega;
        this.dataSaida = dataSaida;
        this.total = total;
        this.list = list;
    }

    

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<CamposRecibo> getList() {
        return list;
    }

    public void setList(List<CamposRecibo> list) {
        this.list = list;
    }
    
    
}
