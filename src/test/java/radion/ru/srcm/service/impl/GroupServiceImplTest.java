package radion.ru.srcm.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import radion.ru.srcm.apiCollage.MapApiCollageService;
import radion.ru.srcm.dao.GroupJpaRepository;
import radion.ru.srcm.dto.api.GroupApiDto;
import radion.ru.srcm.dto.response.GroupResponse;
import radion.ru.srcm.entity.Group;
import radion.ru.srcm.exceptions.NotFoundByIdException;
import radion.ru.srcm.exceptions.NotFoundByKeyException;
import radion.ru.srcm.mapper.entity.GroupMapperEntity;
import radion.ru.srcm.mapper.response.GroupMapperResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupJpaRepository repository;
    @Mock
    private MapApiCollageService apiCollageService;
    @Mock
    private MessageSource messageSource;
    @Spy
    private GroupMapperEntity groupMapper = Mappers.getMapper(GroupMapperEntity.class);
    @Spy
    private GroupMapperResponse groupMapperResponse = Mappers.getMapper(GroupMapperResponse.class);
    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    @DisplayName("sync saves only groups that do not exist in repository")
    void sync() {
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
        List<GroupApiDto> groupsFromApi = Arrays.asList(existingGroup, newGroup1, newGroup2);

        Group newEntity1 = Group.builder()
                .key("NEW_KEY_1")
                .name("New Group 1")
                .build();
        Group newEntity2 = Group.builder()
                .key("NEW_KEY_2")
                .name("New Group 2")
                .build();

        when(apiCollageService.getListGroup()).thenReturn(groupsFromApi);
        when(repository.existsGroupByKey("EXISTING_KEY")).thenReturn(true);
        when(repository.existsGroupByKey("NEW_KEY_1")).thenReturn(false);
        when(repository.existsGroupByKey("NEW_KEY_2")).thenReturn(false);
        when(groupMapper.toEntity(newGroup1)).thenReturn(newEntity1);
        when(groupMapper.toEntity(newGroup2)).thenReturn(newEntity2);

        groupService.sync();

        verify(apiCollageService).getListGroup();
        verify(repository).existsGroupByKey("EXISTING_KEY");
        verify(repository).existsGroupByKey("NEW_KEY_1");
        verify(repository).existsGroupByKey("NEW_KEY_2");
        verify(groupMapper).toEntity(newGroup1);
        verify(groupMapper).toEntity(newGroup2);
        verify(groupMapper, never()).toEntity(existingGroup);
        verify(repository).save(newEntity1);
        verify(repository).save(newEntity2);
        verifyNoMoreInteractions(repository, groupMapper);
    }

    @Test
    @DisplayName("getGroupList maps all entities from repository to response DTOs")
    void getGroupList() {
        List<Group> entities = Arrays.asList(
                Group.builder()
                        .id(1L)
                        .key("G1")
                        .name("Group 1")
                        .year("2024")
                        .spec("Spec 1")
                        .hoz("Hoz 1")
                        .build(),
                Group.builder()
                        .id(2L)
                        .key("G2")
                        .name("Group 2")
                        .year("2025")
                        .spec("Spec 2")
                        .hoz("Hoz 2")
                        .build()
        );
        when(repository.findAll()).thenReturn(entities);

        List<GroupResponse> response = groupService.getGroupList();

        assertEquals(2, response.size());
        assertEquals(1L, response.get(0).getId());
        assertEquals("G1", response.get(0).getKey());
        assertEquals("Group 1", response.get(0).getName());
        assertEquals("2024", response.get(0).getYear());
        assertEquals(2L, response.get(1).getId());
        assertEquals("G2", response.get(1).getKey());
        verify(repository).findAll();
        verify(groupMapperResponse).toResponse(entities);
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
        Group group = Group.builder().id(1L).name("Group 1").key("G1").build();
        when(repository.findById(group.getId())).thenReturn(Optional.of(group));

        Group response = groupService.getGroupById(group.getId());

        assertNotNull(response);
        assertEquals(group.getId(), response.getId());
        verify(repository).findById(group.getId());
    }

    @Test
    @DisplayName("getGroupById throws NotFoundByIdException with localized message")
    void getGroupById_throws_when_group_does_not_exist() {
        Long groupId = 99L;
        when(repository.findById(groupId)).thenReturn(Optional.empty());
        when(messageSource.getMessage("warning.NotFoundGroupById", new Object[]{groupId}, null))
                .thenReturn("group not found by id");

        NotFoundByIdException exception = assertThrows(
                NotFoundByIdException.class,
                () -> groupService.getGroupById(groupId)
        );

        assertEquals("group not found by id", exception.getMessage());
        verify(repository).findById(groupId);
        verify(messageSource).getMessage("warning.NotFoundGroupById", new Object[]{groupId}, null);
    }

    @Test
    @DisplayName("Checking for the logic of getting a group by key")
    void getGroupByKey() {
        String key = "G1";
        Group group = Group.builder().id(1L).name("Group 1").key(key).build();
        when(repository.findGroupByKey(key)).thenReturn(Optional.of(group));

        Group response = groupService.getGroupByKey(key);

        assertNotNull(response);
        assertEquals(key, response.getKey());
        verify(repository).findGroupByKey(key);
    }

    @Test
    @DisplayName("getGroupByKey throws NotFoundByKeyException with localized message")
    void getGroupByKey_throws_when_group_does_not_exist() {
        String key = "UNKNOWN";
        when(repository.findGroupByKey(key)).thenReturn(Optional.empty());
        when(messageSource.getMessage("warning.NotFoundGroupByKey", new Object[]{key}, null))
                .thenReturn("group not found by key");

        NotFoundByKeyException exception = assertThrows(
                NotFoundByKeyException.class,
                () -> groupService.getGroupByKey(key)
        );

        assertEquals("group not found by key", exception.getMessage());
        verify(repository).findGroupByKey(key);
        verify(messageSource).getMessage("warning.NotFoundGroupByKey", new Object[]{key}, null);
    }

    @Test
    @DisplayName("save delegates entity persistence to repository")
    void save() {
        Group group = Group.builder().id(1L).key("G1").name("Group 1").build();

        groupService.save(group);

        verify(repository).save(group);
    }

    @Test
    @DisplayName("saveAll delegates batch persistence to repository")
    void saveAll() {
        List<Group> groups = Arrays.asList(
                Group.builder().id(1L).key("G1").name("Group 1").build(),
                Group.builder().id(2L).key("G2").name("Group 2").build()
        );

        groupService.saveAll(groups);

        verify(repository).saveAll(groups);
    }
}
