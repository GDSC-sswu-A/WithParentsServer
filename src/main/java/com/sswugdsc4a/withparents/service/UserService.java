package com.sswugdsc4a.withparents.service;

import com.sswugdsc4a.withparents.dto.dto.ScheduleDTO;
import com.sswugdsc4a.withparents.dto.dto.medication.MedicationDTO;
import com.sswugdsc4a.withparents.dto.dto.photo.PhotoDTO;
import com.sswugdsc4a.withparents.dto.dto.user.*;
import com.sswugdsc4a.withparents.entity.Family;
import com.sswugdsc4a.withparents.entity.LastApiCallTime;
import com.sswugdsc4a.withparents.entity.LocationInfo;
import com.sswugdsc4a.withparents.entity.User;
import com.sswugdsc4a.withparents.exception.CustomException;
import com.sswugdsc4a.withparents.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final LocationInfoRepository locationInfoRepository;
    private final MedicationRepository medicationRepository;
    private final LastApiCallTimeRepository lastApiCallTimeRepository;
    private final ScheduleRepository scheduleRepository;
    private final PhotoRepository photoRepository;

    public User getUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new CustomException("invalid user id"));
    }

    public Family getFamily(Long id){
        return familyRepository.findById(id)
                .orElseThrow(() -> new CustomException("invalid family id"));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("invalid user id"));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("invalid email"));
    }

    public Boolean isExistingMember(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public Boolean areTheyAFamily(Long id){

        if (getUserById(id).getFamily() == null || getUser().getFamily() == null) {
            return false;
        }

        return getUserById(id).getFamily().getId() == getUser().getFamily().getId();
    }

    @Transactional
    public UserDTO createUser(String email) {
        return UserDTO.entityToDTO(
                userRepository.save(
                        new User(
                                null,
                                email,
                                "nickname",
                                null,
                                "ROLE_USER",
                                null,
                                null
                        )
                )
        );
    }

    @Transactional
    public FamilyDTO createFamily(String password) {
        User creator = getUser();

        return FamilyDTO.entityToDTO(
                familyRepository.save(
                        new Family(
                                null,
                                password,
                                null,
                                creator
                        )
                )
        );
    }

    @Transactional
    public UserDTO modifyUserInfo(
            String nickname,
            Long familyId,
            String familyPassword,
            Boolean isParent) {

        User user = getUser();

        if(nickname != null) {
            user.setNickname(nickname);
        }

        if(familyId != null) {

            if (familyPassword == null) {
                throw new CustomException("Family password does not exist");
            }

            if (!getFamily(familyId).getPassword().equals(familyPassword)) {
                throw new CustomException("Invalid Family Password");
            }

            user.setFamily(getFamily(familyId));
        }

        if(isParent != null) {
            user.setIsParent(isParent);
        }

        return UserDTO.entityToDTO(user);
    }

    public LocationInfoDTO setLocationInfo(
            String latitude,
            String longitude
    ) {

        return LocationInfoDTO.entityToDTO(
                locationInfoRepository.save(
                        new LocationInfo(
                                getUser().getId(),
                                null,
                                latitude,
                                longitude
                        )
                )
        );

    }

    public List<LocationAndNicknameDTO> getLocationInfo() {

        User user = getUser();

        if (user.getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }

        List<User> parents = userRepository.getParents(user.getFamily().getId());

        return parents
                .stream()
                .map(p -> {
                    LocationInfo locationInfo = locationInfoRepository.findById(p.getId()).orElse(null);

                    if (locationInfo == null) {
                        return new LocationAndNicknameDTO(
                                p.getId(),
                                p.getNickname(),
                                null,
                                null
                        );
                    }

                    return new LocationAndNicknameDTO(
                            p.getId(),
                            p.getNickname(),
                            locationInfo.getLatitude(),
                            locationInfo.getLongitude()
                    );

                })
                .collect(Collectors.toList());
    }

    public List<SimpleUserInfoDTO> getFamilyMemberList(){

        User user = getUser();

        if (user.getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }

        return userRepository.findAllByFamilyId(user.getFamily().getId())
                .stream()
                .map(e -> new SimpleUserInfoDTO(e.getId(), e.getNickname()))
                .collect(Collectors.toList());

    }

    public HomeInfoDTO getHomeInfo() {

        HomeInfoDTO response = new HomeInfoDTO();
        response.setUserList(getFamilyMemberList());
        response.setTodayMedicationList(getTodayMedicationList());
        response.setTodayScheduleList(getTodayScheduleList());
        response.setRecentPhotoList(getRecentPhotoList());

        return response;

    }

    private List<MedicationDTO> getTodayMedicationList() {

        int dayOfWeekNumber = switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            case 1 -> 6; // sun
            case 2 -> 0; // mon
            case 3 -> 1; // tue
            case 4 -> 2; // wed
            case 5 -> 3; // thu
            case 6 -> 4; // fri
            case 7 -> 5; // sat
            default -> 8; // ???
        };

        return medicationRepository.findAllByUserId(getUser().getId())
                .stream()
                .filter(e -> {
                    return e.getDayOfTheWeekList().charAt(dayOfWeekNumber) == '1';
                })
                .map(e -> MedicationDTO.entityToDto(e))
                .collect(Collectors.toList());

    }

    private List<ScheduleDTO> getTodayScheduleList(){

        return scheduleRepository.getTodaySchedule(getUser().getFamily().getId(), LocalDate.now())
                .stream()
                .map(e -> {return ScheduleDTO.entityToDTO(e);})
                .collect(Collectors.toList());

    }

    private List<PhotoDTO> getRecentPhotoList(){
        return photoRepository.getRecentPhotos(getUser().getFamily().getId())
                .stream()
                .map(e -> PhotoDTO.entityToDto(e))
                .collect(Collectors.toList());
    }

    public List<LastApiCallTime> getParentsLastApiCallTime() {

        User user = getUser();

        if (user.getFamily() == null) {
            throw new CustomException("Family id does not exist");
        }

        return userRepository.getParents(user.getFamily().getId())
                .stream()
                .map(e -> lastApiCallTimeRepository.findById(e.getId())
                        .orElse(new LastApiCallTime(e.getId(), null)))
                .collect(Collectors.toList());

    }
}
