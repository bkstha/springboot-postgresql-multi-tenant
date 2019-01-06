package com.journal.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.journal.app.utils.ApiConstant.API_VER;
import static com.journal.app.utils.ApiConstant.COMPANIES_PATH;

@RestController
@RequestMapping(API_VER + COMPANIES_PATH)
public class CompanyController {
}
