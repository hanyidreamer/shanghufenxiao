package com.payease.scfordermis.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @Author : zhangwen
 * @Data : 2018/1/11
 * @Description :
 */
@Entity
@Table(name = "t_note")
public class TNoteEntity {
    private long id;
    private String fNote;
    private Date fCreatetime;
    private long fOperate;
    private long fCompanyId;
    private String fOperatingType;
    private long fTransportOrderId;
    private String fType;
    private String fOperator;
    private String fPartyType;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "f_note", nullable = false, length = 500)
    public String getfNote() {
        return fNote;
    }

    public void setfNote(String fNote) {
        this.fNote = fNote;
    }

    @Basic
    @Column(name = "f_createtime", nullable = false)
    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    @Basic
    @Column(name = "f_operate", nullable = false)
    public long getfOperate() {
        return fOperate;
    }

    public void setfOperate(long fOperate) {
        this.fOperate = fOperate;
    }

    @Basic
    @Column(name = "f_company_id", nullable = false)
    public long getfCompanyId() {
        return fCompanyId;
    }

    public void setfCompanyId(long fCompanyId) {
        this.fCompanyId = fCompanyId;
    }

    @Basic
    @Column(name = "f_operating_type", nullable = false, length = 20)
    public String getfOperatingType() {
        return fOperatingType;
    }

    public void setfOperatingType(String fOperatingType) {
        this.fOperatingType = fOperatingType;
    }

    @Basic
    @Column(name = "f_transport_order_id", nullable = false)
    public long getfTransportOrderId() {
        return fTransportOrderId;
    }

    public void setfTransportOrderId(long fTransportOrderId) {
        this.fTransportOrderId = fTransportOrderId;
    }

    @Basic
    @Column(name = "f_type", nullable = false, length = 45)
    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    @Basic
    @Column(name = "f_operator", nullable = false, length = 45)
    public String getfOperator() {
        return fOperator;
    }

    public void setfOperator(String fOperator) {
        this.fOperator = fOperator;
    }

    @Basic
    @Column(name = "f_party_type", nullable = false, length = 20)
    public String getfPartyType() {
        return fPartyType;
    }

    public void setfPartyType(String fPartyType) {
        this.fPartyType = fPartyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TNoteEntity that = (TNoteEntity) o;
        return id == that.id &&
                fOperate == that.fOperate &&
                fCompanyId == that.fCompanyId &&
                fTransportOrderId == that.fTransportOrderId &&
                Objects.equals(fNote, that.fNote) &&
                Objects.equals(fCreatetime, that.fCreatetime) &&
                Objects.equals(fOperatingType, that.fOperatingType) &&
                Objects.equals(fType, that.fType) &&
                Objects.equals(fOperator, that.fOperator) &&
                Objects.equals(fPartyType, that.fPartyType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fNote, fCreatetime, fOperate, fCompanyId, fOperatingType, fTransportOrderId, fType, fOperator, fPartyType);
    }
}
