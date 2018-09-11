package com.journal.app.models.DTO;

import com.journal.app.models.domain.User;
import com.journal.app.models.domain.UserCompany;

import java.util.ArrayList;
import java.util.List;

public class MapDTO {


    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setUserDate(user.getUserDate());
        userDTO.setEmail(user.getEmail());
        user.setContactNumber(user.getContactNumber());
        userDTO.setGender(user.getGender());
        if (user.getUserCompanyList() != null) {
            List<UsersCompanyDTO> usersCompanyDTOList = new ArrayList<>();
//            for (UserCompany userCompany : user.getUserCompanyList()) {
//                usersCompanyDTOList.add(userCompanyToUsersCompanyDTO(userCompany));
//            }

            user.getUserCompanyList().forEach((userCompany -> usersCompanyDTOList.add(userCompanyToUsersCompanyDTO(userCompany))));
            userDTO.setCompanyList(usersCompanyDTOList);
        }
        return userDTO;
    }

    public static UsersCompanyDTO userCompanyToUsersCompanyDTO(UserCompany userCompany) {

        UsersCompanyDTO company = new UsersCompanyDTO();
        company.setUcId(userCompany.getId());
        company.setCompanyId(userCompany.getCompany().getId());
        company.setName(userCompany.getCompany().getName());
        company.setSchema(userCompany.getCompany().getSchema());
        company.setPartiesId(userCompany.getPartiesId());
        company.setStatus(userCompany.getStatus());
        List<String> authoritiesList = new ArrayList<>();
        userCompany.getAuthorities().forEach((authority) -> authoritiesList.add(authority.getRole().name()));
        company.setAuthorityList(authoritiesList);
        return company;
    }
}
