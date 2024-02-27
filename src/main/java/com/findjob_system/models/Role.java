package com.findjob_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users =  new HashSet<>();

    public Role(String name){
        this.name = name;
    }

    public void removellUSerFromRoles(){
        if(this.getUsers()!= null){
            List<User> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUSerFromRoles);
        }
    }

    public void removeUSerFromRoles(User users) {
        users.getRoles().remove(this);
        this.getUsers().remove(users);
    }

    public void assignUserToRole(User users) {
        users.getRoles().add(this);
        this.getUsers().add(users);
    }


}
