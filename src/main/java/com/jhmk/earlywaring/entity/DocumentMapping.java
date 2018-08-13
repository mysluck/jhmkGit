package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/8/10 14:38
 */

@Entity
@Table(name = "document_mapping", schema = "jhmk_waring")
public class DocumentMapping {
    private int id;
    private String chinaName;
    private String chinaAllName;
    private String urlPath;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "china_name", nullable = false, length = 100)
    public String getChinaName() {
        return chinaName;
    }

    public void setChinaName(String chinaName) {
        this.chinaName = chinaName;
    }

    @Basic
    @Column(name = "china_all_name", nullable = true, length = 100)
    public String getChinaAllName() {
        return chinaAllName;
    }

    public void setChinaAllName(String chinaAllName) {
        this.chinaAllName = chinaAllName;
    }

    @Basic
    @Column(name = "url_path", nullable = false, length = 100)
    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentMapping that = (DocumentMapping) o;
        return id == that.id &&
                Objects.equals(chinaName, that.chinaName) &&
                Objects.equals(chinaAllName, that.chinaAllName) &&
                Objects.equals(urlPath, that.urlPath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, chinaName, chinaAllName, urlPath);
    }
}
