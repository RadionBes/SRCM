package radion.ru.srcm.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dto.api.GroupApiDto;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.mapper.entity.GroupMapperEntity;
import radion.ru.srcm.mapper.response.GroupMapperResponse;
import radion.ru.srcm.dao.GroupJpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupJpaRepository repository;
    @Mock
    private MapApiCollageService apiCollageService;
    @Spy
    private GroupMapperEntity groupMapper = Mappers.getMapper(GroupMapperEntity.class);

    @Spy
    private GroupMapperResponse groupMapperResponse = Mappers.getMapper(GroupMapperResponse.class);
    @InjectMocks
    private GroupServiceImpl groupService;


    @Test
    void syncGroup() {
        GroupApiDto existingGroup = GroupApiDto.builder()
                .key("EXISTING_KEY")
                .name("Existing Group")
                .build();

        GroupApiDto newGroup1 = GroupApiDto.builder()
                .key("NEW_KEY_1")
                .name("New Group 1")
                .build();

        GroupApiDto newGroup2 = GroupApiDto.builder()
                .key("NEW_KEY_2")
                .name("New Group 2")
                .build();

        List<GroupApiDto> groupsFromApi = Arrays.asList(
                existingGroup, newGroup1, newGroup2
        );

        Group newEntity1 = Group.builder()
                .key("NEW_KEY_1")
                .name("New Group 1")
                .build();

        Group newEntity2 = Group.builder()
                .key("NEW_KEY_2")
                .name("New Group 2")
                .build();

        // Мокаем API
        when(apiCollageService.getListGroup()).thenReturn(groupsFromApi);

        // Мокаем проверку существования
        when(repository.existsGroupByKey("EXISTING_KEY")).thenReturn(true);
        when(repository.existsGroupByKey("NEW_KEY_1")).thenReturn(false);
        when(repository.existsGroupByKey("NEW_KEY_2")).thenReturn(false);

        // Мокаем маппинг
        when(groupMapper.toEntity(newGroup1)).thenReturn(newEntity1);
        when(groupMapper.toEntity(newGroup2)).thenReturn(newEntity2);

        // Act
        groupService.syncGroup();

        // Assert
        // Проверяем, что получили группы из API
        verify(apiCollageService).getListGroup();

        // Проверяем проверки существования для всех групп
        verify(repository).existsGroupByKey("EXISTING_KEY");
        verify(repository).existsGroupByKey("NEW_KEY_1");
        verify(repository).existsGroupByKey("NEW_KEY_2");

        // Проверяем, что маппер вызывался ТОЛЬКО для новых групп
        verify(groupMapper).toEntity(newGroup1);
        verify(groupMapper).toEntity(newGroup2);
        verify(groupMapper, never()).toEntity(existingGroup); // Для существующей не должен вызываться

        // Проверяем, что сохранили ТОЛЬКО новые группы
        verify(repository).save(newEntity1);
        verify(repository).save(newEntity2);

        // Проверяем, что не было лишних вызовов
        verifyNoMoreInteractions(repository, groupMapper);
    }

    @Test
    void getGroupList() {

    }

    @Test
    @DisplayName("Check for the return of all groups")
    void getAll_Check_for_the_return_of_all_groups() {
        List<Group> entities = Arrays.asList(
                Group.builder().id(1L).name("Group 1").key("G1").build(),
                Group.builder().id(2L).name("Group 2").key("G2").build()
        );
        when(repository.findAll()).thenReturn(entities);
        List<Group> response = groupService.getAll();
        assertEquals(entities.size(), response.size());
        assertEquals(entities.get(0).getId(), response.get(0).getId());
        assertEquals(entities.get(1).getId(), response.get(1).getId());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Checking for the logic of getting a group by id")
    void getGroupById_Checking_the_logic_of_getting_a_group_by_id() {
        var group = Group.builder().id(1L).name("Group 1").key("G1").build();

        when(repository.findById(group.getId())).thenReturn(Optional.of(group));
        Group response = groupService.getGroupById(group.getId());

        assertNotNull(response);
        assertEquals(Objects.requireNonNull(group).getId(), response.getId());
        verify(repository).findById(anyLong());
    }

    @Test
    void getGroupByKey() {


    }

    @Test
    void save() {
    }

    @Test
    void saveAll() {
    }
}