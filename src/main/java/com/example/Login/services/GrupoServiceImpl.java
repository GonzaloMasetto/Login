package com.example.Login.services;

import com.example.Login.entities.Grupo;
import com.example.Login.repositories.BaseRepository;
import com.example.Login.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoServiceImpl extends BaseServiceImpl<Grupo, Long> implements GrupoService{

    @Autowired
    private GrupoRepository grupoRepository;

    public GrupoServiceImpl(BaseRepository<Grupo, Long> baseRepository) {
        super(baseRepository);
    }


}
