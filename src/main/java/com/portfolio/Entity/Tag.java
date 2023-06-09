package com.portfolio.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    @Id
    @Column(name = "name", columnDefinition = "VARCHAR(150)", unique = true)
    private String name;

    @Column(name = "image", columnDefinition = "MEDIUMTEXT")
    private String image;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<Education> educations = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "tag")
    private Set<HardSkill> skills = new HashSet<>();

    public Tag() {
    }

    public Tag(String name, String img) {
        this.name = name;
        this.image = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<HardSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<HardSkill> skills) {
        this.skills = skills;
    }

}
