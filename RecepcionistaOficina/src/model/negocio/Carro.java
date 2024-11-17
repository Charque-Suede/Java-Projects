package model.negocio;

import java.io.Serializable;

public class Carro implements Serializable{

    String matricula, marca, modelo, cor, tipoCombustivel, idCliente;
    int nrMotor;
    float quilometragem;

    public Carro(String matricula, String marca, String modelo, String cor, String tipoCombustivel, String idCliente, int nrMotor, float quilometragem) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.tipoCombustivel = tipoCombustivel;
        this.idCliente = idCliente;
        this.nrMotor = nrMotor;
        this.quilometragem = quilometragem;
    }

    public Carro() {
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getNrMotor() {
        return nrMotor;
    }

    public void setNrMotor(int nrMotor) {
        this.nrMotor = nrMotor;
    }

    public float getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(float quilometragem) {
        this.quilometragem = quilometragem;
    }


}
