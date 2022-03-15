package com.pkstaz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="ctpm_act_eco")
public class ActividadEconomica extends PanacheEntity{
    @Column(name="act_codigo")
    public String codigo;

    @Column(name="act_rut")
    public Integer rut;

    @Column(name="act_dv")
    public String dv;

    @Column(name="act_descripcion")
    public String descripcion;


    public ActividadEconomica() {
    }

    public ActividadEconomica(String codigo, String descripcion, Integer rut, String dv) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.rut = rut;
        this.dv = dv;
    }
}
