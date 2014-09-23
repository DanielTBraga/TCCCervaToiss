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
public class Cerveja {
    private int cod;
    private String descricao;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
     public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cerveja other = (Cerveja) obj;
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
        return descricao;
    }
    
}
