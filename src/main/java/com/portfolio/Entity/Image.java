package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "type", columnDefinition = "VARCHAR(25)")
    private String type;

    @Column(name = "data_img", columnDefinition = "MEDIUMTEXT")
    private String base64;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "usser_username", referencedColumnName = "username")
    private Usser usser;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "img", cascade = CascadeType.ALL)
    private Set<HardSkill> skills = new HashSet<>();

    public Image(String name, String type, String base64) {
        this.name = name;
        this.type = type;
        this.base64 = base64;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Usser getUsser() {
        return usser;
    }

    public void setUsser(Usser usser) {
        this.usser = usser;
    }

    public Set<HardSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<HardSkill> skills) {
        this.skills = skills;
    }

}
