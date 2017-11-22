package com.journal.app.models.services;

import com.journal.app.models.DTO.UniqueIdDTO;
import com.journal.app.models.domain.UniqueId;
import com.journal.app.models.repositories.UniqueIdRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueIdService {

    @Autowired
    private UniqueIdRepository uniqueIdRepository;

    public static boolean isUniqueIdEmpty(Session session) {
        boolean b;
        Long total = (Long) session
                .createQuery("select count(c) from UniqueId c")
                .getSingleResult();
        b = total <= 0;
        return b;
    }
    public boolean isUniqueIdEmpty() {
        return getCount() <= 0;
    }

    public Long getCount(){
        return uniqueIdRepository.count();
    }

    public void addUniqueId(UniqueIdDTO uniqueIdDTO){
        UniqueId uniqueId= toUniqueId(uniqueIdDTO);
        uniqueIdRepository.save(uniqueId);
    }

    private UniqueId toUniqueId(UniqueIdDTO uniqueIdDTO){
        return new UniqueId(uniqueIdDTO.getId(), uniqueIdDTO.getCode(), uniqueIdDTO.getUniqueId());
    }

    private UniqueIdDTO toUniqueIdDTO(UniqueId uniqueId){
        return new UniqueIdDTO(uniqueId.getId(), uniqueId.getCode(), uniqueId.getUniqueId());
    }

}
