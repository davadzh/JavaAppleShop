package com.davadzh.bluebeard.BLL.Services.MasterService;

import com.davadzh.bluebeard.BLL.Constants.ExceptionMessages;
import com.davadzh.bluebeard.BLL.Exceptions.NotFoundException;
import com.davadzh.bluebeard.DAL.Master;
import com.davadzh.bluebeard.DTO.MasterDtos.AddMasterDto;
import com.davadzh.bluebeard.DTO.MasterDtos.DeleteMasterDto;
import com.davadzh.bluebeard.DTO.MasterDtos.UpdateMasterDto;
import com.davadzh.bluebeard.DTO.WorkTypeDtos.GetMastersByWorkTypeIdDto;
import com.davadzh.bluebeard.Repositories.MasterRepository;
import com.davadzh.bluebeard.Repositories.MasterWorkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasterService implements IMasterService {
    private MasterRepository masterRepository;
    private MasterWorkTypeRepository masterWorkTypeRepository;

    @Autowired
    public void setMasterServiceConfig(MasterRepository masterRepository,
                                    MasterWorkTypeRepository masterWorkTypeRepository){
        this.masterRepository = masterRepository;
        this.masterWorkTypeRepository = masterWorkTypeRepository;
    }


    @Override
    public List<Master> getMasters() {
        return masterRepository.findAll();
    }


    @Override
    public List<Master> getMastersByWorkTypeId(GetMastersByWorkTypeIdDto getMastersByWorkTypeIdDto) {
        return masterWorkTypeRepository
                .findAll()
                .stream()
                .filter(masterWorkType -> masterWorkType.getWorkType().getId().equals(
                        getMastersByWorkTypeIdDto.workTypeId
                ))
                .map(masterWorkType -> masterWorkType.getMaster())
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Master> findMasterById(Long masterId) {
        return masterRepository.findById(masterId);
    }


    @Override
    public Master addMaster(AddMasterDto addMasterDto) {
        var master = new Master(addMasterDto);
        masterRepository.save(master);

        return master;
    }


    @Override
    public Master updateMaster(UpdateMasterDto updateMasterDto) {
        var master = masterRepository.findById(updateMasterDto.masterId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.MASTER_NOT_FOUND));

        master.setFullName(updateMasterDto.fullName);
        master.setAge(updateMasterDto.age);
        master.setPosition(updateMasterDto.position);
        masterRepository.save(master);

        return master;
    }


    @Override
    public Master deleteMaster(DeleteMasterDto deleteMasterDto) {
        var master = masterRepository.findById(deleteMasterDto.masterId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessages.MASTER_NOT_FOUND));

        masterRepository.deleteById(deleteMasterDto.masterId);
        return master;
    }
}
