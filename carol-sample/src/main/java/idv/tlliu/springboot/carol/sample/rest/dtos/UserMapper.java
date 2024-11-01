package idv.tlliu.springboot.carol.sample.rest.dtos;

import org.mapstruct.Mapper;

import idv.tlliu.springboot.carol.model.entity.User;

@Mapper()
public interface UserMapper {
  public UserDto toDto(User user);
  
  public User toEntity(UserDto userDto);

}
