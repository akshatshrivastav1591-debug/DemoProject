package com.example.React_Spring.Repo;

import com.example.React_Spring.TestingClass.BikePojoClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface RepoClass extends JpaRepository <BikePojoClass,Integer> {

}
