package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ronvel.farztev.admin.controller.dto.AlbumType;
import com.ronvel.farztev.admin.controller.dto.ListAlbumType;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.model.AlbumTypeModel;
import com.ronvel.farztev.admin.service.AlbumTypeService;

@Service
public class AlbumTypeServiceImpl implements AlbumTypeService {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private AlbumTypeDao albumTypeDao;

  @Override
  public Optional<AlbumType> findAlbumTypeById(Long id) {
    Optional<AlbumType> optional;
    AlbumTypeModel albumTypeModel = albumTypeDao.findOne(id);
    if (albumTypeModel == null) {
      optional = Optional.empty();
    } else {
      AlbumType albumType = mapper.map(albumTypeModel, AlbumType.class);
      optional = Optional.of(albumType);
    }
    return optional;
  }

  @Override
  public List<ListAlbumType> listAlbumTypes() {
    List<ListAlbumType> listAlbumTypes = new ArrayList<>();
    Iterable<AlbumTypeModel> albumTypes = albumTypeDao.findAll();
    albumTypes.forEach(albumTypeModel -> listAlbumTypes.add(mapToListAlbumType(albumTypeModel)));
    return listAlbumTypes;
  }

  @Override
  public List<ListAlbumType> listAlbumTypesByCountry(Long countryId) {
    List<ListAlbumType> listAlbumTypes = new ArrayList<>();
    Iterable<AlbumTypeModel> albumTypes = albumTypeDao.findAllByCountryId(countryId);
    albumTypes.forEach(albumTypeModel -> listAlbumTypes.add(mapToListAlbumType(albumTypeModel)));
    return listAlbumTypes;
  }
  
  @Override
  public ListAlbumType mapToListAlbumType(AlbumTypeModel albumTypeModel) {
    ListAlbumType listAlbumType = mapper.map(albumTypeModel, ListAlbumType.class);
    listAlbumType.setCountryId(albumTypeModel.getCountry().getId());
    listAlbumType.setCountryName(albumTypeModel.getCountry().getName());
    return listAlbumType;
  }

  @Override
  public AlbumType addAlbumType(AlbumType albumType) {
    AlbumTypeModel albumTypeModel = mapper.map(albumType, AlbumTypeModel.class);
    AlbumTypeModel resultAlbumTypeModel = albumTypeDao.save(albumTypeModel);
    AlbumType resultAlbumType = mapper.map(resultAlbumTypeModel, AlbumType.class);
    return resultAlbumType;
  }

  @Override
  public void updateAlbumType(Long id, AlbumType albumType) {
    albumType.setId(id);
    AlbumTypeModel albumTypeModel = mapper.map(albumType, AlbumTypeModel.class);
    albumTypeDao.save(albumTypeModel);
  }

  @Override
  public void deleteAlbumType(Long id) {
    albumTypeDao.delete(id);
  }

}
