/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.cerva.toiss.bean;

/**
 *
 * @author Daniel
 */
public class Valor {
    
    private int cod;
    private String valor;
    private String data;
    private int codCerv;
    private int codCatCerveja;
    private String descricaoCerveja;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCodCerv() {
        return codCerv;
    }

    public void setCodCerv(int codCerv) {
        this.codCerv = codCerv;
    }

    public int getCodCatCerveja() {
        return codCatCerveja;
    }

    public void setCodCatCerveja(int codCatCerveja) {
        this.codCatCerveja = codCatCerveja;
    }

    public String getDescricaoCerveja() {
        return descricaoCerveja;
    }

    public void setDescricaoCerveja(String descricaoCerveja) {
        this.descricaoCerveja = descricaoCerveja;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Valor other = (Valor) obj;
        if (this.cod != other.cod) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.cod;
        return hash;
    }

    @Override
    public String toString() {
        return data + " - " + descricaoCerveja + " - R$" + String.valueOf(valor);
    }
    
}
