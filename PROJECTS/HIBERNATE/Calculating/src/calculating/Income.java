/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculating;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author hirwa
 */
@Entity
public class Income {
    @Id
    private Double January;
    private Double February;
    private Double March;
    private Double April;
    private Double Total;

    public Income() {
    }

    public Income(Double January, Double February, Double March, Double April, Double Total) {
        this.January = January;
        this.February = February;
        this.March = March;
        this.April = April;
        this.Total = Total;
    }

    public Double getJanuary() {
        return January;
    }

    public void setJanuary(Double January) {
        this.January = January;
    }

    public Double getFebruary() {
        return February;
    }

    public void setFebruary(Double February) {
        this.February = February;
    }

    public Double getMarch() {
        return March;
    }

    public void setMarch(Double March) {
        this.March = March;
    }

    public Double getApril() {
        return April;
    }

    public void setApril(Double April) {
        this.April = April;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    
}
