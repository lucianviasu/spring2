package com.sda.spring.service;


import com.sda.spring.dto.CompanyCreateDto;
import com.sda.spring.dto.CompanyInfoDto;
import com.sda.spring.model.Company;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CompanyService {

    CompanyInfoDto create(CompanyCreateDto company, String userName);

    List<CompanyInfoDto> getAllCompanies(Integer pageNo,Integer pageSize, String sortBy);

    List<CompanyInfoDto> getAllCompanies();

    void populateDb(List<Company> companies);

    CompanyInfoDto findById(Integer id);
}
