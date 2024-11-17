package model.negocio;

import java.io.Serializable;
import java.util.Date;

public class Ficha implements Serializable{

    String id, idCliente, idMecanico, estado, tipoServico;
    Date dataEmissao, dataEntrega;

    public Ficha(String id, String idCliente, String idMecanico, String estado, String tipoServico, Date dataEmissao, Date dataEntrega) {
        this.id = id;
        this.idCliente = idCliente;
        this.idMecanico = idMecanico;
        this.estado = estado;
        this.dataEmissao = dataEmissao;
        this.dataEntrega = dataEntrega;
        this.tipoServico = tipoServico;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Ficha() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(String idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

}
