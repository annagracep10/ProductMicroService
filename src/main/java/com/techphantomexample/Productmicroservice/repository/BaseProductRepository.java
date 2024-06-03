package com.techphantomexample.Productmicroservice.repository;

import com.techphantomexample.Productmicroservice.model.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseProductRepository<T extends BaseProduct> extends JpaRepository<T, Integer> {
}
