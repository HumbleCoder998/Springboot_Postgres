package com.capgemini.employees.Repositories;

import com.capgemini.employees.Models.Address;
import com.capgemini.employees.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByLocation(String location);
    @Query("Select a from Address a WHERE a.location = :location")
    Optional<List<Address>> findByAddress(@Param("location") String location);
}
